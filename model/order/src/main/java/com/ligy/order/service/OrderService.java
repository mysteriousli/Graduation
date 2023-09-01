package com.ligy.order.service;

import com.alibaba.fastjson.JSONObject;
import com.ligy.dto.OrderDto;
import com.ligy.dto.SecKillDto;
import com.ligy.fegin.config.SystemTokenConfig;
import com.ligy.fegin.userFegin.UserFeign;
import com.ligy.order.dao.OrderMapper;
import com.ligy.order.dao.OrderProduceReferMapper;
import com.ligy.pojo.Order;
import com.ligy.pojo.OrderProduceRefer;
import com.ligy.pojo.Product;
import com.ligy.fegin.productFegin.ProductFeign;
import com.ligy.pojo.User;
import com.ligy.util.LoginUserUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;


/**
 * @Author lgy
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderProduceReferMapper orderProduceReferMapper;

    @Resource
    private ProductFeign productFeign;

    @Resource
    private UserFeign userFeign;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Transactional(rollbackFor = Exception.class)
    public void addOrder(OrderDto orderDto){
        Order order = orderDto.getOrder();
        orderMapper.insert(order);
        for (OrderProduceRefer orderProduceRefer : orderDto.getOrderProduceReferList()) {
            orderProduceRefer.setOrderId(order.getId());
            orderProduceReferMapper.insert(orderProduceRefer);
        }
    }

    @KafkaListener(topics = {"${spring.kafka.template.default-topic}"})
    public void customOrder(ConsumerRecord<?, ?> consumerRecord){
        SecKillDto secKillDto = JSONObject.parseObject(consumerRecord.value().toString(), SecKillDto.class);
        SystemTokenConfig.setToken(secKillDto.getToken());
        Product product = productFeign.queryById(secKillDto.getProductId()).getData();
        User user = userFeign.queryUser(secKillDto.getUserId()).getData();
        Order order = new Order();
        order.setName(product.getName());
        order.setPlace(secKillDto.getPlace());
        order.setTitle(product.getTitle());
        order.setPrice(product.getPrice());
        order.setPlace(user.getPlace());
        OrderProduceRefer orderProduceRefer = new OrderProduceRefer();
        orderProduceRefer.setNum(1);
        orderProduceRefer.setPrice(product.getPrice());
        orderProduceRefer.setProductId(secKillDto.getProductId());
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder(order);
        orderDto.setOrderProduceReferList(Collections.singletonList(orderProduceRefer));
        addOrder(orderDto);
    }

    public Order queryById(Integer orderId){
        return orderMapper.queryById(orderId);
    }

}
