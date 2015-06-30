/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.Cart;
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
public class CartBean extends SuperEJB<Cart> {

    public CartBean() {
        this.className = "Cart";
    }

    public Cart findByCartId(String cartid) {
        Query query;
        query = em.createNamedQuery("Cart.findByCartId");
        query.setParameter("cartid", cartid);
        try {
            return (Cart) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cart> findByStoreId(int storeid) {
        Query query;
        query = em.createNamedQuery("Cart.findByStoreId");
        query.setParameter("storeid", storeid);
        return query.getResultList();
    }

}
