package com.ligy;/*
 * Copyright 2020, Zetyun DEP All rights reserved.
 */

import java.io.Serializable;

/**
 * 响应信息主体.
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功.
     */
    public static final int SUCCESS = 200;

    /**
     * 失败.
     */
    public static final int FAIL = 500;

    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAIL_MESSAGE = "操作失败";

    private int status;

    private String message;

    private T data;

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, SUCCESS_MESSAGE);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, SUCCESS_MESSAGE);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> fail() {
        return restResult(null, FAIL, FAIL_MESSAGE);
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, FAIL, FAIL_MESSAGE);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> R<T> fail(int status, String msg) {
        return restResult(null, status, msg);
    }

    private static <T> R<T> restResult(T data, int status, String message) {
        R<T> apiResult = new R<>();
        apiResult.setStatus(status);
        apiResult.setData(data);
        apiResult.setMessage(message);
        return apiResult;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
