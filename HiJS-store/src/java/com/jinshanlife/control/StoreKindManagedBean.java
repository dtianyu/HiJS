/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.StoreKindBean;
import com.jinshanlife.entity.StoreKind;
import com.jinshanlife.lazy.StoreKindModel;
import com.jinshanlife.web.SuperOperateBean;
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
public class StoreKindManagedBean extends SuperOperateBean<StoreKind> {

    @EJB
    private StoreKindBean sessionBean;

    /**
     * Creates a new instance of StoreKindManagedBean
     */
    public StoreKindManagedBean() {
        super(StoreKind.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        setEntityList(sessionBean.findByStatus("V"));
        if (!entityList.isEmpty()) {
            jab = sessionBean.createJsonArrayBuilder(entityList);
            buildJsonFile(jab.build(), getAppDataPath(), "storekind.json");
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new StoreKindModel(sessionBean, userManagedBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
    }

}
