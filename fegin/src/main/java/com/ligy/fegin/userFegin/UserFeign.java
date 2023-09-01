package com.ligy.fegin.userFegin;

import com.ligy.fegin.config.SystemTokenConfig;
import com.ligy.pojo.User;
import com.ligy.web.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author lgy
 */
@Component
@FeignClient(name = "user-server",path = "/user",  configuration = SystemTokenConfig.class)
public interface UserFeign {

    @PostMapping("/login")
    AjaxResult<Boolean> login(@RequestBody User user);

    @GetMapping("/uums/{uums}")
    AjaxResult<User> uums(@PathVariable("uums") String uums);

    @GetMapping("/{id}")
    public AjaxResult<User> queryUser(@PathVariable("id") Integer id);
}
