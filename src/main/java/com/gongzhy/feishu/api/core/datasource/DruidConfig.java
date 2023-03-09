package com.gongzhy.feishu.api.core.datasource;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@PropertySource("classpath:druid.config.properties")
public class DruidConfig {
    @Value("${druid.initial-size}\")\n" +
            "    private int initSize;\n" +
            "    @Value(\"${druid.min-idle}\")\n" +
            "    private int minIdle;\n" +
            "    @Value(\"${druid.max-active}\")\n" +
            "    private int maxActive;\n" +
            "    @Value(\"${druid.max-wait}\")\n" +
            "    private int maxWait;\n" +
            "    @Value(\"${druid.time-between-eviction-runs-millis}\")\n" +
            "    private int timeBetweenEvictionRunsMillis;\n" +
            "    @Value(\"${druid.min-evictable-idle-time-millis}\")\n" +
            "    private int minEvictableIdleTimeMillis;\n" +
            "    @Value(\"${druid.validation-query}\")\n" +
            "    private String validationQuery;\n" +
            "    @Value(\"${druid.test-while-idle}\")\n" +
            "    private boolean testWhileIdle;\n" +
            "    @Value(\"${druid.test-on-borrow}\")\n" +
            "    private boolean testOnBorrow;\n" +
            "    @Value(\"${druid.test-on-return}\")\n" +
            "    private boolean testOnReturn;\n" +
            "    @Value(\"${druid.exception-sorter}\")\n" +
            "    private String exceptionSorter;\n" +
            "    @Value(\"${druid.pool-prepared-statements}\")\n" +
            "    private boolean poolPreparedStatements;\n" +
            "    @Value(\"${druid.max-open-prepared-statements}\")\n" +
            "    private int maxOpenPreparedStatements;\n" +
            "    @Value(\"${druid.max-pool-prepared-statement-per-connection-size}\")\n" +
            "    private int maxPoolPreparedStatementPerConnectionSize;\n" +
            "    @Value(\"${druid.filters}\")\n" +
            "    private String filters;\n" +
            "    @Value(\"${druid.connection-properties}\")\n" +
            "    private Properties connectionProperties;\n" +
            "    @Value(\"${druid.use-global-data-source-stat}\")\n" +
            "    private boolean useGlobalDataSourceStat;\n" +
            "    @Value(\"${druid.stat-view-servlet.login-username}\")\n" +
            "    private String statViewServletLoginUserName;\n" +
            "    @Value(\"${druid.stat-view-servlet.login-password}")
    private String statViewServletLoginPassword;
}
