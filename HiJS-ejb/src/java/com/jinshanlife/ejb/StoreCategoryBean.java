/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.StoreCategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class StoreCategoryBean extends SuperEJB<StoreCategory> {

    public StoreCategoryBean() {
        this.className = "StoreCategory";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(StoreCategory entity) {
        if (entity != null) {
            JsonObjectBuilder job;
            job = Json.createObjectBuilder();
            job.add("id", entity.getId())
                    .add("kind", entity.getKind())
                    .add("category", entity.getCategory());
            return job;
        } else {
            return null;
        }
    }

    public List<StoreCategory> findByKind(int id) {
        Query query;
        query = em.createNamedQuery("StoreCategory.findByKind");
        query.setParameter("kind", id);
        return query.getResultList();
    }

    public List<StoreCategory> findByKind(int id, int first, int pageSize) {
        Query query;
        query = em.createNamedQuery("StoreCategory.findByKind").setFirstResult(first).setMaxResults(pageSize);
        query.setParameter("kind", id);
        return query.getResultList();
    }

}
