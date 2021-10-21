# java上报日志到ELK的SDk


启动类添加com.lzx.elk.log.sdk 扫描
```
@SpringBootApplication(scanBasePackages = {"com.lzx.elk.log.sdk","com.xxxx"})
```

配置文件添加
```xml
logging:
  config: classpath:logback-kafka.xml
elk:
  kafka:
    url: 127.0.0.1:9092
    topic: logs
```