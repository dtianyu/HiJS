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
    @NamedQuery(name = "StoreKind.findByStatus", query = "SELECT s FROM StoreKind s WHERE s.status = :status")})
public class StoreKind extends BaseOperateEntity {

    @Size(max = 20)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "classname")
    private String classname;
    @Column(name = "storecount")
    private Integer storecount;

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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
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

    /**
     * @return the storecount
     */
    public Integer getStorecount() {
        return storecount;
    }

    /**
     * @param storecount the storecount to set
     */
    public void setStorecount(Integer storecount) {
        this.storecount = storecount;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
