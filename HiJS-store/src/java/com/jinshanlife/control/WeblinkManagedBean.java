/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.WeblinkBean;
import com.jinshanlife.entity.Weblink;
import com.jinshanlife.lazy.WeblinkModel;
import com.jinshanlife.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "weblinkManagedBean")
@SessionScoped
public class WeblinkManagedBean extends SuperOperateBean<Weblink> {

    @EJB
    private WeblinkBean sessionBean;

    /**
     * Creates a new instance of WeblinkManagedBean
     */
    public WeblinkManagedBean() {
        super(Weblink.class);
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new WeblinkModel(sessionBean));
    }

    @Override
    protected void buildJsonArray() {
        retrieve();
        if (!entityList.isEmpty()) {
            createJson(entityClass.getSimpleName() + ".json");
        }
        setEntityList(null);
        setEntityList(sessionBean.findByLogo2(0, getAppTopList()));
        if (!entityList.isEmpty()) {
            createJson("Weblink2.json");
        }
    }

    private void createJson(String fileName) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder job;
        for (Weblink entity : entityList) {
            job = Json.createObjectBuilder();
            job.add("id", entity.getId())
                    .add("name", entity.getName())
                    .add("url", entity.getUrl());
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
            job.add("idx", entity.getIdx());
            jab.add(job);
        }
        String path = getAppDataPath();
        String name = path + "//" + fileName;
        buildJsonFile(jab.build(), path, name);
    }
    
}
