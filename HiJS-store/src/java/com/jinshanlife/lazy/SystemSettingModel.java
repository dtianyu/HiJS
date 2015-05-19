/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.SystemSetting;

/**
 *
 * @author C0160
 */
public class SystemSettingModel extends BaseModel<SystemSetting> {

    public SystemSettingModel(SuperEJB sessionBean) {
        this.superEJB = sessionBean;
    }

}
