/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.query;

import com.jinshanlife.ejb.StoreKindBean;
import com.jinshanlife.entity.StoreKind;
import com.jinshanlife.lazy.StoreKindModel;
import com.jinshanlife.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@RequestScoped
public class StoreKindQueryBean extends SuperQueryBean<StoreKind> {

    @EJB
    private StoreKindBean sessionBean;

    public StoreKindQueryBean() {
        super(StoreKind.class);
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new StoreKindModel(sessionBean));
        super.init();
    }

}
