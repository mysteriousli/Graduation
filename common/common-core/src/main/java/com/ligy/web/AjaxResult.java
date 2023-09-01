package com.ligy.web;/*
 * Copyright 2020, Zetyun DEP All rights reserved.
 */

import com.ligy.core.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 操作消息提醒.
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 成功. */
    public static final int SUCCESS = Constants.SUCCESS;

    /** 失败. */
    public static final int FAIL = Constants.FAIL;

    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAIL_MESSAGE = "操作失败";

    private int status;
    private String message;
    private T data;

    public static <T> AjaxResult<T> success()
    {
        return restResult(null, SUCCESS, SUCCESS_MESSAGE);
    }

    public static <T> AjaxResult<T> success(T data)
    {
        return restResult(data, SUCCESS, SUCCESS_MESSAGE);
    }

    public static <T> AjaxResult<T> success(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> AjaxResult<T> error()
    {
        return restResult(null, FAIL, FAIL_MESSAGE);
    }

    public static <T> AjaxResult<T> error(String msg)
    {
        return restResult(null, FAIL, msg);
    }

    public static <T> AjaxResult<T> error(T data)
    {
        return restResult(data, FAIL, FAIL_MESSAGE);
    }

    public static <T> AjaxResult<T> error(T data, String msg)
    {
        return restResult(data, FAIL, msg);
    }

    public static <T> AjaxResult<T> error(int status, String msg)
    {
        return restResult(null, status, msg);
    }

    private static <T> AjaxResult<T> restResult(T data, int status, String message)
    {
        AjaxResult<T> apiResult = new AjaxResult<>();
        apiResult.setStatus(status);
        apiResult.setData(data);
        apiResult.setMessage(message);
        return apiResult;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

}
