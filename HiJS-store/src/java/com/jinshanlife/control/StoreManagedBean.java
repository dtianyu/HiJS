/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.StoreBean;
import com.jinshanlife.entity.Store;
import com.jinshanlife.lazy.StoreModel;
import com.jinshanlife.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "storeManagedBean")
@SessionScoped
public class StoreManagedBean extends SuperOperateBean<Store> {

    @EJB
    private StoreBean sessionBean;

    /**
     * Creates a new instance of StoreManagedBean
     */
    public StoreManagedBean() {
        super(Store.class);
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new StoreModel(sessionBean));
        if (currentEntity==null){
            setCurrentEntity(getNewEntity());
        }
    }

    @Override
    public String viewDetail(Store entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the sessionBean
     */
    public StoreBean getSessionBean() {
        return sessionBean;
    }

    /**
     * @param sessionBean the sessionBean to set
     */
    public void setSessionBean(StoreBean sessionBean) {
        this.sessionBean = sessionBean;
    }

}
