/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.query;

import com.jinshanlife.entity.StoreKind;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@RequestScoped
public class StoreKindQueryBean {

    private List<StoreKind> dataList;

    /**
     * Creates a new instance of StoreKindQueryBean
     */
    public StoreKindQueryBean() {
        dataList = new ArrayList<>();
        dataList.add(new StoreKind(10,"美食"));
        dataList.add(new StoreKind(20,"求帮"));
        dataList.add(new StoreKind(30,"爱教"));
        dataList.add(new StoreKind(50,"修车"));
    }

    /**
     * @return the dataList
     */
    public List<StoreKind> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<StoreKind> dataList) {
        this.dataList = dataList;
    }


}
