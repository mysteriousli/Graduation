package com.ligy.dto;

import com.ligy.pojo.Order;
import com.ligy.pojo.OrderProduceRefer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author lgy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Order order;
    private List<OrderProduceRefer> orderProduceReferList;
}
