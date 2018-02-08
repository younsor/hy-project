package com.wisemk.web.spg.share;

import org.springframework.http.HttpStatus;

public class WJsonResult
{
    private int    code;
    private String message;
    private Object data;

    public WJsonResult()
    {
        this.setCode(HttpStatus.OK);
        this.setMessage(HttpStatus.OK.getReasonPhrase());
    }

    public WJsonResult(HttpStatus status)
    {
        this.setCode(status);
        this.setMessage(status.getReasonPhrase());
    }

    public WJsonResult(Object data)
    {
        this.setCode(HttpStatus.OK);
        this.setMessage(HttpStatus.OK.getReasonPhrase());
        this.setData(data);
    }

    public WJsonResult(HttpStatus status, String message)
    {
        this.setCode(status);
        this.setMessage(message);
    }

    public WJsonResult(HttpStatus status, String message, Object data)
    {
        this.setCode(status);
        this.setMessage(message);
        this.setData(data);
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(HttpStatus status)
    {
        this.code = status.value();
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
