/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.Store;
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
public class StoreBean extends SuperEJB<Store> {

    public StoreBean() {
        this.className = "Store";
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(Store entity) {
        if (entity != null) {
            JsonObjectBuilder job;
            job = Json.createObjectBuilder();
            job.add("id", entity.getId())
                    .add("userid", entity.getUserid())
                    .add("name", entity.getName())
                    .add("kind", entity.getKind());
            if (entity.getAddress() != null) {
                job.add("address", entity.getAddress());
            } else {
                job.addNull("address");
            }
            if (entity.getContacter() != null) {
                job.add("contacter", entity.getContacter());
            } else {
                job.addNull("contacter");
            }
            if (entity.getPhone() != null) {
                job.add("phone", entity.getPhone());
            } else {
                job.addNull("phone");
            }
            if (entity.getTown() != null) {
                job.add("town", entity.getTown());
            } else {
                job.addNull("town");
            }
            if (entity.getCategory() != null) {
                job.add("category", entity.getCategory());
            } else {
                job.addNull("category");
            }
            if (entity.getPcc() != null) {
                job.add("pcc", entity.getPcc());
            } else {
                job.add("pcc", 0);
            }
            if (entity.getFeature() != null) {
                job.add("feature", entity.getFeature());
            } else {
                job.addNull("feature");
            }
            if (entity.getAction() != null) {
                job.add("action", entity.getAction());
            } else {
                job.addNull("action");
            }
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
            job.add("hot", entity.getHot())
                    .add("idx", entity.getIdx())
                    .add("itemcount", entity.getIdx());
            return job;
        } else {
            return null;
        }
    }

    public List<Store> findByKind(int id) {
        Query query;
        query = em.createNamedQuery("Store.findByKind");
        query.setParameter("kind", id);
        return query.getResultList();
    }

    public List<Store> findByKind(int id, int first, int pageSize) {
        Query query;
        query = em.createNamedQuery("Store.findByKind").setFirstResult(first).setMaxResults(pageSize);
        query.setParameter("kind", id);
        return query.getResultList();
    }

    public int getRowCountByKind(int kind) {
        Query query = em.createNamedQuery("Store.getRowCountByKind");
        query.setParameter("kind", kind);
        if (query.getSingleResult() == null) {
            return 0;
        } else {
            return Integer.parseInt(query.getSingleResult().toString());
        }
    }

    public int getRowCountByTown(String value) {
        Query query = em.createNamedQuery("Store.getRowCountByTown");
        query.setParameter("town", value);
        if (query.getSingleResult() == null) {
            return 0;
        } else {
            return Integer.parseInt(query.getSingleResult().toString());
        }
    }

}
