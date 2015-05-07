/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.comm.SuperManagedBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.entity.Store;
import com.jinshanlife.lazy.StoreModel;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class StoreManagedBean extends SuperManagedBean<Store> {

    @EJB
    private StoreBean storeBean;
    @ManagedProperty(value = "#{userManagedBean}")
    private UserManagedBean userManagedBean;

    /**
     * Creates a new instance of StoreManagedBean
     */
    public StoreManagedBean() {
        super(Store.class);
    }

    @Override
    public void init() {
        setSessionBean(storeBean);
        setModel(new StoreModel(storeBean));
    }

    @Override
    public void create() {
        if (getNewEntity() == null) {
            Store entity = new Store();
            entity.setStatus("N");
            entity.setCreator(getUserManagedBean().getCurrentUser().getUserid());
            entity.setCredate(getUserManagedBean().getDate());
            setNewEntity(entity);
        }
    }

    @Override
    public void verify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unverify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail(Store entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the storeBean
     */
    public StoreBean getStoreBean() {
        return storeBean;
    }

    /**
     * @param storeBean the storeBean to set
     */
    public void setStoreBean(StoreBean storeBean) {
        this.storeBean = storeBean;
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
