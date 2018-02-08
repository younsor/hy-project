package cn.zyy.zhichuan.webpd.domain;

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
 * The persistent class for the prom_type database table.
 * 
 */
@Entity
@Table(name = "prom_type")
@NamedQuery(name = "PromType.findAll", query = "SELECT p FROM PromType p")
public class PromType implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false, length = 64)
    private String            name;

    @Column(name = "prom_clk_layout_ids", nullable = false, length = 64)
    private String            promClkLayoutIds;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "template_url", nullable = false, length = 128)
    private String            templateUrl;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public PromType()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPromClkLayoutIds()
    {
        return this.promClkLayoutIds;
    }

    public void setPromClkLayoutIds(String promClkLayoutIds)
    {
        this.promClkLayoutIds = promClkLayoutIds;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public String getTemplateUrl()
    {
        return this.templateUrl;
    }

    public void setTemplateUrl(String templateUrl)
    {
        this.templateUrl = templateUrl;
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