package com.zetyun.dep.common.core.web.controller;/*
 * Copyright 2020, Zetyun DEP All rights reserved.
 */


import com.ligy.web.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * web层通用数据处理.
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 响应返回结果.
     *
     * @param rows 影响行数
     * @return 操作结果
     */

    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    protected AjaxResult toAjax(Object data) {
        return AjaxResult.success(data);
    }

}
