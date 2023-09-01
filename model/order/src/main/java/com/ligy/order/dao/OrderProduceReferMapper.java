package com.ligy.order.dao;

import com.ligy.pojo.OrderProduceRefer;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderProduceReferMapper {

    int delete(Integer id);

    int insert(OrderProduceRefer record);

    List<OrderProduceRefer> selectList(OrderProduceRefer record);

    OrderProduceRefer queryById(Integer id);

    int update(OrderProduceRefer record);
}