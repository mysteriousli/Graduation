package com.ligy.product.service;

import com.alibaba.fastjson.JSONObject;
import com.ligy.dto.SecKillDto;
import com.ligy.exception.CustomException;
import com.ligy.pojo.Product;
import com.ligy.product.dao.ProductMapper;
import com.ligy.product.dao.ProductReferMapper;
import com.ligy.util.LoginUserUtils;
import com.ligy.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lgy
 */
@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductReferMapper productReferMapper;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public void addProduct(Product product) {
        productMapper.insert(product);
    }

    public List<Product> selectList(Product product) {
        return productMapper.selectList(product);
    }

    public Product query(Integer id) {
        return productMapper.queryById(id);
    }

    public synchronized void seckill(Integer id) {
        Product product = productMapper.queryById(id);
        if (product.getNum() > 0) {
            SecKillDto secKillDto = new SecKillDto();
            secKillDto.setProductId(id);
            secKillDto.setUserId(LoginUserUtils.getLoginUser().getId());
            secKillDto.setPlace(LoginUserUtils.getLoginUser().getPlace());
            secKillDto.setToken(WebUtils.getToken());
            productReferMapper.insert(secKillDto);
            reduce(id);
            // 向消息队列发送秒杀信息
            kafkaTemplate.send(topic, JSONObject.toJSONString(secKillDto));
        } else {
            throw new CustomException("产品数量不足！");
        }
    }

    public boolean reduce(Integer id) {
        return productMapper.reduceById(id) > 0;
    }
}
