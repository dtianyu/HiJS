/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.SystemSettingBean;
import com.jinshanlife.entity.SystemSetting;
import com.jinshanlife.lazy.SystemSettingModel;
import com.jinshanlife.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class SystemSettingManagedBean extends SuperOperateBean<SystemSetting> {

    @EJB
    private SystemSettingBean sessionBean;

    /**
     * Creates a new instance of SystemSettingManagedBean
     */
    public SystemSettingManagedBean() {
        super(SystemSetting.class);
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new SystemSettingModel(sessionBean));
    }

}
