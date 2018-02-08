package com.wisemk.web.spg.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the conf_freight database table.
 * 
 */
@Entity
@Table(name = "conf_freight")
@NamedQuery(name = "ConfFreight.findAll", query = "SELECT c FROM ConfFreight c")
public class ConfFreight implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "continue_num", nullable = false, precision = 10, scale = 2)
    private BigDecimal        continueNum;

    @Column(name = "continue_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        continuePrice;

    @Column(nullable = false, length = 32)
    private String            name;

    @Column(name = "origin_num", nullable = false, precision = 10, scale = 2)
    private BigDecimal        originNum;

    @Column(name = "origin_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        originPrice;

    @Column(nullable = false, length = 512)
    private String            province;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false)
    private int               type;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public ConfFreight()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public BigDecimal getContinueNum()
    {
        return this.continueNum;
    }

    public void setContinueNum(BigDecimal continueNum)
    {
        this.continueNum = continueNum;
    }

    public BigDecimal getContinuePrice()
    {
        return this.continuePrice;
    }

    public void setContinuePrice(BigDecimal continuePrice)
    {
        this.continuePrice = continuePrice;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getOriginNum()
    {
        return this.originNum;
    }

    public void setOriginNum(BigDecimal originNum)
    {
        this.originNum = originNum;
    }

    public BigDecimal getOriginPrice()
    {
        return this.originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice)
    {
        this.originPrice = originPrice;
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

    public int getType()
    {
        return this.type;
    }

    public void setType(int type)
    {
        this.type = type;
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