/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.lazy;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.BaseEntity;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong Ï
 */
public abstract class BaseModel extends LazyDataModel<BaseEntity> {

    protected SuperEJB sessionBean;
    private List<BaseEntity> dataList;

    @Override
    public void setRowIndex(int rowIndex) {
        /*
         * The following is in ancestor (LazyDataModel):
         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
         */
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    @Override
    public BaseEntity getRowData(String rowKey) {
        for (BaseEntity entity : dataList) {
            if (entity.getId().toString().equals(rowKey)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(BaseEntity entity) {
        return entity.getId();
    }

    @Override
    public List<BaseEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setDataList(sessionBean.findAll(first, pageSize));
        setRowCount(sessionBean.getRowCount());
        return this.dataList;
    }

    /**
     * @return the dataList
     */
    public List<BaseEntity> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<BaseEntity> dataList) {
        this.dataList = dataList;
    }

}
