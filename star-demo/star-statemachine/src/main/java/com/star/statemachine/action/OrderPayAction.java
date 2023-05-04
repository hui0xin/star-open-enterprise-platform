package com.star.statemachine.action;

import com.star.statemachine.bean.Order;
import com.star.statemachine.common.Events;
import com.star.statemachine.common.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * 支付Action
 *（1）Action 可以用来在状态机的状态转换过程中实现自定义逻辑，如数据库操作，日志记录等。
 *（2）注意，如果 Action 执行过程中出现了异常，状态机的状态是不会发生变化的。
 * 我们需要在订单从“待支付”变为“待收货”状态时执行一些业务逻辑，首先定义一个 Action：
 */
public class OrderPayAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        // 获取消息中的订单对象
        Order order = (Order) context.getMessage().getHeaders().get("order");
        System.out.println("正在执行订单（" + order.getId() + "）支付处理业务......");

        // 为了测试抛出一个异常
        throw new RuntimeException("这是一个运行错误!");
    }
}