/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.AreaBean;
import com.jinshanlife.ejb.CartBean;
import com.jinshanlife.ejb.ItemMasterBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.ejb.StoreCategoryBean;
import com.jinshanlife.ejb.StoreKindBean;
import com.jinshanlife.entity.Area;
import com.jinshanlife.entity.ItemMaster;
import com.jinshanlife.entity.Store;
import com.jinshanlife.entity.StoreCategory;
import com.jinshanlife.entity.StoreKind;
import com.jinshanlife.lazy.StoreModel;
import com.jinshanlife.web.SuperOperateBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    private AreaBean areaBean;
    @EJB
    private CartBean cartBean;
    @EJB
    private StoreKindBean storeKindBean;
    @EJB
    private StoreCategoryBean storeCategoryBean;
    @EJB
    private StoreBean sessionBean;
    @EJB
    private ItemMasterBean itemMasterBean;

    private List<Area> areaList;
    private List<StoreKind> storeKindList;
    private List<StoreCategory> storeCategoryList;

    private int newOrderCount;

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
        if (storeKindList == null || storeKindList.isEmpty()) {
            setStoreKindList(storeKindBean.findAll());
        } else {
            for (StoreKind kind : storeKindList) {
                setEntityList(null);
                setEntityList(sessionBean.findByKind(kind.getId()));
                if (!entityList.isEmpty()) {
                    jab = sessionBean.createJsonArrayBuilder(entityList);
                    name = kind.getClassname() + ".json";
                    buildJsonFile(jab.build(), path, name);

                    kind.setStorecount(entityList.size());
                    storeKindBean.update(kind);

                    setEntityList(entityList.subList(0, getAppTopList() < entityList.size() ? getAppTopList() : entityList.size()));
                    jab = sessionBean.createJsonArrayBuilder(entityList);
                    name = kind.getClassname() + "Top.json";
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
                currentEntity.setItemcount(itemMasterList.size());
                try {
                    this.save();
                } catch (Exception ex) {
                    Logger.getLogger(StoreManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            name = currentEntity.getId().toString() + ".json";
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
            newEntity.setItemcount(0);
            userManagedBean.getCurrentUser().setOwnstore(Boolean.TRUE);
            userManagedBean.update();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void create() {
        super.create();
        if (this.newEntity != null) {
            newEntity.setPcc(BigDecimal.ZERO);
            newEntity.setFreightfree(BigDecimal.ZERO);
            newEntity.setFreight(BigDecimal.ZERO);
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setAreaList(areaBean.findAll());
        setStoreKindList(storeKindBean.findAll());
        setStoreCategoryList(storeCategoryBean.findAll());
        if (userManagedBean.getCurrentUser() != null) {
            if (userManagedBean.getCurrentUser().getSuperuser()) {
                setModel(new StoreModel(sessionBean, userManagedBean));
                setCurrentEntity(newEntity);
            } else if (userManagedBean.getCurrentUser().getOwnstore()) {
                setCurrentEntity(sessionBean.findByUserId(userManagedBean.getCurrentUser().getId()).get(0));
                HashMap filters = new HashMap();
                filters.put("status", "N");
                filters.put("storeid", currentEntity.getId());
                setNewOrderCount(cartBean.findAll(filters).size());
            } else {
                setCurrentEntity(newEntity);
            }
        }
    }

    public void onKindChangedNew() {
        if (newEntity != null && newEntity.getKind() != 0) {
            onKindChanged(newEntity.getKind());
        } else {
            onKindChanged(0);
        }
    }

    public void onKindChangedEdit() {
        if (currentEntity != null && currentEntity.getKind() != 0) {
            onKindChanged(currentEntity.getKind());
        } else {
            onKindChanged(0);
        }
    }

    private void onKindChanged(int kind) {
        setStoreCategoryList(storeCategoryBean.findByKind(kind));
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

    /**
     * @return the storeCategoryList
     */
    public List<StoreCategory> getStoreCategoryList() {
        return storeCategoryList;
    }

    /**
     * @param storeCategoryList the storeCategoryList to set
     */
    public void setStoreCategoryList(List<StoreCategory> storeCategoryList) {
        this.storeCategoryList = storeCategoryList;
    }

    /**
     * @return the areaList
     */
    public List<Area> getAreaList() {
        return areaList;
    }

    /**
     * @param areaList the areaList to set
     */
    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    /**
     * @return the newOrderCount
     */
    public int getNewOrderCount() {
        return newOrderCount;
    }

    /**
     * @param newOrderCount the newOrderCount to set
     */
    public void setNewOrderCount(int newOrderCount) {
        this.newOrderCount = newOrderCount;
    }

}
