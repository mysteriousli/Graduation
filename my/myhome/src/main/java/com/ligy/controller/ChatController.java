package com.ligy.controller;

import com.ligy.annotation.Log;
import com.ligy.core.enums.BusinessType;
import com.ligy.exception.CustomException;
import com.ligy.netty.domain.BaseFactory;
import com.ligy.netty.domain.EsEntity;
import com.ligy.netty.enums.ChatEnums;
import com.ligy.netty.factory.ChatFactory;
import com.ligy.util.EsUtils;
import com.ligy.web.AjaxResult;
import io.netty.channel.Channel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.T;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lgy
 */
@RestController
@RequestMapping("/chat")
@Api(tags = "聊天模块")
public class ChatController {
    @Resource
    private ChatFactory chatFactory;
    @Resource
    private EsUtils esUtil;

    @GetMapping("/{serverPort}")
    @ApiOperation(value = "初始化聊天窗口")
    public AjaxResult<T> initChannel(@PathVariable("serverPort") String serverPort, @RequestParam("chatEnums") ChatEnums chatEnums){
        BaseFactory baseFactory = chatFactory.getFactoryMap(serverPort, chatEnums);
        baseFactory.addChannel();
        return AjaxResult.success();
    }

    @PostMapping("/{serverPort}")
    @ApiOperation(value = "发送消息")
    public AjaxResult<Object> send(@PathVariable("serverPort") String serverPort, @RequestParam("msg") String msg, @RequestParam("chatEnums") ChatEnums chatEnums,@RequestParam("clientPort") String clientPort){
        BaseFactory baseFactory = chatFactory.getFactoryMap(serverPort, chatEnums);
        if (CollectionUtils.isEmpty(baseFactory.getChannelFutureList())){
            delete(serverPort);
            throw new CustomException("聊天窗口不存在！");
        }
        Channel channel = baseFactory.queryChannel(clientPort).channel();
        channel.writeAndFlush(msg);
        return AjaxResult.success();
    }

    @GetMapping("/log/{serverPort}")
    @Log(value = "消息发送",businessType = BusinessType.OTHER)
    @ApiOperation(value = "获取聊天窗口下所有消息")
    public AjaxResult<List<EsEntity>> getMsg(@PathVariable("serverPort") String serverPort){
        List<EsEntity> esEntityList = esUtil.searchEs(serverPort);
        return AjaxResult.success(esEntityList);
    }

    @DeleteMapping("/{serverPort}")
    @ApiOperation(value = "删除聊天窗口")
    public AjaxResult<Object> delete(@PathVariable("serverPort") String serverPort){
        // 删除聊天窗口
        chatFactory.removeChat(serverPort);
        // 删除聊天窗口下的消息
        esUtil.removeIndex(serverPort);
        return AjaxResult.success();
    }
}
