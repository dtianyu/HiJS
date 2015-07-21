/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.control.StoreManagedBean;
import com.jinshanlife.control.UserManagedBean;
import com.jinshanlife.ejb.ItemCategoryBean;
import com.jinshanlife.entity.ItemCategory;
import com.jinshanlife.entity.Store;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ItemCategoryModel extends BaseModel<ItemCategory> {

    private Store store;
    private ItemCategoryBean itemCategoryBean;

    public ItemCategoryModel(ItemCategoryBean superEJB, UserManagedBean userManagedBean, Store store) {
        this.superEJB = superEJB;
        this.userManagedBean = userManagedBean;
        this.itemCategoryBean = superEJB;
        this.store = store;
    }

    @Override
    public List<ItemCategory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (userManagedBean != null) {
            if (userManagedBean.getCurrentUser().getSuperuser()) {
                setDataList(superEJB.findAll(first, pageSize));
                setRowCount(superEJB.getRowCount());
            } else {
                setDataList(itemCategoryBean.findByStoreId(store.getId(), first, pageSize));
                setRowCount(itemCategoryBean.getRowCountByStoreId(store.getId()));
            }
        }
        return this.dataList;
    }

}
