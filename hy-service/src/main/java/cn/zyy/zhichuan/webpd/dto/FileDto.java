package cn.zyy.zhichuan.webpd.dto;

/**
 * Created by Joy.Zhao on 2017/6/8.
 */
public class FileDto
{
    public Long     fileSize;
    private String  mime;
    private String  suffixName;
    private String  resolution;
    private Integer size_w;
    private Integer size_h;
    private String  fileName;
    private String  url;

    public Long getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getMime()
    {
        return mime;
    }

    public void setMime(String mime)
    {
        this.mime = mime;
    }

    public String getSuffixName()
    {
        return suffixName;
    }

    public void setSuffixName(String suffixName)
    {
        this.suffixName = suffixName;
    }

    public String getResolution()
    {
        return resolution;
    }

    public void setResolution(String resolution)
    {
        this.resolution = resolution;
    }

    public Integer getSize_w()
    {
        return size_w;
    }

    public void setSize_w(Integer size_w)
    {
        this.size_w = size_w;
    }

    public Integer getSize_h()
    {
        return size_h;
    }

    public void setSize_h(Integer size_h)
    {
        this.size_h = size_h;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
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
