/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@Entity
@Table(name = "weblink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WebShortcut.findAll", query = "SELECT w FROM WebShortcut w"),
    @NamedQuery(name = "WebShortcut.findById", query = "SELECT w FROM WebShortcut w WHERE w.id = :id"),
    @NamedQuery(name = "WebShortcut.findByName", query = "SELECT w FROM WebShortcut w WHERE w.name = :name"),
    @NamedQuery(name = "WebShortcut.findByUrl", query = "SELECT w FROM WebShortcut w WHERE w.url = :url"),
    @NamedQuery(name = "WebShortcut.findByLogo1", query = "SELECT w FROM WebShortcut w WHERE w.logo1 = :logo1"),
    @NamedQuery(name = "WebShortcut.findByLogo2", query = "SELECT w FROM WebShortcut w WHERE w.logo2 = :logo2"),
    @NamedQuery(name = "WebShortcut.findByIdx", query = "SELECT w FROM WebShortcut w WHERE w.idx = :idx"),
    @NamedQuery(name = "WebShortcut.findByStatus", query = "SELECT w FROM WebShortcut w WHERE w.status = :status"),
    @NamedQuery(name = "WebShortcut.findByCreator", query = "SELECT w FROM WebShortcut w WHERE w.creator = :creator"),
    @NamedQuery(name = "WebShortcut.findByCredate", query = "SELECT w FROM WebShortcut w WHERE w.credate = :credate"),
    @NamedQuery(name = "WebShortcut.findByOptuser", query = "SELECT w FROM WebShortcut w WHERE w.optuser = :optuser"),
    @NamedQuery(name = "WebShortcut.findByOptdate", query = "SELECT w FROM WebShortcut w WHERE w.optdate = :optdate"),
    @NamedQuery(name = "WebShortcut.findByCfmuser", query = "SELECT w FROM WebShortcut w WHERE w.cfmuser = :cfmuser"),
    @NamedQuery(name = "WebShortcut.findByCfmdate", query = "SELECT w FROM WebShortcut w WHERE w.cfmdate = :cfmdate")})
public class WebLink implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 45)
    @Column(name = "creator")
    private String creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @Size(max = 45)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @Size(max = 45)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;

    public WebLink() {
    }

    public WebLink(Integer id) {
        this.id = id;
    }

    public WebLink(Integer id, String name, String url, int idx, String status) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.idx = idx;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
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
        if (!(object instanceof WebLink)) {
            return false;
        }
        WebLink other = (WebLink) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.WebShortcut[ id=" + id + " ]";
    }
    
}
