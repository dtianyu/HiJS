/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.SystemAdv;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SystemAdvBean extends SuperEJB<SystemAdv> {

    public SystemAdvBean() {
        this.className = "SystemAdv";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(SystemAdv entity) {
        JsonObjectBuilder job;
        job = Json.createObjectBuilder();
        if (entity != null) {
            job.add("id", entity.getId())
                    .add("adv", entity.getAdv());
            if (entity.getTitle() != null) {
                job.add("title", entity.getTitle());
            } else {
                job.add("title", "");
            }
        }
        return job;
    }

}
