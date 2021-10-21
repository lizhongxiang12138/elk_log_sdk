# java上报日志到ELK的SDk

## 打包到本地仓库
```shell script
mvn clean install -Dmaven.test.skip=true
```

## 在项目中引入
```xml
<dependency>
    <groupId>com.lzx</groupId>
    <artifactId>elk_log_sdk</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## 启动类添加com.lzx.elk.log.sdk 扫描
```
@SpringBootApplication(scanBasePackages = {"com.lzx.elk.log.sdk","com.xxxx"})
```

## 配置文件添加
```xml
logging:
  config: classpath:logback-kafka.xml
elk:
  kafka:
    url: 127.0.0.1:9092  #kafka地址
    topic: logs          #主题
```