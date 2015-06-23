/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.entity;

import java.math.BigDecimal;
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
@Table(name = "cartdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CartDetail.getRowCount", query = "SELECT count(c) FROM CartDetail c"),
    @NamedQuery(name = "CartDetail.getRowCountByUserId", query = "SELECT count(c) FROM CartDetail c WHERE c.userid = :userid"),
    @NamedQuery(name = "CartDetail.findAll", query = "SELECT c FROM CartDetail c"),
    @NamedQuery(name = "CartDetail.findById", query = "SELECT c FROM CartDetail c WHERE c.id = :id"),
    @NamedQuery(name = "CartDetail.findByCartId", query = "SELECT c FROM CartDetail c WHERE c.cartid = :cartid"),
    @NamedQuery(name = "CartDetail.findByStoreId", query = "SELECT c FROM CartDetail c WHERE c.storeid = :storeid"),
    @NamedQuery(name = "CartDetail.findByUserId", query = "SELECT c FROM CartDetail c WHERE c.userid = :userid"),
    @NamedQuery(name = "CartDetail.findByItemId", query = "SELECT c FROM CartDetail c WHERE c.itemid = :itemid"),
    @NamedQuery(name = "CartDetail.findByContent", query = "SELECT c FROM CartDetail c WHERE c.content = :content"),
    @NamedQuery(name = "CartDetail.findByStatus", query = "SELECT c FROM CartDetail c WHERE c.status = :status")})
public class CartDetail extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cartid")
    private String cartid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemid")
    private int itemid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "content")
    private String content;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;
   

    public CartDetail() {
    }

    public CartDetail(String cartid, int storeid, int userid, int itemid, String content, BigDecimal price, int qty, BigDecimal amts, String status) {
        this.cartid = cartid;
        this.storeid = storeid;
        this.userid = userid;
        this.itemid = itemid;
        this.content = content;
        this.price = price;
        this.qty = qty;
        this.amts = amts;
        this.status = status;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (!(object instanceof CartDetail)) {
            return false;
        }
        CartDetail other = (CartDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.CartDetail[ id=" + id + " ]";
    }

}
