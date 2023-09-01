package com.ligy.product.dao;

import com.ligy.dto.SecKillDto;
import com.ligy.pojo.ProductRefer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductReferMapper {

    int delete(Integer id);

    int insert(SecKillDto record);

    int update(ProductRefer record);

    List<ProductRefer> selectList(ProductRefer record);

    ProductRefer queryById(Integer id);

}