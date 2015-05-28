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
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    private String mobile;
    private String username;
    private String mail;
    private String pwd;
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
                entity.setCreator("system");
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
        return event.getNewStep();
//        if (verifyCode.equals(verifyInput)) {
//            return event.getNewStep();
//        } else {
//            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("错误", "验证码错误"));
//        }
//        return  event.getOldStep();
    }

    public void sendVerifyCode() {
        if ((!mobile.isEmpty()) && (mobile.length() == 11)) {
            Integer code = (int) (Math.random() * 10000);
            verifyCode = code.toString();
            Lib.sendVerifyCode(mobile,verifyCode);
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("错误", "请输入手机号码"));
        }
    }

    @Override
    public void persist() {
        if (getNewEntity() != null) {
            try {
                newEntity.setUserid(mobile);
                newEntity.setUsername(username);
                newEntity.setEmail(mail);
                newEntity.setPassword(Lib.securityMD5(pwd));
                newEntity.setSuperuser(0);
                super.persist();
            } catch (UnsupportedEncodingException ex) {
                FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("错误", "更新失败"));
                Logger.getLogger(SystemUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

}
