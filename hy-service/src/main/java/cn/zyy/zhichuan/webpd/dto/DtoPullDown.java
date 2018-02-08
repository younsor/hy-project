package cn.zyy.zhichuan.webpd.dto;

public class DtoPullDown
{
    public int    value;
    public String text;

    public DtoPullDown(int value, String text)
    {
        this.value = value;
        this.text = text;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
