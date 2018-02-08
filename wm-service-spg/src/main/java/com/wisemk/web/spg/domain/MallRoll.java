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
 * The persistent class for the mall_roll database table.
 * 
 */
@Entity
@Table(name = "mall_roll")
@NamedQuery(name = "MallRoll.findAll", query = "SELECT m FROM MallRoll m")
public class MallRoll implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "asset_id", nullable = false)
    private int               assetId;

    @Column(name = "landing_info", length = 512)
    private String            landingInfo;

    @Column(name = "landing_type", nullable = false, length = 32)
    private String            landingType;

    @Column(name = "mall_id", nullable = false)
    private int               mallId;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false)
    private int               sort;

    @Column(nullable = false)
    private int               state;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public MallRoll()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAssetId()
    {
        return this.assetId;
    }

    public void setAssetId(int assetId)
    {
        this.assetId = assetId;
    }

    public String getLandingInfo()
    {
        return this.landingInfo;
    }

    public void setLandingInfo(String landingInfo)
    {
        this.landingInfo = landingInfo;
    }

    public String getLandingType()
    {
        return this.landingType;
    }

    public void setLandingType(String landingType)
    {
        this.landingType = landingType;
    }

    public int getMallId()
    {
        return this.mallId;
    }

    public void setMallId(int mallId)
    {
        this.mallId = mallId;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public int getSort()
    {
        return this.sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public int getState()
    {
        return this.state;
    }

    public void setState(int state)
    {
        this.state = state;
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