package com.ligy.netty.factory;

import com.ligy.netty.client.ChatClientUtil;
import com.ligy.netty.domain.BaseFactory;
import com.ligy.netty.enums.ChatEnums;
import com.ligy.netty.server.ChatServerUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Author lgy
 */
@Slf4j
public class PopularChat extends BaseFactory {

    @Override
    public void initChat(String port) {
        setChatEnums(ChatEnums.POPULAR);
        setBootstrap(ChatServerUtil.initChatServer(port));
        setServerPort(port);
        setTitle("普通窗口！");
        setName("PopularChat");
    }

    @Override
    public String addChannel() {
        ChannelFuture channelFuture = null;
        try {
            channelFuture = ChatClientUtil.getChannelFuture(this.getServerPort());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        getChannelFutureList().add(channelFuture);
        InetSocketAddress socketAddress = (InetSocketAddress) channelFuture.channel().remoteAddress();
        return String.valueOf(socketAddress.getPort());
    }

    @Override
    public ChannelFuture queryChannel(String clientPort) {
        ChannelFuture channelFuture = null;
        for (ChannelFuture future : getChannelFutureList()) {
            Channel channel = future.channel();
            InetSocketAddress address = (InetSocketAddress) channel.localAddress();
            if (clientPort.equals(String.valueOf(address.getPort()))){
                channelFuture = future;
            }
        }
        return channelFuture;
    }

    @Override
    public void removeChannel(String clientPort) {
        ChannelFuture channelFuture = null;
        for (ChannelFuture future : getChannelFutureList()) {
            Channel channel = future.channel();
            InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
            if (clientPort.equals(String.valueOf(address.getPort()))){
                channelFuture = future;
            }
        }
        getChannelFutureList().remove(channelFuture);
    }
}
