/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.web;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.control.UserManagedBean;
import com.jinshanlife.entity.BaseEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author KevinDong
 * @param <T>
 */
@ManagedBean
@SessionScoped
public abstract class SuperManagedBean<T extends BaseEntity> implements Serializable {

    protected Class<T> entityClass;
    protected SuperEJB superEJB;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected T currentEntity;
    protected T newEntity;
    protected List<T> entityList;
    protected LazyDataModel model;

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

    public abstract String viewDetail(T entity);

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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "更新成功！"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public void save() {
        if (currentEntity != null) {
            try {
                getSuperEJB().update(currentEntity);
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

    public List<T> retrieve() {
        return getSuperEJB().findAll();
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

    protected String destination;
    protected String fileName;
    protected UploadedFile file;

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        if (file != null && getCurrentEntity() != null) {
            try {
                setFileName(file.getFileName());
                upload();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", getFileName() + " is uploaded."));
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage("Failure", e.toString());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("错误", "文件或实体对象不存在"));
        }
    }

    protected void upload() throws IOException {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.setCharacterEncoding("UTF-8");
            InputStream in = file.getInputstream();

            if (userManagedBean.getSetting() != null) {
                setDestination(userManagedBean.getSetting().getWebpath() + "//app//img//" + currentEntity.getId().toString());
            }

            File dir = new File(getDestination());
            if (!dir.exists()) {
                dir.mkdir();
            }

            OutputStream out = new FileOutputStream(new File(getDestination() + "//" + getFileName()));
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
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
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

}
