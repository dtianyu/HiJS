/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.CartBean;
import com.jinshanlife.ejb.CartDetailBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.entity.Cart;
import com.jinshanlife.entity.CartDetail;
import com.jinshanlife.entity.Store;
import com.jinshanlife.lazy.CartModel;
import com.jinshanlife.web.SuperOperateBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class CartManagedBean extends SuperOperateBean<Cart> {

    @EJB
    private StoreBean storeBean;

    @EJB
    private CartBean sessionBean;

    @EJB
    private CartDetailBean cartDetailBean;

    private List<CartDetail> cartDetails;
    private Store currentStore;

    /**
     * Creates a new instance of CartManagedBean
     */
    public CartManagedBean() {
        super(Cart.class);
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new CartModel(sessionBean, userManagedBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
    }

    @Override
    public String view(String path) {
        if (currentEntity != null) {
            this.currentStore = storeBean.findById(currentEntity.getStoreid());
            this.cartDetails = cartDetailBean.findByCartId(currentEntity.getCartid());
        }
        return path;
    }

    /**
     * @return the cartDetails
     */
    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    /**
     * @return the currentStore
     */
    public Store getCurrentStore() {
        return currentStore;
    }

}
