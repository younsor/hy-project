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
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(nullable = false, length = 32)
    private String            appid;

    @Column(name = "avatar_url", length = 512)
    private String            avatarUrl;

    @Column(length = 32)
    private String            city;

    @Column(length = 32)
    private String            country;

    @Column(nullable = false)
    private int               del;

    @Column(length = 32)
    private String            gender;

    @Column(length = 32)
    private String            language;

    @Column(name = "login_ip", nullable = false, length = 32)
    private String            loginIp;

    @Column(name = "login_sess_key", nullable = false, length = 64)
    private String            loginSessKey;

    @Column(name = "login_ts", nullable = false, length = 32)
    private String            loginTs;

    @Column(name = "nick_name", length = 64)
    private String            nickName;

    @Column(nullable = false, length = 64)
    private String            openid;

    @Column(length = 32)
    private String            province;

    @Column(name = "reg_ip", nullable = false, length = 32)
    private String            regIp;

    @Column(name = "reg_source", nullable = false, length = 32)
    private String            regSource;

    @Column(name = "reg_ts", nullable = false, length = 32)
    private String            regTs;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(length = 32)
    private String            tel;

    @Column(nullable = false, length = 64)
    private String            unionid;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    @Column(name = "wx_account", length = 64)
    private String            wxAccount;

    public User()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAppid()
    {
        return this.appid;
    }

    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    public String getAvatarUrl()
    {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public int getDel()
    {
        return this.del;
    }

    public void setDel(int del)
    {
        this.del = del;
    }

    public String getGender()
    {
        return this.gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getLanguage()
    {
        return this.language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getLoginIp()
    {
        return this.loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public String getLoginSessKey()
    {
        return this.loginSessKey;
    }

    public void setLoginSessKey(String loginSessKey)
    {
        this.loginSessKey = loginSessKey;
    }

    public String getLoginTs()
    {
        return this.loginTs;
    }

    public void setLoginTs(String loginTs)
    {
        this.loginTs = loginTs;
    }

    public String getNickName()
    {
        return this.nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getOpenid()
    {
        return this.openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getProvince()
    {
        return this.province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getRegIp()
    {
        return this.regIp;
    }

    public void setRegIp(String regIp)
    {
        this.regIp = regIp;
    }

    public String getRegSource()
    {
        return this.regSource;
    }

    public void setRegSource(String regSource)
    {
        this.regSource = regSource;
    }

    public String getRegTs()
    {
        return this.regTs;
    }

    public void setRegTs(String regTs)
    {
        this.regTs = regTs;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public String getTel()
    {
        return this.tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getUnionid()
    {
        return this.unionid;
    }

    public void setUnionid(String unionid)
    {
        this.unionid = unionid;
    }

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getWxAccount()
    {
        return this.wxAccount;
    }

    public void setWxAccount(String wxAccount)
    {
        this.wxAccount = wxAccount;
    }

}