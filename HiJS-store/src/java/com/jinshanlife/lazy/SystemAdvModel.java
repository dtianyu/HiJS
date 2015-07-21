/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.control.UserManagedBean;
import com.jinshanlife.entity.SystemAdv;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class SystemAdvModel extends BaseModel<SystemAdv> {

    public SystemAdvModel(SuperEJB superEJB, UserManagedBean userManagedBean) {
        this.superEJB = superEJB;
        this.userManagedBean = userManagedBean;
    }

    @Override
    public List<SystemAdv> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setDataList(superEJB.findAll());
        setRowCount(superEJB.getRowCount());
        return this.dataList;
    }

}
