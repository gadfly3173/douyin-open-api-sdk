package vip.gadfly.tiktok.core.util.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * <a href="https://open.douyin.com/platform/doc/6943439913106835470">https://open.douyin.com/platform/doc/6943439913106835470</a>
 *
 * @author Gadfly
 * @since 2021-09-15 17:55
 */
public class MobilePhoneUtil {
    public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
                           InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

    public static String decrypt(String encryptedMobile, String clientSecret)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
                           InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] clientSecretBytes = clientSecret.getBytes();
        SecretKey secretKey = new SecretKeySpec(clientSecretBytes, 0, clientSecretBytes.length, "AES");

        byte[] iv = Arrays.copyOfRange(clientSecretBytes, 0, 16);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        String algorithm = "AES/CBC/PKCS5Padding";

        return decrypt(algorithm, encryptedMobile, secretKey, ivParameterSpec);
    }
}
