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
 * The persistent class for the track_detail database table.
 * 
 */
@Entity
@Table(name = "track_detail")
@NamedQuery(name = "TrackDetail.findAll", query = "SELECT t FROM TrackDetail t")
public class TrackDetail implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false)
    private int               adid;

    @Column(nullable = false)
    private int               aid;

    @Column(length = 64)
    private String            city;

    @Column(length = 64)
    private String            country;

    @Column(length = 15)
    private String            ip;

    @Column(name = "is_click", nullable = false)
    private int               isClick;

    @Column(length = 64)
    private String            os;

    @Column(length = 64)
    private String            province;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public TrackDetail()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAdid()
    {
        return this.adid;
    }

    public void setAdid(int adid)
    {
        this.adid = adid;
    }

    public int getAid()
    {
        return this.aid;
    }

    public void setAid(int aid)
    {
        this.aid = aid;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getIp()
    {
        return this.ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public int getIsClick()
    {
        return this.isClick;
    }

    public void setIsClick(int isClick)
    {
        this.isClick = isClick;
    }

    public String getOs()
    {
        return this.os;
    }

    public void setOs(String os)
    {
        this.os = os;
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

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

}