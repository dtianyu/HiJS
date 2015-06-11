/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.StoreCategoryBean;
import com.jinshanlife.ejb.StoreKindBean;
import com.jinshanlife.entity.StoreCategory;
import com.jinshanlife.entity.StoreKind;
import com.jinshanlife.lazy.StoreCategoryModel;
import com.jinshanlife.web.SuperOperateBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class StoreCategoryManagedBean extends SuperOperateBean<StoreCategory> {

    @EJB
    StoreCategoryBean sessionBean;

    @EJB
    StoreKindBean storeKindBean;

    private List<StoreKind> storeKindList;

    public StoreCategoryManagedBean() {
        super(StoreCategory.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        String name;
        if (storeKindList.isEmpty()) {
            setStoreKindList(storeKindBean.findAll());
        } else {
            for (StoreKind kind : storeKindList) {
                setEntityList(null);
                setEntityList(sessionBean.findByKind(kind.getId()));
                if (!entityList.isEmpty()) {
                    jab = sessionBean.createJsonArrayBuilder(entityList);
                    name = kind.getClassname() + "Category.json";
                    buildJsonFile(jab.build(), getAppDataPath(), name);
                }
            }
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setStoreKindList(storeKindBean.findAll());
        setModel(new StoreCategoryModel(sessionBean, userManagedBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
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
