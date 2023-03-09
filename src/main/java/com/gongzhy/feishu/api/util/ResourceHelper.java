package com.gongzhy.feishu.api.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResourceHelper {

    /**
     * 读取资源的内容
     * @param resource
     * @param charset
     * @return
     * @throws IOException
     */
    public static String read(Resource resource, String charset) throws IOException {
        if (resource == null) {
            return null;
        }

        BufferedReader reader = null;
        String line = null;

        StringBuilder resourceContentBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), charset));
            while ((line = reader.readLine()) != null) {
                resourceContentBuilder.append(line);
            }
            return resourceContentBuilder.toString();
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /**
     * 从文件路径中获取文件资源
     * @param fileName
     * @param folder
     * @return
     */
    public static Resource getResourceFromFileSystem(String fileName, String...folder) {
        String absolutePath = null;
        if (folder != null && folder.length > 0 && !StringUtils.isEmpty(folder[0])) {
            absolutePath = System.getProperty("user.dir") + File.separator + folder[0] + File.separator + fileName;
        } else {
            absolutePath = System.getProperty("user.dir") + File.separator + fileName;
        }

        return new FileSystemResource(absolutePath);
    }

    /**
     * 从classpath中获取文件资源
     * @param fileName
     * @param folder
     * @return
     */
    public static Resource getResourceFromClasspath(String fileName, String...folder) {
        String absolutePath = null;
        if (folder != null && folder.length > 0 && !StringUtils.isEmpty(folder[0])) {
            absolutePath = folder[0] + File.separator + fileName;
        } else {
            absolutePath = fileName;
        }
        return new ClassPathResource(absolutePath);
    }

}
