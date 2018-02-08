package com.wisemk.web.spg.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import com.wisemk.web.spg.domain.TrackDetail;

public class DtoViewRecords
{
    public long                total;
    public List<ContentDetail> content;

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public List<ContentDetail> getContent()
    {
        return content;
    }

    public void setContent(List<ContentDetail> content)
    {
        this.content = content;
    }

    public static class ContentDetail
    {
        public String id;
        public String ts;
        public String region;
        public String client;
        public String artid;

        public static ContentDetail of(TrackDetail td)
        {
            if (null == td)
                return null;

            ContentDetail inst = new ContentDetail();
            inst.id = td.getId() + "";

            if (null != td.getUpdateTime())
            {
                inst.ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(td.getUpdateTime());
            }
            else
            {
                inst.ts = "";
            }

            if (null != td.getProvince())
                inst.region = null != td.getCity() ? td.getProvince() + td.getCity() : td.getProvince();
            if (null != td.getOs())
                inst.client = td.getOs();
            inst.artid = td.getAid() + "";

            return inst;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getTs()
        {
            return ts;
        }

        public void setTs(String ts)
        {
            this.ts = ts;
        }

        public String getRegion()
        {
            return region;
        }

        public void setRegion(String region)
        {
            this.region = region;
        }

        public String getClient()
        {
            return client;
        }

        public void setClient(String client)
        {
            this.client = client;
        }

        public String getArtid()
        {
            return artid;
        }

        public void setArtid(String artid)
        {
            this.artid = artid;
        }
    }
}
