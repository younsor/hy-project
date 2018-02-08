package cn.zyy.zhichuan.webpd.dto;

public class DtoPromCreative
{
    public int    id;
    public String name;
    public String promTypeId;
    public String promTypeName;
    public String status;
    public String ts;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPromTypeId()
    {
        return promTypeId;
    }

    public void setPromTypeId(String promTypeId)
    {
        this.promTypeId = promTypeId;
    }

    public String getPromTypeName()
    {
        return promTypeName;
    }

    public void setPromTypeName(String promTypeName)
    {
        this.promTypeName = promTypeName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTs()
    {
        return ts;
    }

    public void setTs(String ts)
    {
        this.ts = ts;
    }

}
