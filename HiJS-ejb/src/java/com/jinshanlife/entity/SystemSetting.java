/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "webapppath")
    private String webapppath;

    public SystemSetting() {
    }

    public SystemSetting(Integer id) {
        this.id = id;
    }

    public SystemSetting(Integer id, String webapppath) {
        this.id = id;
        this.webapppath = webapppath;
    }

    public String getWebapppath() {
        return webapppath;
    }

    public void setWebapppath(String webapppath) {
        this.webapppath = webapppath;
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
    
}
