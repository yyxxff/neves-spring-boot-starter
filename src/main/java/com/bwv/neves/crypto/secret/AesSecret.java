package com.bwv.neves.crypto.secret;

import com.bwv.neves.properties.CryptoProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.UUID;


public class AesSecret {

    public static String aesKey;

    @Autowired
    private CryptoProperties cryptoProperties;

    /**
     * key size
     */
    private static final int KEYSIZE = 128;

    public AesSecret() {
        aesKey = cryptoProperties.getAesKey();
    }

    public byte[] encrypt(String data) {
        if (StringUtils.isNotBlank(data)) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                // 选择一种固定算法，为了避免不同java实现的不同算法，生成不同的密钥，而导致解密失败
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                random.setSeed(aesKey.getBytes());
                keyGenerator.init(KEYSIZE, random);
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
                // 创建密码器
                Cipher cipher = Cipher.getInstance("AES");
                byte[] byteContent = data.getBytes(StandardCharsets.UTF_8);
                // 初始化
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                return cipher.doFinal(byteContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * AES加密
     *
     */
    public String encryptToStr(String data) {

        return StringUtils.isNotBlank(data) ? parseByte2HexStr(encrypt(data)) : null;
    }


    /**
     * AES解密
     *
     */
    public byte[] decrypt(byte[] data) {
        if (ArrayUtils.isNotEmpty(data)) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                //选择一种固定算法，为了避免不同java实现的不同算法，生成不同的密钥，而导致解密失败
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                random.setSeed(aesKey.getBytes());
                keyGenerator.init(KEYSIZE, random);
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
                // 创建密码器
                Cipher cipher = Cipher.getInstance("AES");
                // 初始化
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                // 加密
                return cipher.doFinal(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * AES解密
     *
     */
    public String decryptToStr(String enCryptdata) {
        return StringUtils.isNotBlank(enCryptdata) ? new String(decrypt(parseHexStr2Byte(enCryptdata))) : null;
    }

    /**
     * 二进制转换成16进制
     *
     */
    public String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     */
    public byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}