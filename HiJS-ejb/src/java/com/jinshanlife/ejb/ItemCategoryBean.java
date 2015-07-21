/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.ItemCategory;
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
public class ItemCategoryBean extends SuperEJB<ItemCategory> {

    public ItemCategoryBean() {
        this.className = "ItemCategory";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(ItemCategory entity) {
        JsonObjectBuilder job;
        job = Json.createObjectBuilder();
        if (entity != null) {
            job.add("id", entity.getId())
                    .add("storeid", entity.getStoreid())
                    .add("category", entity.getCategory());
            if (entity.getItemcount() != null) {
                job.add("itemcount", entity.getItemcount());
            } else {
                job.add("itemcount", 0);
            }
        }
        return job;
    }

    public int getRowCountByStoreId(int id) {
        Query query = em.createNamedQuery(getClassName() + ".getRowCountByStoreId");
        query.setParameter("storeid", id);
        if (query.getSingleResult() == null) {
            return 0;
        } else {
            return Integer.parseInt(query.getSingleResult().toString());
        }
    }

    public List<ItemCategory> findByStoreId(int id) {
        Query query;
        query = em.createNamedQuery("ItemCategory.findByStoreId");
        query.setParameter("storeid", id);
        return query.getResultList();
    }

    public List<ItemCategory> findByStoreId(int id, int first, int pageSize) {
        Query query;
        query = em.createNamedQuery("ItemCategory.findByStoreId").setFirstResult(first).setMaxResults(pageSize);
        query.setParameter("storeid", id);
        return query.getResultList();
    }

}
