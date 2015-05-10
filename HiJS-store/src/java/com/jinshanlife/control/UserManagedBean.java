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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "用户名或密码错误"));
        }
        try {
            secpwd = Lib.securityMD5(getPwd());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            SystemUser u = systemUserBean.getByIdAndPwd(getUserid(), getSecpwd());
            if (u != null) {
                setCurrentUser(u);
                setStatus(true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "用户名或密码错误"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("异常", "用户名或密码不正确！"));
            setStatus(false);
            return "login";
        }
        return "index";
    }

    public String logout() {
        if (status) {
            setCurrentUser(null);
            setStatus(false);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            return "index";
        } else {
            return "index";
        }
    }

    public void updatePwd() {
        try {
            secpwd = Lib.securityMD5(pwd);
            currentUser.setPassword(secpwd);
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("异常", e.getMessage()));
        }
        try {
            systemUserBean.update(currentUser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("消息", "更新成功"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("异常", e.getMessage()));
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

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(SystemUser currentUser) {
        this.currentUser = currentUser;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
