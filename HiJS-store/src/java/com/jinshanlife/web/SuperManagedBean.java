/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.web;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.control.UserManagedBean;
import com.jinshanlife.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author C0160
 * @param <T>
 */
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

    public void delete(T entity) {
        if (entity != null) {
            try {
                getSuperEJB().delete(entity);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
            init();
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("消息", "删除成功！"));
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
        }
    }

    public void persist() {
        if (getNewEntity() != null) {
            try {
                getSuperEJB().persist(getNewEntity());
                setNewEntity(null);
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
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.toString()));
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "更新成功！"));
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

}
