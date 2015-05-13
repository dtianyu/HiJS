/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.query;

import com.jinshanlife.entity.StoreKind;
import com.jinshanlife.web.SuperQueryBean;
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
public class StoreKindQueryBean extends SuperQueryBean<StoreKind>{

    public StoreKindQueryBean() {
        super(StoreKind.class);
    }

}
