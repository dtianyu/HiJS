/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.entity;

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
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "systemuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemUser.getRowCount", query = "SELECT count(s) FROM SystemUser s"),
    @NamedQuery(name = "SystemUser.getRowCountByUserId", query = "SELECT count(s) FROM SystemUser s WHERE s.id= :userid"),
    @NamedQuery(name = "SystemUser.findAll", query = "SELECT s FROM SystemUser s"),
    @NamedQuery(name = "SystemUser.findById", query = "SELECT s FROM SystemUser s WHERE s.userid = :id"),
    @NamedQuery(name = "SystemUser.findByIdAndPwd", query = "SELECT s FROM SystemUser s WHERE (s.userid = :id OR s.email =:email) AND s.password = :pwd"),
    @NamedQuery(name = "SystemUser.findByMailAdd", query = "SELECT s FROM SystemUser s WHERE s.email = :email"),
    @NamedQuery(name = "SystemUser.findByStatus", query = "SELECT s FROM SystemUser s WHERE s.status = :status"),
    @NamedQuery(name = "SystemUser.findByUserId", query = "SELECT s FROM SystemUser s WHERE s.id = :userid"),})
public class SystemUser extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "userid")
    private String userid;
    @Basic(optional = false)
    @NotEmpty
    @Size(min = 1, max = 16)
    @Column(name = "username")
    private String username;
    //@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "电子邮件无效")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "password")
    private String password;
    @Column(name = "superuser")
    private Boolean superuser;
    @Column(name = "ownstore")
    private Boolean ownstore;
    @Column(name = "locked")
    private Boolean locked;
    @Column(name = "lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastlogin;

    public SystemUser() {
    }

    public SystemUser(Integer id) {
        this.id = id;
    }

    public SystemUser(String userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Boolean superuser) {
        this.superuser = superuser;
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
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jinshanlife.entity.SystemUser[ id=" + id + " ]";
    }

    /**
     * @return the ownstore
     */
    public Boolean getOwnstore() {
        return ownstore;
    }

    /**
     * @param ownstore the ownstore to set
     */
    public void setOwnstore(Boolean ownstore) {
        this.ownstore = ownstore;
    }

    /**
     * @return the locked
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * @param locked the locked to set
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * @return the lastlogin
     */
    public Date getLastlogin() {
        return lastlogin;
    }

    /**
     * @param lastlogin the lastlogin to set
     */
    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

}
