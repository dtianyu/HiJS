/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.ItemMasterBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.ejb.StoreKindBean;
import com.jinshanlife.entity.ItemMaster;
import com.jinshanlife.entity.Store;
import com.jinshanlife.entity.StoreKind;
import com.jinshanlife.lazy.StoreModel;
import com.jinshanlife.web.SuperOperateBean;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

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

    @EJB
    private ItemMasterBean itemMasterBean;

    private List<StoreKind> storeKindList;

    /**
     * Creates a new instance of StoreManagedBean
     */
    public StoreManagedBean() {
        super(Store.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        String path = getAppDataPath();
        String name;
        if (storeKindList.isEmpty()) {
            setStoreKindList(storeKindBean.findAll());
        } else {
            for (StoreKind kind : storeKindList) {
                setEntityList(null);
                setEntityList(sessionBean.findByKind(kind.getId()));
                if (!entityList.isEmpty()) {
                    jab = sessionBean.createJsonArrayBuilder(entityList);
                    name = path + "//" + kind.getClassname() + ".json";
                    buildJsonFile(jab.build(), path, name);

                    setEntityList(entityList.subList(0, getAppTopList() < entityList.size() ? getAppTopList() : entityList.size()));
                    jab = sessionBean.createJsonArrayBuilder(entityList);
                    name = path + "//" + kind.getClassname() + "Top.json";
                    buildJsonFile(jab.build(), path, name);
                }
            }
        }
    }

    @Override
    protected void buildJsonObject() {
        if (currentEntity != null) {
            JsonObjectBuilder job;
            String path = getAppDataPath();
            String name;
            job = sessionBean.createJsonObjectBuilder(currentEntity);
            List<ItemMaster> itemMasterList = itemMasterBean.findByStoreId(currentEntity.getId());
            if (!itemMasterList.isEmpty()) {
                job.add("content", itemMasterBean.createJsonArrayBuilder(itemMasterList));
            }
            name = path + "//" + currentEntity.getId().toString() + ".json";
            buildJsonFile(job.build(), path, name);
        }
    }

    @Override
    protected boolean doBeforePersist() {
        if (newEntity != null && userManagedBean.getCurrentUser() != null) {
            newEntity.setUserid(userManagedBean.getCurrentUser().getId());
            newEntity.setPcc(BigDecimal.ZERO);
            newEntity.setHot(4);
            newEntity.setIdx(0);
            userManagedBean.getCurrentUser().setOwnstore(Boolean.TRUE);
            userManagedBean.update();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setStoreKindList(storeKindBean.findAll());
        if (userManagedBean.getCurrentUser() != null) {
            if (userManagedBean.getCurrentUser().getSuperuser()) {
                setModel(new StoreModel(sessionBean));
                setCurrentEntity(newEntity);
            } else if (userManagedBean.getCurrentUser().getOwnstore()) {
                setCurrentEntity(sessionBean.findByUserId(userManagedBean.getCurrentUser().getId()).get(0));
            } else {
                setCurrentEntity(newEntity);
            }
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
