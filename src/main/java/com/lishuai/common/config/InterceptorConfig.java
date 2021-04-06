package com.lishuai.common.config;

import com.lishuai.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 让拦截器起作用
 * @author lishuai
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor()).
                //除了/user，都拦截
                excludePathPatterns("/user/**")
                .addPathPatterns("/**");
    }
}