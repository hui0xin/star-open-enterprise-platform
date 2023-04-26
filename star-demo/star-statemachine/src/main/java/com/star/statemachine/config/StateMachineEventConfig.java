package com.star.statemachine.config;

import com.star.statemachine.bean.Order;
import com.star.statemachine.common.Events;
import com.star.statemachine.common.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 事件监听器
 * 对于状态监听器，Spring StateMachine 还提供了优雅的注解配置实现方式，
 * 所有 StateMachineListener 接口中定义的事件都能通过注解的方式来进行配置实现。我们可以将StateMachineConfig样例中的状态监听器改用如下注解配置：
 * 注意：监听器仅仅是负责监听而已，它无法对状态转移流程进行控制。也就是说即使监听器内部代码抛出异常，状态仍然会照常发生变化。如果需要通过业务代码控制状态是否转移、转移分支，需要使用后文介绍的 guard、action、choice。
 */
@Configuration
@WithStateMachine
public class StateMachineEventConfig {

    @OnTransition(target = "UNPAID")
    public void create() {
        System.out.println("订单创建");
    }

//    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
//    public void pay() {
//        System.out.println("用户支付完毕");
//    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_CHECK")
    public void pay(Message<Events> message) {
        // 获取消息中的订单对象
        Order order = (Order) message.getHeaders().get("order");
        // 设置新状态
        order.setStates(States.WAITING_FOR_RECEIVE);
        System.out.println("用户支付完毕，状态机反馈信息：" + message.getHeaders().toString());
    }
//
//    /**
//     * 接收消息
//     * @param message
//     */
//    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
//    public void pay(Message<Events> message) {
//        // 获取消息中的订单对象
//        Order order = (Order) message.getHeaders().get("order");
//        // 设置新状态
//        order.setStates(States.WAITING_FOR_RECEIVE);
//        System.out.println("用户支付完毕，状态机反馈信息：" + message.getHeaders());
//    }

    @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payStart() {
        System.out.println("用户支付（状态转换开始）");
    }

    @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payEnd() {
        System.out.println("用户支付（状态转换结束）");
    }

//    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
//    public void receive() {
//        System.out.println("用户已收货");
//    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive(Message<Events> message) {
        // 获取消息中的订单对象
        Order order = (Order) message.getHeaders().get("order");
        // 设置新状态
        order.setStates(States.DONE);
        System.out.println("用户已收货，状态机反馈信息：" + message.getHeaders());
    }

    /**
     * 监听状态从待检查订单到待收货
     */
    @OnTransition(source = "WAITING_FOR_CHECK", target = "WAITING_FOR_RECEIVE")
    public void checkPassed() {
        System.out.println("检查通过，等待收货");
    }

    /**
     * 监听状态从待检查订单到待付款
     */
    @OnTransition(source = "WAITING_FOR_CHECK", target = "UNPAID")
    public void checkFailed() {
        System.out.println("检查不通过，等待付款");
    }


}