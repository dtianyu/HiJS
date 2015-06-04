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
    protected void buildJsonArray() {
        String path = getAppDataPath();
        String name;
        retrieve();
        if (!entityList.isEmpty()) {
            name = entityClass.getSimpleName() + ".json";
            buildJsonFile(sessionBean.createJsonArrayBuilder(entityList).build(), path, name);
        }
        setEntityList(null);
        setEntityList(sessionBean.findByLogo2(0, getAppTopList()));
        if (!entityList.isEmpty()) {
            name = "Weblink2.json";
            buildJsonFile(sessionBean.createJsonArrayBuilder(entityList).build(), path, name);
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new WeblinkModel(sessionBean, userManagedBean));
    }

}
