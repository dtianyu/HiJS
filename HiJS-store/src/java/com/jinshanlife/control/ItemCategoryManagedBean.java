/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.ItemCategoryBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.entity.ItemCategory;
import com.jinshanlife.entity.Store;
import com.jinshanlife.lazy.ItemCategoryModel;
import com.jinshanlife.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class ItemCategoryManagedBean extends SuperOperateBean<ItemCategory> {

    @EJB
    private StoreBean storeBean;

    @EJB
    private ItemCategoryBean sessionBean;

    private Store currentStore;

    /**
     * Creates a new instance of ItemCategoryManagedBean
     */
    public ItemCategoryManagedBean() {
        super(ItemCategory.class);
    }

    @Override
    protected boolean doBeforePersist() {
        if (newEntity != null && currentStore != null) {
            newEntity.setStoreid(currentStore.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        currentStore = storeBean.findByUserId(userManagedBean.getCurrentUser().getId()).get(0);
        if (currentStore != null) {
            setModel(new ItemCategoryModel(sessionBean, this.userManagedBean, this.currentStore));
        }
    }

}
