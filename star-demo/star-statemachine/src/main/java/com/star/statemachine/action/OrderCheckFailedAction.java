package com.star.statemachine.action;

import com.star.statemachine.bean.Order;
import com.star.statemachine.common.Events;
import com.star.statemachine.common.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * 订单检查未通过Action
 */
public class OrderCheckFailedAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        // 获取消息中的订单对象
        Order order = (Order) context.getMessage().getHeaders().get("order");
        // 设置新状态
        order.setStates(States.UNPAID);
        System.out.println("检查未通过，执行相关的业务代码......");
    }
}