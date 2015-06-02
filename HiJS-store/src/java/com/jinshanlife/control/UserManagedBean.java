/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.control;

import com.jinshanlife.comm.Lib;
import com.jinshanlife.ejb.SystemUserBean;
import com.jinshanlife.entity.SystemUser;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private SystemUserBean systemUserBean;

    private SystemUser currentUser;
    private String userid;
    private String pwd;
    private String secpwd;
    private boolean status;

    public UserManagedBean() {
        status = false;
    }

    public boolean checkUser() {
        return true;
    }

    public String login() {
        if (getUserid().length() == 0 || getPwd().length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "请输入用户名和密码"));
        }
        try {
            secpwd = Lib.securityMD5(getPwd());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            SystemUser u = systemUserBean.getByIdAndPwd(getUserid(), getSecpwd());
            if (u != null) {
                currentUser = u;
                status = true;
                updateLoginTime();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "用户名或密码错误"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "用户名或密码不正确！"));
            status = false;
            return "login";
        }
        return "home";
    }

    public String logout() {
        if (status) {
            currentUser = null;
            status = false;
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            return "login";
        } else {
            return "home";
        }
    }

    public void update() {
        if (currentUser != null) {
            systemUserBean.update(currentUser);
        }
    }

    public void updateLoginTime() {
        if (currentUser != null) {
            currentUser.setLastlogin(getDate());
            update();
        }
    }

    public void updatePwd() {
        try {
            secpwd = Lib.securityMD5(pwd);
            currentUser.setPassword(secpwd);
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal", e.getMessage()));
        }
        try {
            update();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Info", "更新密码成功"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Info", e.getMessage()));
        }
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @return the currentUser
     */
    public SystemUser getCurrentUser() {
        return currentUser;
    }

    public boolean getStatus() {
        return status;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
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
     * @return the secpwd
     */
    public String getSecpwd() {
        return secpwd;
    }

}
