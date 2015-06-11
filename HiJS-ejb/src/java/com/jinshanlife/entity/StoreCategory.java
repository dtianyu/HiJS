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
@Table(name = "storecategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreCategory.getRowCount", query = "SELECT count(s) FROM StoreCategory s"),
    @NamedQuery(name = "StoreCategory.findAll", query = "SELECT s FROM StoreCategory s"),
    @NamedQuery(name = "StoreCategory.findById", query = "SELECT s FROM StoreCategory s WHERE s.id = :id"),
    @NamedQuery(name = "StoreCategory.findByKind", query = "SELECT s FROM StoreCategory s WHERE s.kind = :kind"),
    @NamedQuery(name = "StoreCategory.findByCategory", query = "SELECT s FROM StoreCategory s WHERE s.category = :category"),
    @NamedQuery(name = "StoreCategory.findByStatus", query = "SELECT s FROM StoreCategory s WHERE s.status = :status")})
public class StoreCategory extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "kind")
    private int kind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "category")
    private String category;

    public StoreCategory() {
    }

    public StoreCategory(Integer id) {
        this.id = id;
    }

    public StoreCategory(Integer id, int kind, String category, String status) {
        this.id = id;
        this.kind = kind;
        this.category = category;
        this.status = status;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
