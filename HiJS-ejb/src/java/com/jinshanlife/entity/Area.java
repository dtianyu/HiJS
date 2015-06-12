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
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.getRowCount", query = "SELECT count(a) FROM Area a"),
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a ORDER BY a.idx DESC"),
    @NamedQuery(name = "Area.findById", query = "SELECT a FROM Area a WHERE a.id = :id"),
    @NamedQuery(name = "Area.findByArea", query = "SELECT a FROM Area a WHERE a.area = :area "),
    @NamedQuery(name = "Area.findByParent", query = "SELECT a FROM Area a WHERE a.parent = :parent ORDER BY a.idx DESC"),
    @NamedQuery(name = "Area.findByStorecount", query = "SELECT a FROM Area a WHERE a.storecount = :storecount"),
    @NamedQuery(name = "Area.findByRemark", query = "SELECT a FROM Area a WHERE a.remark = :remark"),
    @NamedQuery(name = "Area.findByStatus", query = "SELECT a FROM Area a WHERE a.status = :status ORDER BY a.status,a.idx DESC")})
public class Area extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "area")
    private String area;
    @Column(name = "parent")
    private Integer parent;
    @Column(name = "idx")
    private int idx;
    @Column(name = "storecount")
    private Integer storecount;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public Area() {
    }

    public Area(Integer id) {
        this.id = id;
    }

    public Area(Integer id, String area, String status) {
        this.id = id;
        this.area = area;
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getStorecount() {
        return storecount;
    }

    public void setStorecount(Integer storecount) {
        this.storecount = storecount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the idx
     */
    public int getIdx() {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

}
