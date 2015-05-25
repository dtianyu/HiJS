/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.web;

import com.jinshanlife.comm.Lib;
import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.control.UserManagedBean;
import com.jinshanlife.entity.BaseEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonStructure;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author KevinDong
 * @param <T>
 */
public abstract class SuperManagedBean<T extends BaseEntity> implements Serializable {

    private String appDataPath;
    private String appImgPath;
    private int appTopList;

    protected Class<T> entityClass;
    protected SuperEJB superEJB;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected T currentEntity;
    protected T newEntity;
    protected List<T> entityList;
    protected LazyDataModel model;

    protected String fileName;
    protected UploadedFile file;

    public SuperManagedBean() {

    }

    public SuperManagedBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PostConstruct
    public void construct() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            create();
            init();
        }
        appDataPath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.jinshanlife.web.appdatapath");
        appImgPath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.jinshanlife.web.appimgpath");
        appTopList = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.jinshanlife.web.toplist"));
    }

    @PreDestroy
    public void destory() {
        if (getEntityList() != null) {
            getEntityList().clear();
            setEntityList(null);
        }
        setCurrentEntity(null);
        setNewEntity(null);
    }

    protected void buildJsonFile(JsonStructure value, String filePath, String fileName) {

        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Lib.buildJson(value, fileName);
        } catch (IOException ex) {
            Logger.getLogger(SuperManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(null, ex.getMessage()));
        }

    }

    protected void buildJsonObject() {
        if (currentEntity != null) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", currentEntity.getId());
            String path = getAppDataPath();
            String name = path + "//" + userManagedBean.getCurrentUser().getUserid() + ".json";
            buildJsonFile(job.build(), path, name);
        }
    }

    protected void buildJsonArray() {
        retrieve();
        if (!entityList.isEmpty()) {
            JsonArrayBuilder jab = Json.createArrayBuilder();
            for (T entity : entityList) {
                jab.add(Json.createObjectBuilder()
                        .add("id", entity.getId()));
            }
            String path = getAppDataPath();
            String name = path + "//" + entityClass.getSimpleName() + ".json";
            buildJsonFile(jab.build(), path, name);
        }
    }

    public void create() {
        if (getNewEntity() == null) {
            try {
                T entity = entityClass.newInstance();
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String create(String path) {
        try {
            create();
            return path;
        } catch (Exception e) {
            return "404";
        }
    }

    public void delete() {
        if (currentEntity != null) {
            delete(currentEntity);
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("消息", "没有选择删除数据！"));
        }
    }

    public void delete(T entity) {
        if (entity != null) {
            try {
                getSuperEJB().delete(entity);
                init();
                if (userManagedBean.getCurrentUser().getSuperuser()) {
                    buildJsonArray();
                } else {
                    buildJsonObject();
                }
                FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("消息", "删除成功！"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public void edit(T entity) {
        if (entity != null) {
            setCurrentEntity(entity);
        }
    }

    public void init() {
        setEntityList(retrieve());
        if (!getEntityList().isEmpty()) {
            setCurrentEntity(getEntityList().get(0));
        } else {
            setCurrentEntity(getNewEntity());
        }
    }

    public void persist() {
        if (getNewEntity() != null) {
            try {
                getSuperEJB().persist(getNewEntity());
                setNewEntity(null);
                create();
                if (userManagedBean.getCurrentUser().getSuperuser()) {
                    buildJsonArray();
                } else {
                    buildJsonObject();
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "更新成功！"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public List<T> retrieve() {
        setEntityList(getSuperEJB().findAll());
        return entityList;
    }

    public void save() {
        if (currentEntity != null) {
            try {
                getSuperEJB().update(currentEntity);
                if (userManagedBean.getCurrentUser().getSuperuser()) {
                    buildJsonArray();
                } else {
                    buildJsonObject();
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "更新成功！"));

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.toString()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有选择更新数据！"));
        }
    }

    public void sendNotification(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void view(T entity) {
        if (entity != null) {
            setCurrentEntity(entity);
        }
    }

    /**
     * @return the currentEntity
     */
    public T getCurrentEntity() {
        return currentEntity;
    }

    /**
     * @param currentEntity the currentEntity to set
     */
    public void setCurrentEntity(T currentEntity) {
        this.currentEntity = currentEntity;
    }

    /**
     * @return the newEntity
     */
    public T getNewEntity() {
        return newEntity;
    }

    /**
     * @param newEntity the newEntity to set
     */
    public void setNewEntity(T newEntity) {
        this.newEntity = newEntity;
    }

    /**
     * @return the entityList
     */
    public List<T> getEntityList() {
        return entityList;
    }

    /**
     * @param entityList the entityList to set
     */
    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    /**
     * @return the superEJB
     */
    public SuperEJB getSuperEJB() {
        return superEJB;
    }

    /**
     * @param superEJB the superEJB to set
     */
    public void setSuperEJB(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    /**
     * @return the model
     */
    public LazyDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(LazyDataModel model) {
        this.model = model;
    }

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    public void handleFileUpload(FileUploadEvent event) {

        file = event.getFile();
        if (file != null && getCurrentEntity() != null) {
            try {
                setFileName(file.getFileName());
                upload();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, getFileName() + " is uploaded."));
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage("Failure", e.toString());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "文件或实体对象不存在"));
        }
    }

    protected void upload() throws IOException {
        try {

            InputStream in = file.getInputstream();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.setCharacterEncoding("UTF-8");

            File dir = new File(getAppImgPath() + userManagedBean.getCurrentUser().getUserid());
            if (!dir.exists()) {
                dir.mkdirs();
            }

            OutputStream out = new FileOutputStream(new File(dir.getAbsolutePath() + "//" + getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while (true) {
                read = in.read(bytes);
                if (read < 0) {
                    break;
                }
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(null, e.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the appDataPath
     */
    public String getAppDataPath() {
        return appDataPath;
    }

    /**
     * @return the appImgPath
     */
    public String getAppImgPath() {
        return appImgPath;
    }

    /**
     * @return the appTopList
     */
    public int getAppTopList() {
        return appTopList;
    }

}
