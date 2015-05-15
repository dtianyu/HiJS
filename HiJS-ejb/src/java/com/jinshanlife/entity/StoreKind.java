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
 * @author C0160
 */
@Entity
@Table(name = "storekind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreKind.getRowCount", query = "SELECT count(s) FROM StoreKind s"),
    @NamedQuery(name = "StoreKind.findAll", query = "SELECT s FROM StoreKind s"),
    @NamedQuery(name = "StoreKind.findById", query = "SELECT s FROM StoreKind s WHERE s.id = :id"),
    @NamedQuery(name = "StoreKind.findByName", query = "SELECT s FROM StoreKind s WHERE s.name = :name"),
    @NamedQuery(name = "StoreKind.findByStatus", query = "SELECT s FROM StoreKind s WHERE s.status = :status"),
    @NamedQuery(name = "StoreKind.findByCreator", query = "SELECT s FROM StoreKind s WHERE s.creator = :creator"),
    @NamedQuery(name = "StoreKind.findByCredate", query = "SELECT s FROM StoreKind s WHERE s.credate = :credate"),
    @NamedQuery(name = "StoreKind.findByOptuser", query = "SELECT s FROM StoreKind s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "StoreKind.findByOptdate", query = "SELECT s FROM StoreKind s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "StoreKind.findByCfmuser", query = "SELECT s FROM StoreKind s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "StoreKind.findByCfmdate", query = "SELECT s FROM StoreKind s WHERE s.cfmdate = :cfmdate")})
public class StoreKind extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "name")
    private String name;

    public StoreKind() {
    }

    public StoreKind(Integer id) {
        this.id = id;
    }

    public StoreKind(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof StoreKind)) {
            return false;
        }
        StoreKind other = (StoreKind) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.StoreKind[ id=" + id + " ]";
    }

}
