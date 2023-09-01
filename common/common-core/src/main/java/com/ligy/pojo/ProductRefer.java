package com.ligy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRefer {
    private Integer id;

    private Integer productId;

    private Integer userId;
}