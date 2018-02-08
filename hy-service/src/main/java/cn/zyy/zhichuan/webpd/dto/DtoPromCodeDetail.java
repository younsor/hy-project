package cn.zyy.zhichuan.webpd.dto;

public class DtoPromCodeDetail
{
    public int    id;
    public int    mid;
    public String name;
    public String tel;
    public int    times;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getMid()
    {
        return mid;
    }

    public void setMid(int mid)
    {
        this.mid = mid;
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

    public int getTimes()
    {
        return times;
    }

    public void setTimes(int times)
    {
        this.times = times;
    }
}
