/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.ItemMaster;
import java.math.BigDecimal;
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
public class ItemMasterBean extends SuperEJB<ItemMaster> {

    public ItemMasterBean() {
        this.className = "ItemMaster";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(ItemMaster entity) {
        JsonObjectBuilder job;
        job = Json.createObjectBuilder();
        if (entity != null) {

            job.add("id", entity.getId())
                    .add("storeid", entity.getStoreid())
                    .add("userid", entity.getUserid());
            if (entity.getItemCategory() != null) {
                job.add("categoryid", entity.getItemCategory().getId());
                job.add("category", entity.getItemCategory().getCategory());
            } else {
                job.addNull("categoryid");
                job.addNull("category");
            }
            if (entity.getItemno() != null) {
                job.add("itemno", entity.getItemno());
            } else {
                job.addNull("itemno");
            }
            if (entity.getItemdesc() != null) {
                job.add("itemdesc", entity.getItemdesc());
            } else {
                job.addNull("itemdesc");
            }
            if (entity.getItemspec() != null) {
                job.add("itemspec", entity.getItemspec());
            } else {
                job.addNull("itemspec");
            }
            if (entity.getItemproperty() != null) {
                job.add("itemproperty", entity.getItemproperty());
            } else {
                job.addNull("itemproperty");
            }
            if (entity.getBrand() != null) {
                job.add("brand", entity.getBrand());
            } else {
                job.addNull("brand");
            }
            if (entity.getBatch() != null) {
                job.add("batch", entity.getBatch());
            } else {
                job.addNull("batch");
            }
            if (entity.getSn() != null) {
                job.add("sn", entity.getSn());
            } else {
                job.addNull("sn");
            }
            job.add("unit", entity.getUnit())
                    .add("price", entity.getPrice())
                    .add("hot", entity.getHot())
                    .add("idx", entity.getIdx());

            if (entity.getLogo1() != null) {
                job.add("logo1", entity.getLogo1());
            } else {
                job.addNull("logo1");
            }
            if (entity.getLogo1() != null) {
                job.add("logo2", entity.getLogo1());
            } else {
                job.addNull("logo2");
            }

        }
        return job;
    }

    public List<ItemMaster> findByKind(int id) {
        Query query;
        query = em.createNamedQuery("ItemMaster.findByKind");
        query.setParameter("kind", id);
        return query.getResultList();
    }

    public List<ItemMaster> findByKind(int id, int first, int pageSize) {
        Query query;
        query = em.createNamedQuery("ItemMaster.findByKind").setFirstResult(first).setMaxResults(pageSize);
        query.setParameter("kind", id);
        return query.getResultList();
    }

    public List<ItemMaster> findByStoreId(int id) {
        Query query;
        query = em.createNamedQuery("ItemMaster.findByStoreId");
        query.setParameter("storeid", id);
        return query.getResultList();
    }

    public List<ItemMaster> findByStoreId(int id, int first, int pageSize) {
        Query query;
        query = em.createNamedQuery("ItemMaster.findByStoreId").setFirstResult(first).setMaxResults(pageSize);
        query.setParameter("storeid", id);
        return query.getResultList();
    }

}
