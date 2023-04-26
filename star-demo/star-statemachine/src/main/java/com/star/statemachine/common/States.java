package com.star.statemachine.common;

/**
 * 订单状态枚举
 */
public enum States {

    // 待支付
    UNPAID,

    // 待检查订单
    WAITING_FOR_CHECK,

    // 待收货
    WAITING_FOR_RECEIVE,

    // 结束
    DONE

}