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
 * The persistent class for the order_good database table.
 * 
 */
@Entity
@Table(name = "order_good")
@NamedQuery(name = "OrderGood.findAll", query = "SELECT o FROM OrderGood o")
public class OrderGood implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "good_id", nullable = false)
    private int               goodId;

    @Column(name = "oder_id", nullable = false)
    private int               oderId;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false, length = 128)
    private String            remark;

    @Column(name = "sale_num", nullable = false, precision = 10, scale = 2)
    private BigDecimal        saleNum;

    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        salePrice;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public OrderGood()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getGoodId()
    {
        return this.goodId;
    }

    public void setGoodId(int goodId)
    {
        this.goodId = goodId;
    }

    public int getOderId()
    {
        return this.oderId;
    }

    public void setOderId(int oderId)
    {
        this.oderId = oderId;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public String getRemark()
    {
        return this.remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public BigDecimal getSaleNum()
    {
        return this.saleNum;
    }

    public void setSaleNum(BigDecimal saleNum)
    {
        this.saleNum = saleNum;
    }

    public BigDecimal getSalePrice()
    {
        return this.salePrice;
    }

    public void setSalePrice(BigDecimal salePrice)
    {
        this.salePrice = salePrice;
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