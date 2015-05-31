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

    public List<Store> findByUserId(int id) {
        Query query;
        query = em.createNamedQuery("Store.findByUserId");
        query.setParameter("userid", id);
        return query.getResultList();
    }
}
