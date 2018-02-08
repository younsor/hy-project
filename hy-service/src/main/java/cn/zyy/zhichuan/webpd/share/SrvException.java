package cn.zyy.zhichuan.webpd.share;

import org.springframework.http.HttpStatus;

public class SrvException extends RuntimeException
{
    private HttpStatus httpStatus;

    public SrvException()
    {
        super();
    }

    public SrvException(HttpStatus httpStatus, String message)
    {
        super(message);
        this.httpStatus = httpStatus;
    }
}
