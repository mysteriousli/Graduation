//package com.ligy.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.ligy.core.path.PathConstants;
//import com.ligy.pojo.User;
//import com.ligy.fegin.userFeign.UserFeign;
//import com.ligy.web.AjaxResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @Author lgy
// */
//@Component
//@Slf4j
//public class LoginFilter implements GlobalFilter, Ordered {
//    @Resource
//    private UserFeign userFeign;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest serverHttpRequest = exchange.getRequest();
//        ServerHttpResponse serverHttpResponse = exchange.getResponse();
//        String path = serverHttpRequest.getPath().toString();
//        if (PathConstants.url.contains(path)) {
//            return chain.filter(exchange);
//        }
//        Map<String, String> cookie = Arrays.stream(serverHttpRequest.getHeaders().get("Cookie").get(0).split(";"))
//                .collect(Collectors.toMap(
//                        key -> key.split("=")[0], value -> value.split("=")[1]
//                ));
//        String uumsStr = cookie.get("UUMS");
//        AjaxResult<User> ajaxResult = userFeign.uums(uumsStr);
//        if (ajaxResult.getData()!=null){
//            ServerHttpRequest request = exchange.getRequest();
//            return chain.filter(exchange);
//        }
//        //json数据
//        Map<String, Object> map = new HashMap<>();
//        map.put("msg", "未登录");
//        map.put("code", 403);
//        //3.3作JSON转换
//        byte[] bytes = JSONObject.toJSONString(map).getBytes(StandardCharsets.UTF_8);
//        //3.4调用bufferFactory方法,生成DataBuffer对象
//        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(bytes);
//        //4.调用Mono中的just方法,返回要写给前端的JSON数据
//        return serverHttpResponse.writeWith(Mono.just(buffer));
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
