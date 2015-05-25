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
 * @author C0160
 */
@Entity
@Table(name = "store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Store.getRowCount", query = "SELECT count(s) FROM Store s"),
    @NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s ORDER BY s.idx DESC "),
    @NamedQuery(name = "Store.findById", query = "SELECT s FROM Store s WHERE s.id = :id"),
    @NamedQuery(name = "Store.findByName", query = "SELECT s FROM Store s WHERE s.name = :name"),
    @NamedQuery(name = "Store.findByKind", query = "SELECT s FROM Store s WHERE s.kind = :kind  ORDER BY s.idx DESC "),
    @NamedQuery(name = "Store.findByTown", query = "SELECT s FROM Store s WHERE s.town = :town"),
    @NamedQuery(name = "Store.findByMobile", query = "SELECT s FROM Store s WHERE s.mobile = :mobile"),
    @NamedQuery(name = "Store.findByPhone", query = "SELECT s FROM Store s WHERE s.phone = :phone"),
    @NamedQuery(name = "Store.findByCategory", query = "SELECT s FROM Store s WHERE s.category = :category  ORDER BY s.idx DESC "),
    @NamedQuery(name = "Store.findByFeature", query = "SELECT s FROM Store s WHERE s.feature = :feature"),
    @NamedQuery(name = "Store.findByPcc", query = "SELECT s FROM Store s WHERE s.pcc = :pcc"),
    @NamedQuery(name = "Store.findByHot", query = "SELECT s FROM Store s WHERE s.hot = :hot"),
    @NamedQuery(name = "Store.findByIdx", query = "SELECT s FROM Store s WHERE s.idx = :idx"),
    @NamedQuery(name = "Store.findByStatus", query = "SELECT s FROM Store s WHERE s.status = :status")})
public class Store extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "kind")
    private int kind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "country")
    private String country;
    @Size(max = 20)
    @Column(name = "city")
    private String city;
    @Size(max = 20)
    @Column(name = "district")
    private String district;
    @Size(max = 20)
    @Column(name = "town")
    private String town;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "contacter")
    private String contacter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "category")
    private String category;
    @Size(max = 45)
    @Column(name = "feature")
    private String feature;
    @Size(max = 100)
    @Column(name = "action")
    private String action;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pcc")
    private BigDecimal pcc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hot")
    private int hot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idx")
    private int idx;
    @Size(max = 45)
    @Column(name = "logo1")
    private String logo1;
    @Size(max = 45)
    @Column(name = "logo2")
    private String logo2;

    public Store() {
    }

    public Store(Integer id) {
        this.id = id;
    }

    public Store(Integer id, Integer kind, String name, String address, String contactor, String mobile, String category, int hot, int idx, String status) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.address = address;
        this.contacter = contactor;
        this.mobile = mobile;
        this.category = category;
        this.hot = hot;
        this.idx = idx;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getPcc() {
        return pcc;
    }

    public void setPcc(BigDecimal pcc) {
        this.pcc = pcc;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
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
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.Store[ id=" + id + " ]";
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

}
