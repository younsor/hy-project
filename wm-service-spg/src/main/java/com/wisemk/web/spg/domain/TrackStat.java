package com.wisemk.web.spg.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
 * The persistent class for the track_stats database table.
 * 
 */
@Entity
@Table(name = "track_stats")
@NamedQuery(name = "TrackStat.findAll", query = "SELECT t FROM TrackStat t")
public class TrackStat implements Serializable
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

    private BigInteger        click;

    private BigInteger        impress;

    @Column(nullable = false)
    private int               mid;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false)
    private Timestamp         ts;

    @Column(nullable = false)
    private int               uid;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public TrackStat()
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

    public BigInteger getClick()
    {
        return this.click;
    }

    public void setClick(BigInteger click)
    {
        this.click = click;
    }

    public BigInteger getImpress()
    {
        return this.impress;
    }

    public void setImpress(BigInteger impress)
    {
        this.impress = impress;
    }

    public int getMid()
    {
        return this.mid;
    }

    public void setMid(int mid)
    {
        this.mid = mid;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public Timestamp getTs()
    {
        return this.ts;
    }

    public void setTs(Timestamp ts)
    {
        this.ts = ts;
    }

    public int getUid()
    {
        return this.uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
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