package com.ligy.product.controller;

import com.ligy.annotation.Log;
import com.ligy.core.enums.BusinessType;
import com.ligy.pojo.Product;
import com.ligy.product.service.ProductService;
import com.ligy.web.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lgy
 */
@RestController
@RequestMapping("/product")
@Api(tags = "产品模块")
public class ProductController extends com.zetyun.dep.common.core.web.controller.BaseController {
    @Resource
    private ProductService productService;

    @ApiOperation("新增产品")
    @PostMapping
    @Log(value = "产品新增", businessType = BusinessType.INSERT)
    public AjaxResult<T> save(Product product){
        productService.addProduct(product);
        return AjaxResult.success();
    }

    @ApiOperation("产品列表查询")
    @GetMapping
    public AjaxResult<List<Product>> selectList(Product product){
        return AjaxResult.success(productService.selectList(product));
    }

    @ApiOperation("产品查询")
    @GetMapping("/{id}")
    public AjaxResult<Product> queryById(@PathVariable("id") Integer id){
        return AjaxResult.success(productService.query(id));
    }

    @ApiOperation("产品秒杀")
    @GetMapping("/seckill/{id}")
    public AjaxResult<T> seckill(@PathVariable("id") Integer id){
        productService.seckill(id);
        return AjaxResult.success();
    }

    @ApiOperation("产品减少")
    @PutMapping("/{id}")
    public AjaxResult<Boolean> reduce(@PathVariable("id") Integer id){
        return AjaxResult.success(productService.reduce(id));
    }
}
