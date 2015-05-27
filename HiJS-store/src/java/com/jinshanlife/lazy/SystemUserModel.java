/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.SystemUser;

/**
 *
 * @author C0160
 */
public class SystemUserModel extends BaseModel<SystemUser> {
    
    public SystemUserModel(SuperEJB sessionBean){
        this.superEJB = sessionBean;
    }
    
}
