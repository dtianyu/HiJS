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
@Table(name = "itemcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemCategory.getRowCount", query = "SELECT count(i) FROM ItemCategory i"),
    @NamedQuery(name = "ItemCategory.getRowCountByStoreId", query = "SELECT count(i) FROM ItemCategory i WHERE i.storeid = :storeid "),
    @NamedQuery(name = "ItemCategory.findAll", query = "SELECT i FROM ItemCategory i"),
    @NamedQuery(name = "ItemCategory.findById", query = "SELECT i FROM ItemCategory i WHERE i.id = :id"),
    @NamedQuery(name = "ItemCategory.findByStoreId", query = "SELECT i FROM ItemCategory i WHERE i.storeid = :storeid"),
    @NamedQuery(name = "ItemCategory.findByCategory", query = "SELECT i FROM ItemCategory i WHERE i.category = :category"),
    @NamedQuery(name = "ItemCategory.findByStatus", query = "SELECT i FROM ItemCategory i WHERE i.status = :status")
})
public class ItemCategory extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "category")
    private String category;
    @Column(name = "itemcount")
    private Integer itemcount;

    public ItemCategory() {
    }

    public ItemCategory(int storeid, String category, String status) {
        this.storeid = storeid;
        this.category = category;
        this.status = status;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getItemcount() {
        return itemcount;
    }

    public void setItemcount(Integer itemcount) {
        this.itemcount = itemcount;
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
        if (!(object instanceof ItemCategory)) {
            return false;
        }
        ItemCategory other = (ItemCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.ItemCategory[ id=" + id + " ]";
    }

}
