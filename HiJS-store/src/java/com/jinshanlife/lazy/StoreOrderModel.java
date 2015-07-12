/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.control.UserManagedBean;
import com.jinshanlife.ejb.CartBean;
import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.entity.Cart;
import com.jinshanlife.entity.Store;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class StoreOrderModel extends BaseModel<Cart> {

    private CartBean cartBean;
    private StoreBean storeBean;
    private Store store;

    public StoreOrderModel(CartBean cartBean, StoreBean storeBean, UserManagedBean userManagedBean) {
        this.superEJB = cartBean;
        this.userManagedBean = userManagedBean;
        this.cartBean = cartBean;
        this.storeBean = storeBean;
    }

    @Override
    public List<Cart> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        store = storeBean.findByUserId(userManagedBean.getCurrentUser().getId()).get(0);
        if (store != null) {
            setDataList(cartBean.findByStoreId(store.getId(), first, pageSize));
            setRowCount(cartBean.getRowCountByStoreId(store.getId()));
        }
        return this.dataList;
    }

}
