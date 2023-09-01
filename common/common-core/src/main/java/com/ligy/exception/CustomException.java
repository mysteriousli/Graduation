package com.ligy.exception;


import lombok.Data;

/**
 * 自定义异常.
 * @author zhangjw
 * @date 2022/12/14 14:42
 * @version 1.0
 */
@Data
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer status;
    /**
     *
     */
    private String message;
    /**
     *
     */
    public CustomException(String message) {
        this.message = message;
    }
    /**
     *
     */
    public CustomException(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public CustomException(Exception t) {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getstatus() {
        return status;
    }
}
