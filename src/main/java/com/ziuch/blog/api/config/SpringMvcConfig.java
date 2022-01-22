package com.ziuch.blog.api.config;


import com.ziuch.blog.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/user/login"
                        , "/test/**", "/ebook/list", "/category/all", "/doc/content/**", "/doc/all/**", "/doc/vote/**", "/user/logout/**"
                        , "/doc.html/**", "/webjars/**", "/swagger-resources", "/v3/**", "/testApi/**", "/ebook-snapshot/**");
    }
}
