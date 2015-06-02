/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.ItemMaster;
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
public class ItemMasterBean extends SuperEJB<ItemMaster> {

    public ItemMasterBean() {
        this.className = "ItemMaster";
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
