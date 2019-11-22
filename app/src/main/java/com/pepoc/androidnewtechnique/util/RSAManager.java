package com.pepoc.androidnewtechnique.util;

import android.content.Context;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAManager {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decode(privateKey.getBytes(), Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(Context context, String publicKeyFile) throws Exception {
        InputStream isPK = context.getAssets().open(publicKeyFile);
        String publicKey = readKey(isPK);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decode(publicKey.getBytes("UTF-8"), Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        isPK.close();
        return keyFactory.generatePublic(keySpec);

//        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "BC");
//        InputStream isPK = context.getAssets().open(publicKey);
//        Certificate certificate = certificateFactory.generateCertificate(isPK);
//        return certificate.getPublicKey();

    }

    private static String readKey(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) != '-') {
                sb.append(readLine);
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encode(encryptedData, Base64.DEFAULT));
    }

    /**
     * RSA解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decode(data, Base64.DEFAULT);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }


    /**
     * RSA解密
     *
     * @param data       待解密数据
     * @param publicKey 公钥
     * @return
     */
    public static String decrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] dataBytes = Base64.decode(data.getBytes("UTF-8"), Base64.DEFAULT);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encode(signature.sign(), Base64.DEFAULT));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decode(sign.getBytes(), Base64.DEFAULT));
    }

    public static String decryptTest(Context context, String sign) {
        try {
            return decrypt(sign, getPublicKey(context, "sa_key.pub"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        try {
            // 生成密钥对
//            KeyPair keyPair = getKeyPair();
//            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
//            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
//            System.out.println("私钥:" + privateKey);
//            System.out.println("公钥:" + publicKey);
//            // RSA加密
//            String data = "待加密的文字内容";
//            String encryptData = encrypt(data, getPublicKey(publicKey));
//            System.out.println("加密后内容:" + encryptData);
//            // RSA解密
            String decryptData = decrypt("hMMQtH3CnmSN56PR5QIzK1UWrXY91iHi7NTNWk+U9YDaY1d+t1l9PM00r7bqED1Wb+vsff6OJyykCHOrSkOdTu7qkfXPn7VbdpWiDePObbAbvhxta3o1uUlHjbHF0upHr7MYR9rCRa1DeA+ppqrVsVlzSFjxKW79tqsy0YF8MBA=", getPublicKey(null, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0qpDUu9Y8gn92Dcb8y2sU0ucA\n" +
                    "uSxMzO1WLPzQtaeOIy+8T/XlzHGfh8gFiXCSsC/t2fKjJP69cyMiAMaks2l0C6Za\n" +
                    "sxgoZiC3gPBaWfqvhCIy2VTpAfg8nXCztSc2xZVovTmfDiDftrVX6116ovabOAw7\n" +
                    "Pek5N9yowj5AAax3JwIDAQAB"));
            System.out.println("解密后内容:" + decryptData);
//
//            // RSA签名
//            String sign = sign(data, getPrivateKey(privateKey));
//            // RSA验签
//            boolean result = verify("123", getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzM4STW6ylPDwzc0BjWuayk3rlag14x/87xzUZZ712xi+OlatjQGZH4KLULQoWPX5KJxlXK48L4PCKcYIIYsy5mo0xyfBDIX9H/fwizPh8CSBkhpabaj+0he5KsU6rWXl0CPjuabExaBEhFe/QYhFZlUcBteEPXa1UXr+GTVYJzvRw2mTaMd6Q/e1LHSkOTRGgywrxcu09CaeJpI+sMXGf66uVvCKQoSskaoZm+8U2r48sWtJnXrk2rwdq1DgobdWsS0mYe3hD5uXvO2J82bSK4wA0s5ATT+W109AszB/iwvSp+yYp5w5/BMIMt8SlGSz31f3H6QbbMegeCHYJXLrlwIDAQAB"), "ZUcIQ/rWmxVo/4jfC2UYco5pRaw0WymXyFZyK6P/ru1eCYhIhEzWmKKStsBTNNKpN54F1qtkLePqmnoE7neBt+SSP+UxstK2ZeElU7Whs3QkhFYbUIZdpsnSeieESLmd1Nc8W33k4GsebTLK+WuHJf2Q2kBN1YgBb7Ub1CgFxRCkf2QQCtO04+miPqWyTr/T/l9CGRuTi3S8g1hcPwFURs7MYW5pqwM3uXCXmc+iPjLusDGMYRmn9Jo1P35Uc/DzkpbIGprDJ9B4ET9PHvw5U53T84KN2dt85QgUBMVLPJxPgeEunfUqRrb/udNDWqLV97qCoSkhtuWCoHQkyxAG4Q==");
//            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
