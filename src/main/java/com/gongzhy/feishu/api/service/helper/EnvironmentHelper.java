package com.gongzhy.feishu.api.service.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @auth Ryan
 * @date 2021/9/30 16:38
 */
@Component
@Configuration
public class EnvironmentHelper {

    /**
     * 当前激活的配置文件
     */
    public static String env;

    @Value("${spring.profiles.active}")
    public void setEnv(String param) {
        env = param;
    }

    /**
     * 是否正式环境
     *
     * @return
     */
    public static boolean isProd() {
        return "prod".equalsIgnoreCase(env);
    }

    public static boolean isTest() {
        return "test".equalsIgnoreCase(env);
    }

    /**
     * 是否开发环境
     *
     * @return
     */
    public static boolean isDev() {
        return "dev".equalsIgnoreCase(env);
    }

}
