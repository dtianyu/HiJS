/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.SystemUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SystemUserBean extends SuperEJB<SystemUser> {
    
    public SystemUserBean(){
        this.className="SystemUser";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method 
    public SystemUser getByIdAndPwd(String id, String pwd) {
        Query query = em.createNamedQuery("SystemUser.findByIdAndPwd");
        query.setParameter("id", id);
        query.setParameter("pwd", pwd);
        try {
            return (SystemUser) query.getSingleResult();
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }
      
}
