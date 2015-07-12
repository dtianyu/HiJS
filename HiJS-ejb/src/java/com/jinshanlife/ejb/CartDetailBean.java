/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.CartDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class CartDetailBean extends SuperEJB<CartDetail> {

    public CartDetailBean() {
        this.className="CartDetail";
    }
       
    public List<CartDetail> findByCartId(String cartid) {
        Query query;
        query = em.createNamedQuery("CartDetail.findByCartId");
        query.setParameter("cartid", cartid);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<CartDetail> findByStoreId(int storeid) {
        Query query;
        query = em.createNamedQuery("Cart.findByStoreId");
        query.setParameter("storeid", storeid);
        return query.getResultList();
    }

}
