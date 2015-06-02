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

    private List<StoreKind> storeKindList;

    /**
     * Creates a new instance of StoreManagedBean
     */
    public StoreManagedBean() {
        super(Store.class);
    }

    @Override
    protected void buildJsonObject() {
        if (currentEntity != null && userManagedBean.getCurrentUser() != null) {
            JsonObjectBuilder job = createJsonObject(currentEntity);
            String path = getAppDataPath();
            String name = path + "//" + currentEntity.getId() + ".json";
            buildJsonFile(job.build(), path, name);
        }
    }

    @Override
    protected void buildJsonArray() {
        if (storeKindList.isEmpty()) {
            setStoreKindList(storeKindBean.findAll());
        } else {
            for (StoreKind kind : storeKindList) {
                setEntityList(null);
                setEntityList(sessionBean.findByKind(kind.getId()));
                if (!entityList.isEmpty()) {
                    createJson(kind.getClassname() + ".json");
                    setEntityList(entityList.subList(0, getAppTopList() < entityList.size() ? getAppTopList() : entityList.size()));
                    if (!entityList.isEmpty()) {
                        createJson(kind.getClassname() + "Top.json");
                    }
                }
            }
        }
    }

    private void createJson(String fileName) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder job;
        for (Store entity : entityList) {
            job = createJsonObject(entity);
            jab.add(job);
        }
        String path = getAppDataPath();
        String name = path + "//" + fileName;
        buildJsonFile(jab.build(), path, name);
    }

    private JsonObjectBuilder createJsonObject(Store entity) {
        JsonObjectBuilder job;
        job = Json.createObjectBuilder();
        job.add("id", entity.getId())
                .add("name", entity.getName())
                .add("kind", entity.getKind());
        if (entity.getAddress() != null) {
            job.add("address", entity.getAddress());
        } else {
            job.addNull("address");
        }
        if (entity.getContacter() != null) {
            job.add("contacter", entity.getContacter());
        } else {
            job.addNull("contacter");
        }
        if (entity.getPhone() != null) {
            job.add("phone", entity.getPhone());
        } else {
            job.addNull("phone");
        }
        if (entity.getTown() != null) {
            job.add("town", entity.getTown());
        } else {
            job.addNull("town");
        }
        if (entity.getCategory() != null) {
            job.add("category", entity.getCategory());
        } else {
            job.addNull("category");
        }
        if (entity.getPcc() != null) {
            job.add("pcc", entity.getPcc());
        } else {
            job.add("pcc", 0);
        }
        if (entity.getFeature() != null) {
            job.add("feature", entity.getFeature());
        } else {
            job.addNull("feature");
        }
        if (entity.getAction() != null) {
            job.add("action", entity.getAction());
        } else {
            job.addNull("action");
        }
        if (entity.getLogo1() != null) {
            job.add("logo1", entity.getLogo1());
        } else {
            job.addNull("logo1");
        }
        if (entity.getLogo2() != null) {
            job.add("logo2", entity.getLogo2());
        } else {
            job.addNull("logo2");
        }
        job.add("hot", entity.getHot())
                .add("idx", entity.getIdx());
        return job;
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
            if (userManagedBean.getCurrentUser().getSuperuser() == 999) {
                setModel(new StoreModel(sessionBean));
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
