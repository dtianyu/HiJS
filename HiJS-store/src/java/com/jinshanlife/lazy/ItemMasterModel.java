/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.control.UserManagedBean;
import com.jinshanlife.entity.ItemMaster;

/**
 *
 * @author C0160
 */
public class ItemMasterModel extends BaseModel<ItemMaster> {

    public ItemMasterModel(SuperEJB sessionBean, UserManagedBean userManagedBean) {
        this.superEJB = sessionBean;
        this.userManagedBean = userManagedBean;
    }

}
