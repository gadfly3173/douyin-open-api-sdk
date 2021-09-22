package vip.gadfly.tiktok.core.util.crypto;

import java.util.Random;

/**
 * 签名工具
 *
 * @author Gadfly
 */
public class SignUtil {
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

    public static boolean checkWebhookSignature(String xSignature, String... arr) {
        String webhookSignature = SHA1.gen(arr);
        return webhookSignature.equals(xSignature);
    }
}
