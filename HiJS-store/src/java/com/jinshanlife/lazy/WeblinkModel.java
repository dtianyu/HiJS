/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.Weblink;

/**
 *
 * @author C0160
 */
public class WeblinkModel extends BaseModel<Weblink> {

    public WeblinkModel(SuperEJB sessionBean) {
        this.superEJB = sessionBean;
    }


}
