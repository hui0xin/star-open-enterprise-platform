package com.star.statemachine.test;

import com.star.statemachine.bean.Order;
import com.star.statemachine.common.Events;
import com.star.statemachine.common.States;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    /**
     * 状态机对象
     */
    @Autowired
    private StateMachine<States, Events> stateMachine;

    /**
     * 运行结果如下。要注意的是，上面代码我们连续发送两个 PAY 支付事件，
     * 结果只有第一次发送成功。因为此时状态已经转移到了待收货状态，不能再次接收支付事件。
     */
    //@Test
    public void testOrder1() {

        System.out.println("--- 开始创建订单流程 ---");
        stateMachine.start();    //start()就是创建这个订单流程，根据之前的定义，该订单会处于待支付状态，
        System.out.println("> 当前状态：" + stateMachine.getState().getId());

        System.out.println("--- 发送支付事件 ---");
        boolean result1 = stateMachine.sendEvent(Events.PAY);
        System.out.println("> 事件是否发送成功：" + result1 + "，当前状态：" + stateMachine.getState().getId());

        System.out.println("--- 再次发送支付事件 ---");
        boolean result2 = stateMachine.sendEvent(Events.PAY);
        System.out.println("> 事件是否发送成功：" + result2 + "，当前状态：" + stateMachine.getState().getId());

        System.out.println("--- 发送收货事件 ---");
        boolean result3 = stateMachine.sendEvent(Events.RECEIVE);
        System.out.println("> 事件是否发送成功：" + result3 + "，当前状态：" + stateMachine.getState().getId());
    }

    /**
     * 发送消息
     * Statemachine 的 sendEvent() 方法除了可以发送事件外，
     * 还可以发送 Message。Message 除了会传递事件从而触发状态机的转换外，
     * 同时还能传递其他信息方便我们进行业务处理。假设我们需要传递订单信息，首先定义一个如下订单类
     */
    //@Test
    public void testOrder2() {

        System.out.println("--- 开始创建订单流程 ---");
        //start()就是创建这个订单流程，根据之前的定义，该订单会处于待支付状态，
        stateMachine.start();

        // 创建订单对象
        Order order = new Order();
        order.setStates(States.UNPAID);
        order.setId(123);

        System.out.println("--- 发送支付事件 ---");
        // 构建消息
        Message message = MessageBuilder.withPayload(Events.PAY).setHeader("order", order).build();
        stateMachine.sendEvent(message);

        System.out.println("--- 发送收货事件 ---");
        message = MessageBuilder.withPayload(Events.RECEIVE).setHeader("order", order).build();
        stateMachine.sendEvent(message);
    }

    /**
     * 首先我们对一个订单 id 小于 100 的订单进行支付，由于 guard 返回为 false，则状态机当前仍然处于“待支付”状态；
     * 接着将订单号修改成 999 后再次付款，由于 guard 返回为 true，因此状态机从“待支付”状态变为“待收货”状态；
     */
    //@Test
    public void testOrder3() {

        System.out.println("--- 开始创建订单流程 ---");
        stateMachine.start();    //start()就是创建这个订单流程，根据之前的定义，该订单会处于待支付状态，
        System.out.println("> 当前状态：" + stateMachine.getState().getId());

        // 创建订单对象
        Order order = new Order();
        order.setStates(States.UNPAID);
        order.setId(1);

        System.out.println("--- 发送支付事件 ---");
        // 构建消息
        Message message = MessageBuilder.withPayload(Events.PAY).setHeader("order", order).build();
        boolean result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());

        // 修改订单id
        order.setId(999);
        System.out.println("--- 再次发送支付事件 ---");
        message = MessageBuilder.withPayload(Events.PAY).setHeader("order", order).build();
        result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());

        System.out.println("--- 发送收货事件 ---");
        message = MessageBuilder.withPayload(Events.RECEIVE).setHeader("order", order).build();
        result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());
    }

    //@Test
    public void testOrder4() {

        System.out.println("--- 开始创建订单流程 ---");
        stateMachine.start();    //start()就是创建这个订单流程，根据之前的定义，该订单会处于待支付状态，

        // 创建订单对象
        Order order = new Order();
        order.setStates(States.UNPAID);
        order.setId(100);

        System.out.println("--- 发送支付事件 ---");
        Message message = MessageBuilder.withPayload(Events.PAY).setHeader("order", order).build();
        boolean result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());

        System.out.println("--- 发送收货事件 ---");
        message = MessageBuilder.withPayload(Events.RECEIVE).setHeader("order", order).build();
        result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());
    }

    @Test
    public void testOrder5() {

        System.out.println("--- 开始创建订单流程 ---");
        stateMachine.start();    //start()就是创建这个订单流程，根据之前的定义，该订单会处于待支付状态，

        // 创建订单对象
        Order order = new Order();
        order.setStates(States.UNPAID);
        order.setId(1);

        System.out.println("--- 发送支付事件 ---");
        Message message = MessageBuilder.withPayload(Events.PAY).setHeader("order", order).build();
        boolean result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());

        // 修改订单id
        order.setId(999);
        System.out.println("--- 再次发送支付事件 ---");
        message = MessageBuilder.withPayload(Events.PAY).setHeader("order", order).build();
        result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());

        System.out.println("--- 发送收货事件 ---");
        message = MessageBuilder.withPayload(Events.RECEIVE).setHeader("order", order).build();
        result = stateMachine.sendEvent(message);
        System.out.println("> 事件是否发送成功：" + result + "，当前状态：" + stateMachine.getState().getId());
    }

}