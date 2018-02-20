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
 * The persistent class for the mall_asset database table.
 * 
 */
@Entity
@Table(name = "mall_asset")
@NamedQuery(name = "MallAsset.findAll", query = "SELECT m FROM MallAsset m")
public class MallAsset implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false)
    private int               attr;

    @Column(nullable = false)
    private int               del;

    @Column(length = 32)
    private String            format;

    private int               kbytes;

    @Column(name = "mall_id", nullable = false)
    private int               mallId;

    @Column(length = 32)
    private String            mime;

    @Column(nullable = false, length = 32)
    private String            name;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    private int               size;

    @Column(name = "size_h")
    private int               sizeH;

    @Column(name = "size_w")
    private int               sizeW;

    @Column(nullable = false)
    private int               type;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    @Column(nullable = false, length = 512)
    private String            value;

    public MallAsset()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAttr()
    {
        return this.attr;
    }

    public void setAttr(int attr)
    {
        this.attr = attr;
    }

    public int getDel()
    {
        return this.del;
    }

    public void setDel(int del)
    {
        this.del = del;
    }

    public String getFormat()
    {
        return this.format;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public int getKbytes()
    {
        return this.kbytes;
    }

    public void setKbytes(int kbytes)
    {
        this.kbytes = kbytes;
    }

    public int getMallId()
    {
        return this.mallId;
    }

    public void setMallId(int mallId)
    {
        this.mallId = mallId;
    }

    public String getMime()
    {
        return this.mime;
    }

    public void setMime(String mime)
    {
        this.mime = mime;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public int getSize()
    {
        return this.size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getSizeH()
    {
        return this.sizeH;
    }

    public void setSizeH(int sizeH)
    {
        this.sizeH = sizeH;
    }

    public int getSizeW()
    {
        return this.sizeW;
    }

    public void setSizeW(int sizeW)
    {
        this.sizeW = sizeW;
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

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}