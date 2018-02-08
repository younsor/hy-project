package com.wisemk.web.spg.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * The persistent class for the user_address database table.
 * 
 */
@Entity
@Table(name = "user_address")
@NamedQuery(name = "UserAddress.findAll", query = "SELECT u FROM UserAddress u")
public class UserAddress implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false, length = 512)
    private String            address;

    @Column(nullable = false, length = 64)
    private String            city;

    @Column(nullable = false, length = 32)
    private String            contact;

    @Column(name = "default", nullable = false)
    private int               default_;

    @Column(nullable = false, length = 64)
    private String            district;

    @Column(nullable = false, length = 64)
    private String            province;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false, length = 32)
    private String            tel;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    @Column(name = "user_id", nullable = false)
    private int               userId;

    public UserAddress()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getContact()
    {
        return this.contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public int getDefault_()
    {
        return this.default_;
    }

    public void setDefault_(int default_)
    {
        this.default_ = default_;
    }

    public String getDistrict()
    {
        return this.district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getProvince()
    {
        return this.province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public String getTel()
    {
        return this.tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

    public int getUserId()
    {
        return this.userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

}