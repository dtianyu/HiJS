/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.comm.Lib;
import com.jinshanlife.ejb.SystemUserBean;
import com.jinshanlife.entity.SystemUser;
import com.jinshanlife.lazy.SystemUserModel;
import com.jinshanlife.web.SuperOperateBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class SystemUserManagedBean extends SuperOperateBean<SystemUser> {

    @EJB
    private SystemUserBean sessionBean;

    private boolean skip;
    private String verifyCode;
    private String verifyInput;

    public SystemUserManagedBean() {
        super(SystemUser.class);
    }

    @Override
    public void create() {
        if (getNewEntity() == null) {
            SystemUser entity;
            try {
                entity = entityClass.newInstance();
                entity.setStatus("N");
                entity.setCredate(getDate());
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperOperateBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void init() {
        setSuperEJB(sessionBean);
        setModel(new SystemUserModel(sessionBean));
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            setSkip(false);   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void sendVerifyCode() {
        Integer code = (int) (Math.random() * 1000);
        verifyCode = code.toString();
        Lib.sendVerifyCode(verifyCode);
    }

    /**
     * @return the skip
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     * @param skip the skip to set
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     * @return the verifyInput
     */
    public String getVerifyInput() {
        return verifyInput;
    }

    /**
     * @param verifyInput the verifyInput to set
     */
    public void setVerifyInput(String verifyInput) {
        this.verifyInput = verifyInput;
    }

}
