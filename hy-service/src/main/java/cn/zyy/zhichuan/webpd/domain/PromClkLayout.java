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
 * The persistent class for the prom_clk_layout database table.
 * 
 */
@Entity
@Table(name = "prom_clk_layout")
@NamedQuery(name = "PromClkLayout.findAll", query = "SELECT p FROM PromClkLayout p")
public class PromClkLayout implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(length = 128)
    private String            description;

    @Column(nullable = false, length = 128)
    private String            name;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public PromClkLayout()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

}