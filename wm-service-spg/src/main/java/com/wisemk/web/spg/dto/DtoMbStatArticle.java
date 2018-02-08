package com.wisemk.web.spg.dto;

public class DtoMbStatArticle
{
    public String id;
    public String title;
    public String desc;
    public String thumb;
    public long   pv;
    public String ts;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getThumb()
    {
        return thumb;
    }

    public void setThumb(String thumb)
    {
        this.thumb = thumb;
    }

    public long getPv()
    {
        return pv;
    }

    public void setPv(long pv)
    {
        this.pv = pv;
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
