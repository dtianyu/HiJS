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
@Table(name = "systemsetting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemSetting.getRowCount", query = "SELECT count(s) FROM SystemSetting s"),
    @NamedQuery(name = "SystemSetting.findAll", query = "SELECT s FROM SystemSetting s"),
    @NamedQuery(name = "SystemSetting.findById", query = "SELECT s FROM SystemSetting s WHERE s.id = :id")})
public class SystemSetting extends BaseOperateEntity {

    @Size(min = 1, max = 50)
    @Column(name = "website")
    private String website;
    @Size(min = 1, max = 50)
    @Column(name = "weburl")
    private String weburl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "webpath")
    private String webpath;

    public SystemSetting() {
    }

    public SystemSetting(Integer id) {
        this.id = id;
    }

    public SystemSetting(Integer id, String webapppath) {
        this.id = id;
        this.webpath = webapppath;
    }

    public String getWebpath() {
        return webpath;
    }

    public void setWebpath(String webpath) {
        this.webpath = webpath;
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
        if (!(object instanceof SystemSetting)) {
            return false;
        }
        SystemSetting other = (SystemSetting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.SystemSetting[ id=" + id + " ]";
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the weburl
     */
    public String getWeburl() {
        return weburl;
    }

    /**
     * @param weburl the weburl to set
     */
    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

}
