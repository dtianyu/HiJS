/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.ejb.StoreKindBean;
import com.jinshanlife.entity.Store;
import com.jinshanlife.entity.StoreKind;
import com.jinshanlife.lazy.StoreModel;
import com.jinshanlife.web.SuperOperateBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "storeManagedBean")
@SessionScoped
public class StoreManagedBean extends SuperOperateBean<Store> {

    @EJB
    private StoreKindBean storeKindBean;

    @EJB
    private StoreBean sessionBean;

    private List<StoreKind> storeKindList;

    /**
     * Creates a new instance of StoreManagedBean
     */
    public StoreManagedBean() {
        super(Store.class);
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new StoreModel(sessionBean));
        setStoreKindList(storeKindBean.findAll());
    }

    /**
     * @return the storeKindList
     */
    public List<StoreKind> getStoreKindList() {
        return storeKindList;
    }

    /**
     * @param storeKindList the storeKindList to set
     */
    public void setStoreKindList(List<StoreKind> storeKindList) {
        this.storeKindList = storeKindList;
    }

}
