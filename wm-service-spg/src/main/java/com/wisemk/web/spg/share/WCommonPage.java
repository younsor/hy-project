package com.wisemk.web.spg.share;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class WCommonPage<T>
{

    public String       code    = "200";
    public String       message = "操作成功";
    private Iterable<T> data;
    private Sort        sort;
    private WPagination pagination;
    private Object      extention;

    public WCommonPage(Iterable<T> data)
    {
        pagination = new WPagination();
        this.data = data;
    }

    public WCommonPage()
    {}

    public static <T> WCommonPage<T> build(List<T> data, Page page)
    {
        WCommonPage<T> rslt = new WCommonPage<>();

        rslt.setData(data);
        rslt.setPagination(new WPagination());

        rslt.getPagination().setFirst(page.isFirst());
        rslt.getPagination().setLast(page.isLast());
        rslt.getPagination().setHasPrevious(page.hasPrevious());
        rslt.getPagination().setHasNext(page.hasNext());
        if (page.getTotalPages() > 0)
        {
            rslt.getPagination().setNumber(page.getNumber() + 1);
        }
        else
        {
            rslt.getPagination().setNumber(page.getNumber());
        }

        rslt.getPagination().setSize(page.getSize());
        rslt.getPagination().setNumberOfElements(page.getNumberOfElements());
        rslt.getPagination().setTotalPages(page.getTotalPages());
        rslt.getPagination().setTotalElements(page.getTotalElements());
        rslt.getPagination().setHasContent(page.hasContent());

        return rslt;
    }

    public Iterable<T> getData()
    {
        return data;
    }

    public void setData(Iterable<T> data)
    {
        this.data = data;
    }

    public Sort getSort()
    {
        return sort;
    }

    public void setSort(Sort sort)
    {
        this.sort = sort;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public WPagination getPagination()
    {
        return pagination;
    }

    public void setPagination(WPagination pagination)
    {
        this.pagination = pagination;
    }

    public Object getExtention()
    {
        return extention;
    }

    public void setExtention(Object extention)
    {
        this.extention = extention;
    }
}
