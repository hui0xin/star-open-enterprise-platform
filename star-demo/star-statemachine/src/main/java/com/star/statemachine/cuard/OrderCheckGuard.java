package com.star.statemachine.cuard;

import com.star.statemachine.bean.Order;
import com.star.statemachine.common.Events;
import com.star.statemachine.common.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * Guard 是一种特殊类型的状态机动作，它被用于限制转换发生的条件。在转换发生之前，
 * 它会检查这个条件是否满足，如果满足了，转换就会发生，否则转换就不会发生。
 * 假设我们要求订单从“待支付”变为“待收货”状态需要满足某个条件（这里为方便演示，只有订单 id 不小于 100 的才满足条件），
 */
public class OrderCheckGuard implements Guard<States, Events> {

    /**
     * 检查方法
     * @param context
     * @return
     */
    @Override
    public boolean evaluate(StateContext<States, Events> context) {
        // 获取消息中的订单对象
        Order order = (Order) context.getMessage().getHeaders().get("order");

        // 这里做个特殊处理，订单id小于100的都属于不通过
        if (order.getId() < 100) {
            System.out.println("检查订单：不通过");
            return false;
        } else {
            System.out.println("检查订单：通过");
            return true;
        }
    }
}