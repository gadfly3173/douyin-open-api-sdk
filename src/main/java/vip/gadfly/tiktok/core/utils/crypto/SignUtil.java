package vip.gadfly.tiktok.core.utils.crypto;

import vip.gadfly.tiktok.core.exception.TikTokException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 签名工具
 *
 * @author Gadfly
 */
public class SignUtil {
    public static String sign(String string, String digest) {
        try {
            MessageDigest sign = MessageDigest.getInstance(digest);
            sign.reset();
            sign.update(string.getBytes(StandardCharsets.UTF_8));

            //获取字节数组
            byte[] messageDigestByte = sign.digest();

            StringBuilder hexStr = new StringBuilder();
            // 字节数组转换为十六进制数
            for (byte b : messageDigestByte) {
                String shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new TikTokException("当前Java环境不支持" + digest, e);
        }
    }

    /**
     * 随机生成32位字符串.
     */
    public static String getRandomStr() {
        return getRandomStr(16);
    }

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String getRandomStr(int length) {
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
