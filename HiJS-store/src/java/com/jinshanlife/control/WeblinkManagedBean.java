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
            JsonArrayBuilder jab = Json.createArrayBuilder();
            for (Weblink entity : entityList) {
                jab.add(Json.createObjectBuilder()
                        .add("id", entity.getId())
                        .add("name", entity.getName())
                        .add("url", entity.getUrl())
                        .add("logo1", entity.getLogo1())
                        .add("logo2", entity.getLogo2())
                        .add("idx", entity.getIdx())
                );
            }
            String path = userManagedBean.getSetting().getWebpath()+"/app/data";
            String name = path +"//" + entityClass.getSimpleName() + ".json";
            buildJsonFile(jab.build(), path, name);
        }
    }

}
