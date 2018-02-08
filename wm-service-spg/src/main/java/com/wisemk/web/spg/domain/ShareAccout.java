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
 * The persistent class for the share_accout database table.
 * 
 */
@Entity
@Table(name = "share_accout")
@NamedQuery(name = "ShareAccout.findAll", query = "SELECT s FROM ShareAccout s")
public class ShareAccout implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false, length = 128)
    private String            accout;

    @Column(nullable = false, length = 64)
    private String            name;

    @Column(nullable = false, length = 128)
    private String            password;

    @Column(name = "random_str", nullable = false, length = 256)
    private String            randomStr;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false)
    private int               state;

    @Column(nullable = false, length = 256)
    private String            ticket;

    @Column(nullable = false, length = 256)
    private String            token;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    public ShareAccout()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAccout()
    {
        return this.accout;
    }

    public void setAccout(String accout)
    {
        this.accout = accout;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRandomStr()
    {
        return this.randomStr;
    }

    public void setRandomStr(String randomStr)
    {
        this.randomStr = randomStr;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public int getState()
    {
        return this.state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getTicket()
    {
        return this.ticket;
    }

    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }

    public String getToken()
    {
        return this.token;
    }

    public void setToken(String token)
    {
        this.token = token;
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