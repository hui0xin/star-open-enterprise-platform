# application name
spring:
  application:
    name: @spring.application.name@

# port
server:
  port: @server.port@
  tomcat:
    uri-encoding: UTF-8
    # 最大线程数
    max-threads: @server.tomcat.max-threads@
    # 最大连接数
    max-connections: @server.tomcat.max-connections@
#  servlet:
#    context-path: /demo

---
# database HikariCP相关配置
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: @spring.datasource.url@
    username: @spring.datasource.username@
    password: @spring.datasource.password@
    hikari:
      minimum-idle: @spring.datasource.hikari.minimum-idle@
      maximum-pool-size: @spring.datasource.hikari.maximum-pool-size@
      auto-commit: @spring.datasource.hikari.auto-commit@
      idle-timeout: @spring.datasource.hikari.idle-timeout@
      pool-name: DatebookHikariCP
      max-lifetime: @spring.datasource.hikari.max-lifetime@
      connection-timeout: @spring.datasource.hikari.connection-timeout@
      connection-test-query: @spring.datasource.hikari.connection-test-query@
    type: com.zaxxer.hikari.HikariDataSource

# MyBatis
mybatis:
  # 搜索指定包别名
  type-aliases-package: com.${packageName}
  # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapper-locations: classpath:mapper/*Mapper.xml
  # 加载全局的配置文件
  config_location: classpath:mybatis/mybatis-config.xml
#  #配置驼峰属性自动映射
#  configuration:
#    map-underscore-to-camel-case: true

# pagehelper
pagehelper:
  # 方言
  helperDialect: mysql
  # 分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页，
  # pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。
  reasonable: true
  # # 默认值false，分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值，查找到合适的值时就会自动分页。
  supportMethodsArguments: true
  # 用于从对象中根据属性名取值， 可以配置 pageNum,pageSize,count,pageSizeZero,reasonable，不配置映射的用默认值，
  # 默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero
  params: count=countSql
  returnPageInfo: check

---
## redis config
spring:
  redis:
    # Redis默认情况下有16个分片，这里配置具体使用的分片
    database: 0
    host: @spring.redis.host@
    port: @spring.redis.port@
    # 连接超时时间（毫秒）
    timeout: @spring.redis.timeout@
    password: @spring.redis.password@
      #集群配置
      #cluster:
      #nodes: #192.168.211.134:7000,192.168.211.134:7001
      #max-redirects: 5
      #哨兵配置
      #sentinel:
      #master: master1
      #nodes: # 172.16.33.216:16001,172.16.33.216:16002
    lettuce:
      pool:
        # 连接池最大连接数（使用负值表示没有限制）
        max-active: @spring.redis.lettuce.pool.max-active@
        # 连接池中的最大空闲连接
        max-idle: @spring.redis.lettuce.pool.max-idle@
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-wait: @spring.redis.lettuce.pool.max-wait@
        # 连接池中的最小空闲连接
        min-idle: @spring.redis.lettuce.pool.min-idle@

---
#上传 文件限制
spring:
  servlet:
    multipart:
      enabled: true
      #单个数据的大小 可以使用MB活着kb 默认是1MB
      max-file-size: 10MB
      #总数据的大小 可以使用MB活着kb 默认是10MB
      #设置上传文件的临时目录
      #location :
      max-request-size: 100MB
      #设置文件缓存的临界点,超过则先保存到临时目录 值可以使用后缀“mb”或“kb” 默认是0
      file-size-threshold: 0
      #是否在文件或参数访问时延迟解析多部分请求。默认是fasle
      resolve-lazily: false

#ribbon请求连接的超时时间- 限制3秒内必须请求到服务，并不限制服务处理的返回时间
#ribbonTimeout = (ribbonReadTimeout + ribbonConnectTimeout) * (maxAutoRetries + 1) * (maxAutoRetriesNextServer + 1);
#ribbonTimeout = (15000 + 3000) * (1 + 0) * (1 + 1) = 36000
# ribbon config
ribbon:
  #请求连接超时时间
  ConnectTimeout: 3000
  #请求处理的超时时间
  ReadTimeout: 6000
  #对当前实例的重试次数
  MaxAutoRetries: 0
  #切换实例的重试次数 1
  MaxAutoRetriesNextServer: 1
  #ServerList缓存时间
  ServerListRefreshInterval: 10000

hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            #熔断超时时间
            timeoutInMilliseconds: 10000
        timeout:
          #是否启用超时时间
          enabled: true

#开启断路器 新版本的fegin自带断路器
feign:
  hystrix:
    enabled: true

# 允许/actuator/bus-refresh接口被外部调用
management:
  endpoints:
    web:
      exposure:
        # 开放监控内容
        include: "*"
  endpoint:
    shutdown:
      enabled: true #启用shutdown端点
    health:
      # health/detail 细节（）
      show-details: always

# swagger 开关 true为开启，false或没有为关闭
swagger:
  enable: @swagger.enable@


## 日志配置
logging:
  config: classpath:logs/logback-spring.xml
  path: /Users/star/logs
  level:
    # 全局日志级别
    default: INFO
    # 项目日志级别
    commons: INFO
    # mybatis日志级别
    mybatis: DEBUG

---
#控制台输出彩色
spring:
  output:
    ansi:
      enabled: always

# ------------------------------业务相关配置写到下面-------------------------