package com.ligy.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author lgy
 */
public class ChatClientUtil {
    public static ChannelFuture initChatClient(String port) throws InterruptedException {
        return getChannelFuture(port);
    }
    public static ChannelFuture getChannelFuture(String port) throws InterruptedException {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup);
        bootstrap.channel(NioSocketChannel.class);
        // 设置处理器
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                // 将字符串编解码器及客户端处理器添加到pipeline中
                ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                ch.pipeline().addLast(new ChatClientHandler());
            }
        });
        // 连接服务端
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", Integer.parseInt(port));
        channelFuture.sync();
        return channelFuture;
    }
}
