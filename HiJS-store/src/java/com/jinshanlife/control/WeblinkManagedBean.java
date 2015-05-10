/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.ejb.WeblinkBean;
import com.jinshanlife.entity.Weblink;
import com.jinshanlife.lazy.WeblinkModel;
import com.jinshanlife.web.SuperOperateBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "weblinkManagedBean")
@SessionScoped
public class WeblinkManagedBean extends SuperOperateBean<Weblink> {

    @EJB
    private WeblinkBean sessionBean;

    /**
     * Creates a new instance of WeblinkManagedBean
     */
    public WeblinkManagedBean() {
        super(Weblink.class);
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new WeblinkModel(sessionBean));
    }

    @Override
    public String viewDetail(Weblink entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the sessionBean
     */
    public WeblinkBean getWeblinkBean() {
        return sessionBean;
    }

    /**
     * @param storeBean the sessionBean to set
     */
    public void setWeblinkBean(WeblinkBean storeBean) {
        this.sessionBean = storeBean;
    }

}
