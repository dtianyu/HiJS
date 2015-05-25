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
@Table(name = "weblink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Weblink.getRowCount", query = "SELECT count(w) FROM Weblink w"),
    @NamedQuery(name = "Weblink.findAll", query = "SELECT w FROM Weblink w ORDER BY w.idx DESC "),
    @NamedQuery(name = "Weblink.findById", query = "SELECT w FROM Weblink w WHERE w.id = :id"),
    @NamedQuery(name = "Weblink.findByName", query = "SELECT w FROM Weblink w WHERE w.name = :name"),
    @NamedQuery(name = "Weblink.findByLogo2", query = "SELECT w FROM Weblink w WHERE w.logo2 IS NOT NULL AND w.logo2<>'' ORDER BY w.idx DESC "),
    @NamedQuery(name = "Weblink.findByStatus", query = "SELECT w FROM Weblink w WHERE w.status = :status")})
public class Weblink extends BaseOperateEntity {
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "url")
    private String url;
    @Size(max = 45)
    @Column(name = "logo1")
    private String logo1;
    @Size(max = 45)
    @Column(name = "logo2")
    private String logo2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idx")
    private int idx;

    public Weblink() {
    }

    public Weblink(Integer id) {
        this.id = id;
    }

    public Weblink(Integer id, String name, String url, int idx, String status) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.idx = idx;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo1() {
        return logo1;
    }

    public void setLogo1(String logo1) {
        this.logo1 = logo1;
    }

    public String getLogo2() {
        return logo2;
    }

    public void setLogo2(String logo2) {
        this.logo2 = logo2;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
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
        if (!(object instanceof Weblink)) {
            return false;
        }
        Weblink other = (Weblink) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.Weblink[ id=" + id + " ]";
    }

}
