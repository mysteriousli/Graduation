package com.ligy.netty.domain;

import com.ligy.netty.enums.ChatEnums;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lgy
 */
@Data
public abstract class BaseFactory {
    /**
     * 聊天室名称
     */
    private String name;
    /**
     * 聊天室id
     */
    private String serverPort;
    /**
     * 聊天室人员ip
     */
    private final List<String> ips = new ArrayList<>();
    /**
     * 主题
     */
    private String title;
    /**
     * 聊天室类型
     */
    private ChatEnums chatEnums;
    /**
     * 聊天人员通道
     */
    private final List<ChannelFuture> channelFutureList = new ArrayList<>();
    /**
     * 服务
     */
    private ServerBootstrap bootstrap;

    /**
     * 初始化聊天室
     */
    public abstract void initChat(String serverPort);

    /**
     * 为聊天室添加人员信息
     */
    public abstract String addChannel();

    /**
     * 通过用户port获取聊天室
     * @param port port
     * @return ChannelFuture
     */
    public abstract ChannelFuture queryChannel(String clientPort);
    /**
     * 删除聊天室
     */
    public abstract void removeChannel(String clientPort);
}
