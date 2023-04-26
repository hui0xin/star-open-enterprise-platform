# 如果您觉得本项目对你有用，请随手star，谢谢

技术文章地址：https://www.jianshu.com/u/6ad247189165

# star-open-enterprise-platform

开放的企业级平台，使用springcloud来实现

还没有实现的：   
1，邮件发送 和 短信发送      
2，支付系统     
3，广告系统    
4，交易所

工程说明：

```
-- star-business-center              业务系统    
    -- star-adsystem                 广告系统   
    -- star-file-upload              上传下载   
    -- star-oauth-center             oatuh认证中心   
        -- star-oauth2               oauth2实现单点登陆（配合网关使用）   
-- star-demo                         demo操作   
    -- star-commons-elasticsearch    es操作demo   
    -- star-commons-mongodb          mongodb操作demo  
    -- star-commons-rabbitmq         rabbitmq操作demo   
    -- star-commons-multi            多数据源配置demo   
        -- star-commons-multi-jdbctemplate         jdbcTemplate 的多数据源配置   
        -- star-commons-multi-jpa-annotation       jpa 注解方式的多数据源配置   
        -- star-commons-multi-jpa-scanpackage      jpa 扫描包方式的多数据源配置  
        -- star-commons-multi-kafka                kafka 多数据源配置  
        -- star-commons-multi-mongodb              mongodb 多数据源配置   
        -- star-commons-multi-mybatis-annotation   mybatis 注解方式的多数据源配置   
        -- star-commons-multi-mybatis-scanpackage  mybatis 扫描包方式的多数据源配置   
        -- star-commons-multi-shardingjdbc         shardingjdbc 方式多数据源配置   
-- star-eureka-server                 注册中心   
-- star-gateway-center                网关中心   
    -- star-api-gateway               springcloud+jwt    
    -- star-api-gateway-zuul          zuul+jwt   
    -- star-api-gateway-zuul-oauth2   zuul+oauth   
-- star-monitor-center                监控中心   
    -- star-monitor-admin-server      springboot项目 服务监控   
    -- star-hystrix-turbine-server    对服务集群的熔断数据汇总。然后交给 dashboard-server 显示   
    -- star-hystrix-dashboard-server  用于显示 turbine-server 汇总的数据   
    -- star-hystrix-dashboard-turbine 服务是将 turbine-server 和 dashboard-server 服务汇总起来作为一个工程   
-- star-generator-center              代码和项目自动生成 中心（脚手架）   
    -- star-mybatis-generator         生成 controller，service serviceImple，mapper，DO 等实体   
    -- star-project-generator         生成demo工程   
-- star-spring-boot-starter           支持工具   
    -- star-spring-boot-starter-datadog                  datadog组建
    -- star-spring-boot-starter-lock                     基于redisson实现的分布式锁  
    -- star-spring-boot-starter-log                      log日志组建
    -- star-spring-boot-starter-mail                     邮件发送模版组建
    -- star-spring-boot-starter-redis                    redis工具类组建   
    -- star-spring-boot-starter-dynamic-datasource       动态数据源，通过配置加载
    -- star-spring-boot-starter-dynamic-datasource-redis redis动态数据源，通过配置加载
-- star-support                       支持工具
    -- star-commons-support           基本工具工程 util，exception，errorcode，baseBean 全局枚举，多语言支持等等   
    -- star-commons-support-web       提供web功能的支持 跨域，请求和返回数据格式化，全局异常处理，   
-- star-spring-boot-starter-parent    系统依赖的parent    
-- star-spring-boot-dependencies      公司 依赖 jar 的版本管理（最顶层项目）   

```
