/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.web;

import com.jinshanlife.entity.BaseOperateEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindong
 * @param <T>
 */
@ManagedBean
@SessionScoped
public abstract class SuperOperateBean<T extends BaseOperateEntity> extends SuperManagedBean<T> {

    /**
     * Creates a new instance of SuperOperateBean
     *
     * @param entityClass
     */
    public SuperOperateBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create() {
        if (getNewEntity() == null) {
            T entity;
            try {
                entity = entityClass.newInstance();
                entity.setStatus("N");
                entity.setCreator(getUserManagedBean().getCurrentUser().getUserid());
                entity.setCredate(getUserManagedBean().getDate());
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperOperateBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setStatus("V");
                currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setCfmdate(getUserManagedBean().getDate());
                superEJB.update(currentEntity);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setStatus("M");
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdate(getUserManagedBean().getDate());
                currentEntity.setCfmuser(null);
                currentEntity.setCfmdate(null);
                superEJB.update(currentEntity);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }
}
