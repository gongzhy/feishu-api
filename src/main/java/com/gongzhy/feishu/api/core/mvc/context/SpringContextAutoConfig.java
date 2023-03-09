package com.gongzhy.feishu.api.core.mvc.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Stan
 * @Description:
 * @Date: create in 19:10 2021/9/16
 */
@Configuration
public class SpringContextAutoConfig {

    @Bean
    public SpringHelper springHelper() {
        return new SpringHelper();
    }



}
