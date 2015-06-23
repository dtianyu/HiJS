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
import javax.validation.constraints.Pattern;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class SystemUserManagedBean extends SuperOperateBean<SystemUser> {

    @EJB
    private SystemUserBean systemUserBean;
    private String mobile;
    private String username;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "电子邮件无效")
    private String mail;
    private String pwd;
    private String verifyCode;
    private String verifyInput;
    private int count;//验证码发送次数

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
                entity.setOptuser(null);
                entity.setOptdate(null);
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperOperateBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected boolean doBeforePersist() {

        try {
            newEntity.setUserid(mobile);
            newEntity.setUsername(username);
            newEntity.setEmail(mail);
            newEntity.setPassword(Lib.securityMD5(pwd));
            newEntity.setSuperuser(Boolean.FALSE);
            newEntity.setOwnstore(Boolean.FALSE);
            newEntity.setLocked(Boolean.FALSE);
            return true;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SystemUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public void init() {
        setSuperEJB(systemUserBean);
        setModel(new SystemUserModel(systemUserBean, userManagedBean));
    }

    public String onFlowProcess(FlowEvent event) {
        if (verifyCode.equals(verifyInput)) {
            return event.getNewStep();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "验证码错误"));
        }
        return event.getOldStep();
    }

    public void sendVerifyCode() {
        if(count>3){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "已申请多次，稍后再试"));
            return;
        }
        if ((!mobile.isEmpty()) && (mobile.length() == 11)) {
            Integer code = (int) (Math.random() * 10000);
            verifyCode = code.toString();
            Lib.sendVerifyCode(mobile, verifyCode);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "验证码已发送"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "请输入手机号码"));
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
