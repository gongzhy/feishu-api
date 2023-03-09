package com.gongzhy.feishu.api.core.datasource.processor;

import com.gongzhy.feishu.api.util.AESUtil;
import com.gongzhy.feishu.api.util.PropertiesHelper;
import com.gongzhy.feishu.api.util.ResourceHelper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.util.Iterator;
import java.util.Properties;

/**
 * @Author: Stan
 * @Description:
 * @Date: create in 20:01 2021/9/3
 */
@Order(2)
public class DataSourceEnvironmentPostProcessor implements EnvironmentPostProcessor {

    /**
     * 在配置文件中关于数据库配置的相关key test
     */
    private static final String configuredDatasourceFileNameKey = "datasource.file.name";
    private static final String configuredDatasourceFilePathKey = "datasource.file.path";
    private static final String configuredDatasourceFileEncryptedKey = "datasource.file.encrypted";
    private static final String configuredCharsetKey = "default.charset";
    /**
     * 默认的数据库相关配置
     */
    private static final String defaultDatasourceFileName = "jdbc.properties";
    private static final String defaultDatasourceFilePath = "classpath:";
    private static final String defaultDatasourceFileEncrypt = "";
    private static final String defaultCharset = "UTF-8";
    /**
     * 数据库相关配置参数
     */
    private String configuredDatasourceFileName;
    private String configuredDatasourceFilePath;
    private String configuredDatasourceFileEncrypted;
    private String configuredCharset;

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //
        //
        this.loadConfiguredDatasourceParams(environment.getPropertySources());
        //
        Properties properties = loadDatasourceProperties();
        //
        if (properties != null) {
            // 把数据源配置信息放入Spring环境中
            PropertiesPropertySource propertySource = new PropertiesPropertySource("dataSource", properties);
            environment.getPropertySources().addLast(propertySource);
        }
    }

    /**
     * 加载配置的数据库相关参数
     *
     * @param mutablePropertySources
     */
    private void loadConfiguredDatasourceParams(MutablePropertySources mutablePropertySources) {
        Iterator<PropertySource<?>> mutablePropertyIter = mutablePropertySources.iterator();
        while (mutablePropertyIter.hasNext()) {
            PropertySource<?> propertySource = mutablePropertyIter.next();

            if (propertySource.containsProperty(configuredDatasourceFileNameKey)) {
                this.configuredDatasourceFileName = String.valueOf(propertySource.getProperty(configuredDatasourceFileNameKey));
            }

            if (propertySource.containsProperty(configuredDatasourceFileEncryptedKey)) {
                this.configuredDatasourceFileEncrypted = String.valueOf(propertySource.getProperty(configuredDatasourceFileEncryptedKey));
            }

            if (propertySource.containsProperty(configuredDatasourceFilePathKey)) {
                this.configuredDatasourceFilePath = String.valueOf(propertySource.getProperty(configuredDatasourceFilePathKey));
            }

            if (propertySource.containsProperty(configuredCharsetKey)) {
                this.configuredCharset = String.valueOf(propertySource.getProperty(configuredCharsetKey));
            }
        }

        if (StringUtils.isBlank(configuredDatasourceFileName)) {
            this.configuredDatasourceFileName = defaultDatasourceFileName;
        }
        if (StringUtils.isBlank(configuredDatasourceFilePath)) {
            this.configuredDatasourceFilePath = defaultDatasourceFilePath;
        }
        if (StringUtils.isBlank(configuredDatasourceFileEncrypted)) {
            this.configuredDatasourceFileEncrypted = defaultDatasourceFileEncrypt;
        }
        if (StringUtils.isBlank(configuredCharset)) {
            this.configuredCharset = defaultCharset;
        }
    }

    /**
     * 加载数据库配置
     *
     * @return
     */
    private Properties loadDatasourceProperties() throws Exception {
        String path = null;
        Resource resource = null;

        if (configuredDatasourceFilePath.startsWith("classpath")) {
            // 从classpath中读取配置
            path = configuredDatasourceFilePath.replace("classpath:", "");
            resource = ResourceHelper.getResourceFromClasspath(configuredDatasourceFileName, path);
        } else {
            // 从文件目录中读取配置
            path = configuredDatasourceFilePath.replace("filesystem:", "");
            resource = ResourceHelper.getResourceFromFileSystem(configuredDatasourceFileName, path);
        }

        if (StringUtils.isBlank(this.configuredDatasourceFileEncrypted)) {
            return PropertiesHelper.loadProperties(resource);
        } else {
            // 读取加密的资源内容
            String resourceContent = ResourceHelper.read(resource, this.configuredCharset);
            if (StringUtils.isBlank(resourceContent)) {
                return null;
            }
            // 对资源内容进行解密
            resourceContent = AESUtil.decrypt(resourceContent);
            return PropertiesHelper.loadProperties(resourceContent, this.configuredCharset);
        }
    }
}
