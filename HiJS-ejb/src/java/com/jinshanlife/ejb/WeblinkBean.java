/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.Weblink;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class WeblinkBean extends SuperEJB<Weblink> {

    public WeblinkBean() {
        this.className = "Weblink";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(Weblink entity) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", entity.getId())
                .add("name", entity.getName())
                .add("url", entity.getUrl());
        if (entity.getLogo1() != null) {
            job.add("logo1", entity.getLogo1());
        } else {
            job.addNull("logo1");
        }
        if (entity.getLogo2() != null) {
            job.add("logo2", entity.getLogo2());
        } else {
            job.addNull("logo2");
        }
        job.add("idx", entity.getIdx());
        return job;
    }

    public List<Weblink> findByLogo2() {
        Query query;
        query = em.createNamedQuery("Weblink.findByLogo2");
        return query.getResultList();
    }

    public List<Weblink> findByLogo2(int first, int pageSize) {
        Query query;
        query = em.createNamedQuery("Weblink.findByLogo2").setFirstResult(first).setMaxResults(pageSize);
        return query.getResultList();
    }
}
