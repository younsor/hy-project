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
 * The persistent class for the op_module database table.
 * 
 */
@Entity
@Table(name = "op_module")
@NamedQuery(name = "OpModule.findAll", query = "SELECT o FROM OpModule o")
public class OpModule implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(length = 32)
    private String            action;

    @Column(nullable = false, length = 32)
    private String            control;

    @Column(length = 128)
    private String            icon;

    @Column(name = "is_del", nullable = false)
    private int               isDel;

    @Column(nullable = false, length = 32)
    private String            name;

    @Column(name = "parent_id", nullable = false)
    private int               parentId;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    private int               sort;

    @Column(nullable = false, length = 64)
    private String            type;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public OpModule()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAction()
    {
        return this.action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getControl()
    {
        return this.control;
    }

    public void setControl(String control)
    {
        this.control = control;
    }

    public String getIcon()
    {
        return this.icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
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

    public int getParentId()
    {
        return this.parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public int getSort()
    {
        return this.sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
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