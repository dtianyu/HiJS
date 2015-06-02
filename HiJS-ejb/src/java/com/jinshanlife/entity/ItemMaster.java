/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.entity;

import com.jinshanlife.entity.BaseOperateEntity;
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
 * @author C0160
 */
@Entity
@Table(name = "itemmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemMaster.getRowCount", query = "SELECT count(i) FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.getRowCountByUserId", query = "SELECT count(i) FROM ItemMaster i WHERE i.userid = :userid "),
    @NamedQuery(name = "ItemMaster.findAll", query = "SELECT i FROM ItemMaster i ORDER BY i.idx DESC"),
    @NamedQuery(name = "ItemMaster.findById", query = "SELECT i FROM ItemMaster i WHERE i.id = :id"),
    @NamedQuery(name = "ItemMaster.findByStoreId", query = "SELECT i FROM ItemMaster i WHERE i.storeid = :storeid ORDER BY i.idx DESC"),
    @NamedQuery(name = "ItemMaster.findByUserId", query = "SELECT i FROM ItemMaster i WHERE i.userid = :userid ORDER BY i.idx DESC"),
    @NamedQuery(name = "ItemMaster.findByKind", query = "SELECT i FROM ItemMaster i WHERE i.kind = :kind ORDER BY i.idx DESC "),
    @NamedQuery(name = "ItemMaster.findByItemno", query = "SELECT i FROM ItemMaster i WHERE i.itemno = :itemno"),
    @NamedQuery(name = "ItemMaster.findByItemdesc", query = "SELECT i FROM ItemMaster i WHERE i.itemdesc = :itemdesc"),
    @NamedQuery(name = "ItemMaster.findByItemspec", query = "SELECT i FROM ItemMaster i WHERE i.itemspec = :itemspec"),
    @NamedQuery(name = "ItemMaster.findByItemproperty", query = "SELECT i FROM ItemMaster i WHERE i.itemproperty = :itemproperty"),
    @NamedQuery(name = "ItemMaster.findByStatus", query = "SELECT i FROM ItemMaster i WHERE i.status = :status")})
public class ItemMaster extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;
    @Column(name = "kind")
    private Integer kind;
    @Size(max = 20)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemdesc")
    private String itemdesc;
    @Size(max = 200)
    @Column(name = "itemspec")
    private String itemspec;
    @Size(max = 45)
    @Column(name = "brand")
    private String brand;
    @Size(max = 45)
    @Column(name = "batch")
    private String batch;
    @Size(max = 45)
    @Column(name = "sn")
    private String sn;
    @Size(max = 45)
    @Column(name = "itemproperty")
    private String itemproperty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unit")
    private String unit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "disc")
    private BigDecimal disc;
    @Column(name = "hot")
    private Integer hot;
    @Column(name = "idx")
    private Integer idx;
    @Size(max = 45)
    @Column(name = "logo1")
    private String logo1;
    @Size(max = 45)
    @Column(name = "logo2")
    private String logo2;

    public ItemMaster() {
    }

    public ItemMaster(Integer id) {
        this.id = id;
    }

    public ItemMaster(Integer id, int storeid, int userid, String itemdesc, String unit, BigDecimal price, String status) {
        this.id = id;
        this.storeid = storeid;
        this.userid = userid;
        this.itemdesc = itemdesc;
        this.unit = unit;
        this.price = price;
        this.status = status;
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

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemspec() {
        return itemspec;
    }

    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    public String getItemproperty() {
        return itemproperty;
    }

    public void setItemproperty(String itemproperty) {
        this.itemproperty = itemproperty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDisc() {
        return disc;
    }

    public void setDisc(BigDecimal disc) {
        this.disc = disc;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemMaster)) {
            return false;
        }
        ItemMaster other = (ItemMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.ejb.ItemMaster[ id=" + id + " ]";
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the batch
     */
    public String getBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

}
