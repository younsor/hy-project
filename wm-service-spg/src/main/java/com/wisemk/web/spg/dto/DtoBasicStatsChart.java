package com.wisemk.web.spg.dto;

import java.util.List;

public class DtoBasicStatsChart
{
    List<String> xAxis;
    List<Series> series;

    public List<String> getxAxis()
    {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis)
    {
        this.xAxis = xAxis;
    }

    public List<Series> getSeries()
    {
        return series;
    }

    public void setSeries(List<Series> series)
    {
        this.series = series;
    }

    public static class Series
    {
        public String       name;
        public String       type;
        public List<String> data;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public List<String> getData()
        {
            return data;
        }

        public void setData(List<String> data)
        {
            this.data = data;
        }
    }
}
