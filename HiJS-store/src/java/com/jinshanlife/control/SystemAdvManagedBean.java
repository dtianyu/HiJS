/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.SystemAdvBean;
import com.jinshanlife.entity.SystemAdv;
import com.jinshanlife.lazy.SystemAdvModel;
import com.jinshanlife.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class SystemAdvManagedBean extends SuperOperateBean<SystemAdv> {

    @EJB
    private SystemAdvBean systemAdvBean;

    public SystemAdvManagedBean() {
        super(SystemAdv.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        String name = "Advert.json";
        setEntityList(systemAdvBean.findByStatus("V"));
        if(!this.entityList.isEmpty()){
            jab = systemAdvBean.createJsonArrayBuilder(this.entityList);
            buildJsonFile(jab.build(), getAppDataPath(), name);
        }

    }

    @Override
    public void init() {
        setSuperEJB(systemAdvBean);
        setModel(new SystemAdvModel(systemAdvBean, this.userManagedBean));
    }

}
