/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.ejb;

import com.jinshanlife.comm.SuperEJB;
import com.jinshanlife.entity.Weblink;
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
public class WeblinkBean extends SuperEJB<Weblink> {

    public WeblinkBean() {
        this.className = "Weblink";
    }

    public List<Weblink> findByLogo2() {
        Query query;
        query = em.createNamedQuery("Weblink.findByLogo2");
        return query.getResultList();
    }

    public List<Weblink> findByLogo2(int first, int pageSize) {
        Query query;
        query = em.createNamedQuery("Weblink.findByLogo2").setFirstResult(first).setMaxResults(pageSize);
        return query.getResultList();
    }
}
