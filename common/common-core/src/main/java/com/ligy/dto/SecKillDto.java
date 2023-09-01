package com.ligy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author lgy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecKillDto {
    private Integer id;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * uums
     */
    private List<String> token;
    /**
     * 地区
     */
    private String place;
}
