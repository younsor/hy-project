package com.wisemk.web.spg.share;

import org.springframework.http.HttpStatus;

public class WSrvException extends RuntimeException
{
    private HttpStatus httpStatus;

    public WSrvException()
    {
        super();
    }

    public WSrvException(HttpStatus httpStatus, String message)
    {
        super(message);
        this.httpStatus = httpStatus;
    }
}
