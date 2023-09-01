package com.ligy.order.dao;

import com.ligy.pojo.Order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    int delete(Integer id);

    int insert(Order record);

    List<Order> selectList(Order example);

    Order queryById(Integer id);

    int update(Order record);
}