package com.star.statemachine.bean;

import com.star.statemachine.common.States;
import lombok.Data;

/**
 * 订单类
 */
@Data
public class Order {

    // 订单号
    private int id;

    // 订单状态
    private States states;

}