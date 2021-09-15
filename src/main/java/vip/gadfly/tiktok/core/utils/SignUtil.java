package vip.gadfly.tiktok.core.utils;

import vip.gadfly.tiktok.core.exception.TikTokException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * 签名工具
 *
 * @author Gadfly
 */
public class SignUtil {
    public static String sign(String string) {
        try {
            MessageDigest sign = MessageDigest.getInstance("MD5");
            sign.reset();
            sign.update(string.getBytes());

            return Base64.getEncoder().encodeToString(sign.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new TikTokException("当前Java环境不支持SHA256withRSA", e);
        }
    }

    /**
     * 随机生成32位字符串.
     */
    public static String genRandomStr() {
        return genRandomStr(32);
    }

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String genRandomStr(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
