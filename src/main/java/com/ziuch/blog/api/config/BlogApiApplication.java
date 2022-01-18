package com.ziuch.blog.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@ComponentScan("com.ziuch.*")
@SpringBootApplication
@MapperScan("com.ziuch.blog.api.mapper")
@EnableScheduling
@EnableAsync
public class BlogApiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BlogApiApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BlogApiApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
