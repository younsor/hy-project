package cn.zyy.zhichuan.webpd.dto;

public class DtoBasicStatsTable
{
    public String time;
    public long   impress;
    public long   click;
    public String rate;

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public long getImpress()
    {
        return impress;
    }

    public void setImpress(long impress)
    {
        this.impress = impress;
    }

    public long getClick()
    {
        return click;
    }

    public void setClick(long click)
    {
        this.click = click;
    }

    public String getRate()
    {
        return rate;
    }

    public void setRate(String rate)
    {
        this.rate = rate;
    }
}
