package com.star.statemachine.config;

import com.star.statemachine.common.Events;
import com.star.statemachine.common.States;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

/**
 * 状态机持久化的配置类
 * 我们对前文的样例做个改造，增加一个持久化配置类，用于实现内存持久化。其原理使用唯一 id 作为 key（本样例是订单 id），把状态机保存到 map 表里面，等需要恢复时再从 map 中取出
 */
@Configuration
public class StateMachinePersisterConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * Redis持久化配置
     */
    @Bean
    public RedisStateMachinePersister persister() {
        RedisStateMachineContextRepository<Events, States> repository = new RedisStateMachineContextRepository(redisConnectionFactory);
        RepositoryStateMachinePersist p = new RepositoryStateMachinePersist<>(repository);
        return new RedisStateMachinePersister<>(p);
    }
}