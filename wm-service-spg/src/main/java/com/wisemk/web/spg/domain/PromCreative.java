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
 * The persistent class for the prom_creative database table.
 * 
 */
@Entity
@Table(name = "prom_creative")
@NamedQuery(name = "PromCreative.findAll", query = "SELECT p FROM PromCreative p")
public class PromCreative implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "audit_desc", length = 512)
    private String            auditDesc;

    @Column(name = "audit_status")
    private int               auditStatus;

    @Column(name = "is_del", nullable = false)
    private int               isDel;

    @Column(nullable = false, length = 32)
    private String            name;

    @Column(name = "pd_user_id", nullable = false)
    private int               pdUserId;

    @Column(name = "prom_clk_layout_id", nullable = false)
    private int               promClkLayoutId;

    @Column(name = "prom_imp_layout_id", nullable = false)
    private int               promImpLayoutId;

    @Column(name = "prom_type_id", nullable = false)
    private int               promTypeId;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public PromCreative()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAuditDesc()
    {
        return this.auditDesc;
    }

    public void setAuditDesc(String auditDesc)
    {
        this.auditDesc = auditDesc;
    }

    public int getAuditStatus()
    {
        return this.auditStatus;
    }

    public void setAuditStatus(int auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public int getIsDel()
    {
        return this.isDel;
    }

    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPdUserId()
    {
        return this.pdUserId;
    }

    public void setPdUserId(int pdUserId)
    {
        this.pdUserId = pdUserId;
    }

    public int getPromClkLayoutId()
    {
        return this.promClkLayoutId;
    }

    public void setPromClkLayoutId(int promClkLayoutId)
    {
        this.promClkLayoutId = promClkLayoutId;
    }

    public int getPromImpLayoutId()
    {
        return this.promImpLayoutId;
    }

    public void setPromImpLayoutId(int promImpLayoutId)
    {
        this.promImpLayoutId = promImpLayoutId;
    }

    public int getPromTypeId()
    {
        return this.promTypeId;
    }

    public void setPromTypeId(int promTypeId)
    {
        this.promTypeId = promTypeId;
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