package com.wisemk.web.spg.dto;

public class DtoPromCode
{
    public int     id;
    public boolean status;
    public int     mid;
    public String  mName;
    public String  name;
    public String  tel;
    public int     limitTimes;
    public String  limitTs;
    public String  url;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public int getMid()
    {
        return mid;
    }

    public void setMid(int mid)
    {
        this.mid = mid;
    }

    public String getmName()
    {
        return mName;
    }

    public void setmName(String mName)
    {
        this.mName = mName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public int getLimitTimes()
    {
        return limitTimes;
    }

    public void setLimitTimes(int limitTimes)
    {
        this.limitTimes = limitTimes;
    }

    public String getLimitTs()
    {
        return limitTs;
    }

    public void setLimitTs(String limitTs)
    {
        this.limitTs = limitTs;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
