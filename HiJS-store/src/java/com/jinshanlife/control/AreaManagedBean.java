/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.AreaBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.entity.Area;
import com.jinshanlife.lazy.AreaModel;
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
public class AreaManagedBean extends SuperOperateBean<Area> {

    @EJB
    private StoreBean storeBean;
    @EJB
    private AreaBean sessionBean;

    public AreaManagedBean() {
        super(Area.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        if (entityList == null) {
            setEntityList(sessionBean.findAll());
        }
        if (!entityList.isEmpty()) {
            for (Area entity : entityList) {
                entity.setStorecount(storeBean.getRowCountByTown(entity.getArea()));
                sessionBean.update(entity);
            }
            jab = sessionBean.createJsonArrayBuilder(entityList);
            buildJsonFile(jab.build(), getAppDataPath(), "town.json");
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new AreaModel(sessionBean, userManagedBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
    }

}
