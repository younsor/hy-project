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
 * The persistent class for the prom_clk_asset database table.
 * 
 */
@Entity
@Table(name = "prom_clk_asset")
@NamedQuery(name = "PromClkAsset.findAll", query = "SELECT p FROM PromClkAsset p")
public class PromClkAsset implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "cdn_path", length = 512)
    private String            cdnPath;

    @Column(name = "is_del", nullable = false)
    private int               isDel;

    private int               kbytes;

    @Column(name = "key_name", nullable = false, length = 32)
    private String            keyName;

    @Column(name = "local_path", length = 512)
    private String            localPath;

    @Column(length = 32)
    private String            mime;

    @Column(name = "prom_clk_layout_elem_id", nullable = false)
    private int               promClkLayoutElemId;

    @Column(name = "prom_creative_id", nullable = false)
    private int               promCreativeId;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    private int               size;

    @Column(name = "size_h")
    private int               sizeH;

    @Column(name = "size_w")
    private int               sizeW;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public PromClkAsset()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCdnPath()
    {
        return this.cdnPath;
    }

    public void setCdnPath(String cdnPath)
    {
        this.cdnPath = cdnPath;
    }

    public int getIsDel()
    {
        return this.isDel;
    }

    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
    }

    public int getKbytes()
    {
        return this.kbytes;
    }

    public void setKbytes(int kbytes)
    {
        this.kbytes = kbytes;
    }

    public String getKeyName()
    {
        return this.keyName;
    }

    public void setKeyName(String keyName)
    {
        this.keyName = keyName;
    }

    public String getLocalPath()
    {
        return this.localPath;
    }

    public void setLocalPath(String localPath)
    {
        this.localPath = localPath;
    }

    public String getMime()
    {
        return this.mime;
    }

    public void setMime(String mime)
    {
        this.mime = mime;
    }

    public int getPromClkLayoutElemId()
    {
        return this.promClkLayoutElemId;
    }

    public void setPromClkLayoutElemId(int promClkLayoutElemId)
    {
        this.promClkLayoutElemId = promClkLayoutElemId;
    }

    public int getPromCreativeId()
    {
        return this.promCreativeId;
    }

    public void setPromCreativeId(int promCreativeId)
    {
        this.promCreativeId = promCreativeId;
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

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

}