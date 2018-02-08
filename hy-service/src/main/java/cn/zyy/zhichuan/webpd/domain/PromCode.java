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
 * The persistent class for the prom_code database table.
 * 
 */
@Entity
@Table(name = "prom_code")
@NamedQuery(name = "PromCode.findAll", query = "SELECT p FROM PromCode p")
public class PromCode implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "begin_time")
    private Timestamp         beginTime;

    @Column(name = "end_time")
    private Timestamp         endTime;

    @Column(name = "is_del", nullable = false)
    private int               isDel;

    @Column(name = "limit_times")
    private int               limitTimes;

    @Column(length = 256)
    private String            name;

    @Column(name = "pd_user_id", nullable = false)
    private int               pdUserId;

    @Column(name = "prom_creative_id")
    private int               promCreativeId;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(name = "show_id", nullable = false, length = 32)
    private String            showId;

    @Column(nullable = false)
    private int               state;

    @Column(length = 20)
    private String            tel;

    private int               times;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public PromCode()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Timestamp getBeginTime()
    {
        return this.beginTime;
    }

    public void setBeginTime(Timestamp beginTime)
    {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime()
    {
        return this.endTime;
    }

    public void setEndTime(Timestamp endTime)
    {
        this.endTime = endTime;
    }

    public int getIsDel()
    {
        return this.isDel;
    }

    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
    }

    public int getLimitTimes()
    {
        return this.limitTimes;
    }

    public void setLimitTimes(int limitTimes)
    {
        this.limitTimes = limitTimes;
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

    public String getShowId()
    {
        return this.showId;
    }

    public void setShowId(String showId)
    {
        this.showId = showId;
    }

    public int getState()
    {
        return this.state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getTel()
    {
        return this.tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public int getTimes()
    {
        return this.times;
    }

    public void setTimes(int times)
    {
        this.times = times;
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