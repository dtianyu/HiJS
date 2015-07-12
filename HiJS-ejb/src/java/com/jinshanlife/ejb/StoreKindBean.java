/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.StoreKind;
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
public class StoreKindBean extends SuperEJB<StoreKind> {

    public StoreKindBean() {
        this.className = "StoreKind";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(StoreKind entity) {
        if (entity != null) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", entity.getId())
                    .add("name", entity.getName())
                    .add("storecount", entity.getStorecount());
            if (entity.getTitle() != null) {
                job.add("title", entity.getTitle());
            } else {
                job.addNull("title");
            }
            if (entity.getClassname() != null) {
                job.add("classname", entity.getClassname());
            } else {
                job.addNull("classname");
            }
            return job;
        } else {
            return null;
        }
    }

}
