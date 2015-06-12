/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.Area;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class AreaBean extends SuperEJB<Area> {

    public AreaBean() {
        this.className = "Area";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(Area entity) {
        JsonObjectBuilder job;
        job = Json.createObjectBuilder();
        if (entity != null) {
            job.add("id", entity.getId())
                    .add("area", entity.getArea())
                    .add("storecount", entity.getStorecount());
        }
        return job;
    }

}
