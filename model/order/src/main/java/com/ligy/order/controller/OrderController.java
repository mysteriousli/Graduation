package com.ligy.order.controller;

import com.ligy.dto.OrderDto;
import com.ligy.order.service.OrderService;
import com.ligy.pojo.Order;
import com.ligy.web.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author lgy
 */

@RestController
@RequestMapping("/order")
@Api(tags = "订单模块")
public class OrderController {
    @Resource
    private OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping
    public AjaxResult<T> save(@RequestBody OrderDto orderDto){
        orderService.addOrder(orderDto);
        return AjaxResult.success();
    }

    @ApiOperation("查看订单")
    @GetMapping("/{id}")
    public AjaxResult<Order> query(@PathVariable("id") Integer orderId){
        return AjaxResult.success(orderService.queryById(orderId));
    }
}
