package com.ligy.netty.server;

import com.ligy.netty.domain.EsEntity;
import com.ligy.util.EsUtils;
import com.ligy.util.SpringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author lgy
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {
    private static List<Channel> channels = new ArrayList<>();
    private EsUtils esUtil;

    public ChatServerHandler() {
        esUtil= SpringUtils.getBean(EsUtils.class);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.add(channel);
        channels.forEach(ch -> {
            if (ch == channel) {
                channel.writeAndFlush("恭喜您，上线成功");
            } else {
                ch.writeAndFlush("系统消息:[" + ch.remoteAddress() + "]客户端已上线");
            }
        });
        System.out.println("客户端[" + channel.remoteAddress() + "]请求连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        Iterator<Channel> iterator = channels.iterator();
        while (iterator.hasNext()) {
            Channel ch = iterator.next();
            if (ch == channel) {
                iterator.remove();
            }
            ch.writeAndFlush("系统消息:[" + channel.remoteAddress() + "]客户端已下线");
        }
        System.out.println("客户端[" + channel.remoteAddress() + "]断开连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Channel channel = ctx.channel();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String now = formatter.format(date);
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(now).append("收到用户[").append(channel.remoteAddress()).append("]发来的消息:").append(msg.toString()).append("\n");
        //index not exists
        String index = "index_"+ ((InetSocketAddress) channel.localAddress()).getPort();
        esUtil.save(dataBuilder.toString(), index);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}