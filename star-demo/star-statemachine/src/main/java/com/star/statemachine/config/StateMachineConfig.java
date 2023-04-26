package com.star.statemachine.config;

import com.star.statemachine.common.Events;
import com.star.statemachine.common.States;
import com.star.statemachine.core.ErrorHandlerAction;
import com.star.statemachine.core.OrderCheckFailedAction;
import com.star.statemachine.core.OrderCheckGuard;
import com.star.statemachine.core.OrderCheckPassedAction;
import com.star.statemachine.core.OrderPayAction;
import java.util.EnumSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * 状态机的配置类
 * @EnableStateMachine 用来启用Spring StateMachine状态机功能
 *
 * 状态机配置类中包含状态配置、状态转换事件关系配置、监听器配置。
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    /**
     * 初始化当前状态机拥有哪些状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        //定义了初始状态为UNPAID待支付
        states.withStates().initial(States.UNPAID)
            //指定分支状态（多个分支状态则多个choice）
            .choice(States.WAITING_FOR_CHECK)
            //指定States中的所有状态作为该状态机的状态定义
            .states(EnumSet.allOf(States.class));
    }

    /**
     * 初始化当前状态机有哪些状态迁移动作
     * 有来源状态为source，目标状态为target，触发事件为event
     *
     * 使用 withChoice 时一定要在在初始化上加上 choice，不然的话不生效。
     * 使用 withChoice 时无须设置 event，因为它不需要发送事件来进行触发。只要状态转移到了 source 状态，就会自动触发分支判断。
     * 如果有多个分支判断，则可以使用 then 子句，它相当于 else if：
     * .withChoice()
     *   .source(States.STATE_FIRST)
     *   .first(States.STATE_SECOND, new MtGuard1())
     *   .then(States.STATE_THIRD, new MtGuard2())
     *   .then(States.STATE_FOURTH, new MtGuard3())
     *   .last(States.STATE_FIFTH)
     *   .and()
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
            .withExternal()
            .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
            //支付事件将触发：待支付状态->待收货状态
            .event(Events.PAY)
            // 状态转换guard
            .guard(new OrderCheckGuard())
            .action(new OrderPayAction())
            .action(new OrderPayAction(), new ErrorHandlerAction())
            .and()
            .withChoice()
            .source(States.WAITING_FOR_CHECK)
            .first(States.WAITING_FOR_RECEIVE, new OrderCheckGuard(), new OrderCheckPassedAction(), new ErrorHandlerAction())
            .last(States.UNPAID)
            //.last(States.UNPAID, new OrderCheckFailedAction())
            .and()
            .withExternal()
            .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
            // 收货事件将触发：待收货状态->结束状态
            .event(Events.RECEIVE);
    }
}