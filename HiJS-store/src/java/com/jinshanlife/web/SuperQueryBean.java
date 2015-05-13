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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperQueryBean<T extends BaseEntity> implements Serializable {

    protected Class<T> entityClass;
    protected SuperEJB superEJB;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected T currentEntity;
    protected List<T> entityList;
    protected LazyDataModel model;

    public SuperQueryBean() {

    }

    public SuperQueryBean(Class<T> entityClass) {
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
    }

    public void init() {
        setEntityList(retrieve());
        if (!getEntityList().isEmpty()) {
            setCurrentEntity(getEntityList().get(0));
        } 
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

