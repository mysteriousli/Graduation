package com.ligy.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author lgy
 */
public class ChatServerUtil {
    public static ServerBootstrap initChatServer(String port) {
        // 新建两个事件循环组，bossGroup用于监听客户端的连接请求，将连接请求发送给workerGroup用于处理客户端连接的数据读写
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup, workGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                nioSocketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                nioSocketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                nioSocketChannel.pipeline().addLast(new ChatServerHandler());
            }
        });
        serverBootstrap.bind(Integer.parseInt(port));
        return serverBootstrap;
    }
}
