/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "systemadv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemAdv.getRowCount", query = "SELECT count(s) FROM SystemAdv s"),
    @NamedQuery(name = "SystemAdv.findAll", query = "SELECT s FROM SystemAdv s"),
    @NamedQuery(name = "SystemAdv.findById", query = "SELECT s FROM SystemAdv s WHERE s.id = :id"),
    @NamedQuery(name = "SystemAdv.findByTitle", query = "SELECT s FROM SystemAdv s WHERE s.title = :title"),
    @NamedQuery(name = "SystemAdv.findByAdv", query = "SELECT s FROM SystemAdv s WHERE s.adv = :adv"),
    @NamedQuery(name = "SystemAdv.findByStatus", query = "SELECT s FROM SystemAdv s WHERE s.status = :status")})
public class SystemAdv extends BaseOperateEntity {

    @Size(max = 20)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adv")
    private String adv;

    public SystemAdv() {
    }

    public SystemAdv(String adv, String status) {
        this.adv = adv;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdv() {
        return adv;
    }

    public void setAdv(String adv) {
        this.adv = adv;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemAdv)) {
            return false;
        }
        SystemAdv other = (SystemAdv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.SystemAdv[ id=" + id + " ]";
    }

}
