package com.ligy.product.dao;

import com.ligy.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    int delete(Integer id);

    Product queryById(Integer id);

    int insert(Product record);

    List<Product> selectList(Product product);

    int update(Product product);

    Integer reduceById(Integer id);
}