package com.ligy.user.controller;

import com.ligy.pojo.User;

import com.ligy.user.service.UserService;
import com.ligy.web.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author lgy
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public AjaxResult<String> login(@RequestBody User user){
        return AjaxResult.success(userService.login(user));
    }

    @GetMapping("/uums/{uums}")
    @ApiOperation(value = "校验uums")
    public AjaxResult<User> uums(@PathVariable("uums") String uums)
    {
        return AjaxResult.success(userService.uums(uums));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户")
    public AjaxResult<User> queryUser(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(userService.queryById(id));
    }
}
