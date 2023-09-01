package com.ligy.fegin.productFegin;

import com.ligy.fegin.config.SystemTokenConfig;
import com.ligy.pojo.Product;
import com.ligy.web.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Author lgy
 */
@Component
@FeignClient(name = "product-server", path = "/product" , configuration = SystemTokenConfig.class)
public interface ProductFeign {
    @GetMapping("/{id}")
    AjaxResult<Product> queryById(@PathVariable("id") Integer id);
    @PutMapping("/{id}")
    AjaxResult<Boolean> reduce(@PathVariable("id") Integer id);
}
