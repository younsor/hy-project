package com.wisemk.web.spg.share;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class CommonPage<T>
{

    public String       code    = "200";
    public String       message = "操作成功";
    private Iterable<T> data;
    private Sort        sort;
    private Pagination  pagination;
    private Object      extention;

    public CommonPage(Iterable<T> data)
    {
        pagination = new Pagination();
        this.data = data;
    }

    public CommonPage()
    {}

    public static <T> CommonPage<T> build(List<T> data, Page page)
    {
        CommonPage<T> rslt = new CommonPage<>();

        rslt.setData(data);
        rslt.setPagination(new Pagination());

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

    public Pagination getPagination()
    {
        return pagination;
    }

    public void setPagination(Pagination pagination)
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
