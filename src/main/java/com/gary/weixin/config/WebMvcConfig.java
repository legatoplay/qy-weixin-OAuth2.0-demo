package com.gary.weixin.config;

import com.gary.weixin.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Gary on 2019-03-04.
 * 拦截器配置
 *
 * @author Gary
 * @version 0.0.1
 * @date 2019-03-04
 * @since 0.0.1
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
