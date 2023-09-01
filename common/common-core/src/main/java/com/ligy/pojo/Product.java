package com.ligy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;

    private String name;

    private String place;

    private String iphone;

    private Double price;

    private String title;

    private String imgUrl;

    private Integer num;
}