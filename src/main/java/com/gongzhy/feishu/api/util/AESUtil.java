package com.gongzhy.feishu.api.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

/**
 * @author Moyou on 2019/4/8
 */
public class AESUtil {
    private final static String sKey = "823^7viSPKuio48@";
    private final static String ivParameter = "823^7viSPKuio48@";
    private final static int KEY_LENGTH = 16;


    public static String encrypt(String sSrc) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String sSrc) throws Exception {
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(2, skeySpec, iv);
        byte[] encrypted1 = Base64.decodeBase64(sSrc);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, "utf-8");
    }

    /**
     * 加密
     *
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String Encrypt(String sSrc, String sKey) {
        if (StringUtils.isBlank(sSrc) || StringUtils.isBlank(sKey)) {
            return sSrc;
        }
        // 判断Key是否为16位
        if (sKey.length() != KEY_LENGTH) {
            System.out.print(String.format("Key长度不是16位, key:%s", sKey));
            return "";
        }
        try {
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            //此处使用BASE64做转码功能，同时能起到2次加密的作用。
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sSrc;
    }

    /**
     * 解密
     *
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String Decrypt(String sSrc, String sKey) {
        if (StringUtils.isBlank(sSrc) || StringUtils.isBlank(sKey)) {
            return sSrc;
        }
        // 判断Key是否为16位
        if (sKey.length() != KEY_LENGTH) {
            System.out.print("Key长度不是16位");
            return "";
        }
        try {
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getRandomValue() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}