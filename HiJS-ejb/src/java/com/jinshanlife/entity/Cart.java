/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.getRowCount", query = "SELECT count(c) FROM Cart c"),
    @NamedQuery(name = "Cart.getRowCountByUserId", query = "SELECT count(c) FROM Cart c WHERE c.userid = :userid"),
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findById", query = "SELECT c FROM Cart c WHERE c.id = :id"),
    @NamedQuery(name = "Cart.findByCartId", query = "SELECT c FROM Cart c WHERE c.cartid = :cartid"),
    @NamedQuery(name = "Cart.findByUserId", query = "SELECT c FROM Cart c WHERE c.userid = :userid"),
    @NamedQuery(name = "Cart.findByStatus", query = "SELECT c FROM Cart c WHERE c.status = :status")})
public class Cart extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "freight")
    private BigDecimal freight;

    @Column(name = "recdate")
    @Temporal(TemporalType.DATE)
    private Date recdate;
    @Column(name = "rectime")
    @Temporal(TemporalType.TIME)
    private Date rectime;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cartid")
    private String cartid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contacter")
    private String contacter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address")
    private String address;
    @Column(name = "remark")
    private String remark;

    public Cart() {
    }

    public Cart(Integer id) {
        this.id = id;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
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
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.ejb.Cart[ id=" + id + " ]";
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRecdate() {
        return recdate;
    }

    public void setRecdate(Date recdate) {
        this.recdate = recdate;
    }

    public Date getRectime() {
        return rectime;
    }

    public void setRectime(Date rectime) {
        this.rectime = rectime;
    }

    /**
     * @return the amts
     */
    public BigDecimal getAmts() {
        return amts;
    }

    /**
     * @param amts the amts to set
     */
    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    /**
     * @return the freight
     */
    public BigDecimal getFreight() {
        return freight;
    }

    /**
     * @param freight the freight to set
     */
    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

}
