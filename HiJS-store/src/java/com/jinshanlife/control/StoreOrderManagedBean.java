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
import com.jinshanlife.lazy.StoreOrderModel;
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
public class StoreOrderManagedBean extends SuperOperateBean<Cart> {

    @EJB
    private StoreBean storeBean;

    @EJB
    private CartBean cartBean;

    @EJB
    private CartDetailBean cartDetailBean;

    private List<CartDetail> cartDetails;
    private Store currentStore;

    /**
     * Creates a new instance of CartManagedBean
     */
    public StoreOrderManagedBean() {
        super(Cart.class);
    }

    @Override
    public void init() {
        setSuperEJB(cartBean);
        setModel(new StoreOrderModel(cartBean, storeBean, userManagedBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
    }

    @Override
    public String view(String path) {
        if (currentEntity != null) {
            this.currentStore = storeBean.findById(currentEntity.getStoreid());
            this.setCartDetails(cartDetailBean.findByCartId(currentEntity.getCartid()));
        }
        return path;
    }

    /**
     * @return the currentStore
     */
    public Store getCurrentStore() {
        return currentStore;
    }

    /**
     * @return the cartDetails
     */
    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    /**
     * @param cartDetails the cartDetails to set
     */
    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

}
