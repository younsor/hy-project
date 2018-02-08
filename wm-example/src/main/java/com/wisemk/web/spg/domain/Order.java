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
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name = "order")
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
public class Order implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false)
    private int               del;

    @Column(name = "freight_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        freightPrice;

    @Column(name = "good_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        goodPrice;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false, length = 128)
    private String            remark;

    @Column(name = "show_id", nullable = false, length = 32)
    private String            showId;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        totalPrice;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    @Column(name = "user_address_id", nullable = false)
    private int               userAddressId;

    @Column(name = "user_id", nullable = false)
    private int               userId;

    public Order()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getDel()
    {
        return this.del;
    }

    public void setDel(int del)
    {
        this.del = del;
    }

    public BigDecimal getFreightPrice()
    {
        return this.freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice)
    {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getGoodPrice()
    {
        return this.goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice)
    {
        this.goodPrice = goodPrice;
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

    public String getShowId()
    {
        return this.showId;
    }

    public void setShowId(String showId)
    {
        this.showId = showId;
    }

    public BigDecimal getTotalPrice()
    {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

    public int getUserAddressId()
    {
        return this.userAddressId;
    }

    public void setUserAddressId(int userAddressId)
    {
        this.userAddressId = userAddressId;
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