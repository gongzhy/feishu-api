package com.gongzhy.feishu.api.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    /**
     * 加载配置文件
     * @param resource
     * @return
     * @throws IOException
     */
    public static Properties loadProperties(Resource resource) throws IOException {
        if (resource != null) {
            return PropertiesLoaderUtils.loadProperties(resource);
        }

        return null;
    }

    /**
     * 加载配置文件
     * @param content
     * @param charset
     * @return
     * @throws IOException
     */
    public static Properties loadProperties(String content, String charset) throws IOException {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        ByteArrayResource byteArrayResource = new ByteArrayResource(content.getBytes(charset));
        return PropertiesLoaderUtils.loadProperties(byteArrayResource);
    }

}
