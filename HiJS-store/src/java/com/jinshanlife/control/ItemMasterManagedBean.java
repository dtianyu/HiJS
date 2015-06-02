/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.ItemMasterBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.entity.ItemMaster;
import com.jinshanlife.entity.Store;
import com.jinshanlife.lazy.ItemMasterModel;
import com.jinshanlife.web.SuperOperateBean;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class ItemMasterManagedBean extends SuperOperateBean<ItemMaster> {

    @EJB
    private StoreBean storeBean;

    @EJB
    private ItemMasterBean sessionBean;

    private Store currentStore;

    /**
     * Creates a new instance of ItemMasterManagedBean
     */
    public ItemMasterManagedBean() {
        super(ItemMaster.class);
    }

    @Override
    protected boolean doBeforePersist() {
        if (newEntity != null && currentStore != null) {
            newEntity.setUserid(currentStore.getUserid());
            newEntity.setStoreid(currentStore.getId());
            newEntity.setHot(4);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new ItemMasterModel(sessionBean, this.userManagedBean));
        currentStore = storeBean.findByUserId(userManagedBean.getCurrentUser().getId()).get(0);
    }

}
