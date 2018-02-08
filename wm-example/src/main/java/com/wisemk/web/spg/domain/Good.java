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
 * The persistent class for the good database table.
 * 
 */
@Entity
@Table(name = "good")
@NamedQuery(name = "Good.findAll", query = "SELECT g FROM Good g")
public class Good implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "actual_sale_num", nullable = false, precision = 10, scale = 2)
    private BigDecimal        actualSaleNum;

    @Column(name = "asset_id", nullable = false)
    private int               assetId;

    @Column(nullable = false, length = 128)
    private String            description;

    @Column(name = "detail_id", nullable = false)
    private int               detailId;

    @Column(name = "detail_type", nullable = false)
    private int               detailType;

    @Column(name = "freight_id", nullable = false)
    private int               freightId;

    @Column(name = "mall_id", nullable = false)
    private int               mallId;

    @Column(nullable = false, length = 128)
    private String            name;

    @Column(name = "origin_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        originPrice;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal        salePrice;

    @Column(nullable = false)
    private int               sort;

    @Column(name = "stock_num", nullable = false, precision = 10, scale = 2)
    private BigDecimal        stockNum;

    @Column(name = "subject_id", nullable = false)
    private int               subjectId;

    @Column(nullable = false)
    private int               type;

    @Column(nullable = false)
    private int               unit;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    @Column(name = "virtual_sale_num", nullable = false, precision = 10, scale = 2)
    private BigDecimal        virtualSaleNum;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal        weight;

    public Good()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public BigDecimal getActualSaleNum()
    {
        return this.actualSaleNum;
    }

    public void setActualSaleNum(BigDecimal actualSaleNum)
    {
        this.actualSaleNum = actualSaleNum;
    }

    public int getAssetId()
    {
        return this.assetId;
    }

    public void setAssetId(int assetId)
    {
        this.assetId = assetId;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getDetailId()
    {
        return this.detailId;
    }

    public void setDetailId(int detailId)
    {
        this.detailId = detailId;
    }

    public int getDetailType()
    {
        return this.detailType;
    }

    public void setDetailType(int detailType)
    {
        this.detailType = detailType;
    }

    public int getFreightId()
    {
        return this.freightId;
    }

    public void setFreightId(int freightId)
    {
        this.freightId = freightId;
    }

    public int getMallId()
    {
        return this.mallId;
    }

    public void setMallId(int mallId)
    {
        this.mallId = mallId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getOriginPrice()
    {
        return this.originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice)
    {
        this.originPrice = originPrice;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public BigDecimal getSalePrice()
    {
        return this.salePrice;
    }

    public void setSalePrice(BigDecimal salePrice)
    {
        this.salePrice = salePrice;
    }

    public int getSort()
    {
        return this.sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public BigDecimal getStockNum()
    {
        return this.stockNum;
    }

    public void setStockNum(BigDecimal stockNum)
    {
        this.stockNum = stockNum;
    }

    public int getSubjectId()
    {
        return this.subjectId;
    }

    public void setSubjectId(int subjectId)
    {
        this.subjectId = subjectId;
    }

    public int getType()
    {
        return this.type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getUnit()
    {
        return this.unit;
    }

    public void setUnit(int unit)
    {
        this.unit = unit;
    }

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

    public BigDecimal getVirtualSaleNum()
    {
        return this.virtualSaleNum;
    }

    public void setVirtualSaleNum(BigDecimal virtualSaleNum)
    {
        this.virtualSaleNum = virtualSaleNum;
    }

    public BigDecimal getWeight()
    {
        return this.weight;
    }

    public void setWeight(BigDecimal weight)
    {
        this.weight = weight;
    }

}