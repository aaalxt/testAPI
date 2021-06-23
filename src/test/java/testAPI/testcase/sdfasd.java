package testAPI.testcase;




import org.testng.annotations.Test;



import java.math.BigInteger;

import java.security.*;

import java.security.spec.*;

import java.util.Date;

import java.io.ByteArrayOutputStream;

import java.security.interfaces.RSAPrivateKey;

import java.security.interfaces.RSAPublicKey;

import java.util.HashMap;



import sun.misc.BASE64Decoder;

import sun.misc.BASE64Encoder;



import javax.crypto.Cipher;

public class sdfasd {



    private static HashMap<String,String> map = new HashMap<String,String>();

    public static void main(String[] args) throws InvalidKeySpecException {



        try {

            //创建RSA加密

            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

            //密钥位数

            keyPairGen.initialize(1024);

            // 动态生成密钥对，这是当前最耗时的操作，一般要2s以上。

            KeyPair key = keyPairGen.generateKeyPair();

            //公钥

            PublicKey publicKey =  key.getPublic();





            //密钥

            PrivateKey privateKey = key.getPrivate();



            printPrivateKey(privateKey);

            printPublicKey(publicKey);





            // 公钥比特编码

//	byte[] privateData = privateKey.getEncoded();  //对应着getPrivateKey()方法

            //密钥比特编码

//	byte[] publicData = publicKey.getEncoded();   //对应着getPublicKey()方法



            String message ="吕双，我的女朋友";

            String inputStr = encrypt(message, new String(new BASE64Encoder().encodeBuffer(publicKey.getEncoded())));

            System.out.println(inputStr);

            System.out.println(decrypt(inputStr, new String(new BASE64Encoder().encodeBuffer(privateKey.getEncoded()))));







        } catch (NoSuchAlgorithmException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        } catch (Exception e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }



    // 通过公钥byte[]将公钥还原，适用于RSA算法

    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,InvalidKeySpecException {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;

    }



    //通过私钥byte[]将密钥还原，适用于RSA算法

    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,InvalidKeySpecException{

        PKCS8EncodedKeySpec keySpec = new  PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;

    }



    //打印公钥信息

    public static void printPublicKey(PublicKey publicKey) {

        RSAPublicKey key = (RSAPublicKey)publicKey;

        map.put("N", key.getModulus().toString());

        map.put(" E", key.getPublicExponent().toString());

    }



    //获取私钥信息

    public static void printPrivateKey(PrivateKey privateKey) {

        RSAPrivateKey key = (RSAPrivateKey)privateKey;

        map.put("D", key.getPrivateExponent().toString());

    }



    // 使用N、E值还原公钥

    public static PublicKey getPublicKeyByN_E(String N,String E) throws NoSuchAlgorithmException, InvalidKeySpecException {

        BigInteger bigN = new BigInteger(N);

        BigInteger bigE = new BigInteger(E);

        RSAPublicKeySpec spec = new RSAPublicKeySpec(bigN, bigE);

        KeyFactory factory = KeyFactory.getInstance("RSA");

        return factory.generatePublic(spec);

    }

    // 使用N、D值还原公钥

    public static PrivateKey getPrivateKeyByN_D(String N,String D) throws NoSuchAlgorithmException, InvalidKeySpecException {

        BigInteger bigN = new BigInteger(N);

        BigInteger bigD = new BigInteger(D);

        RSAPrivateKeySpec spec = new RSAPrivateKeySpec(bigN, bigD);

        KeyFactory factory = KeyFactory.getInstance("RSA");

        return factory.generatePrivate(spec);

    }

    //加密

    /*

     * 公钥加密

     * @Param str : 加密字符串

     * @Param publicKey : 公钥

     * return : 密文

     */

    public static String encrypt(String str , String publicKey) throws Exception {

        //base64编码的公钥

        byte[] decoded = new BASE64Decoder().decodeBuffer(publicKey);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);

        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);



        //RSA加密

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        String outStr = new BASE64Encoder().encode(cipher.doFinal(str.getBytes("UTF-8")));

        return outStr;



    }



    /*

     * RSA私钥解密

     * @param str :加密后的字符串

     * @param privateKey : 解密后的字符串

     */

    public static String decrypt(String str,String privateKey) throws Exception{



        //64位解码加密后的字符串

        byte[] inputStr = new BASE64Decoder().decodeBuffer(new String(str.getBytes("utf-8"),"utf-8"));

        //base64解码的私钥

        byte[] decode = new BASE64Decoder().decodeBuffer(privateKey);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);

        RSAPrivateKey priKey = (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE, priKey);

        String outStr = new String(cipher.doFinal(inputStr));

        return outStr;

    }







    @Test

    public void test() throws Exception {

        sdfasd dd = new sdfasd();

        String    publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANL378k3RiZHWx5AfJqdH9xRNBmD9wGD2iRe41HdTNF8RUhNnHit5NpMNtGL0NPTSSpPjjI1kJfVorRvaQerUgkCAwEAAQ==";

        //  String  publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANL378k3RiZHWx5AfJqdH9xRNBmD9wGD2iRe41HdTNF8RUhNnHit5NpMNtGL0NPTSSpPjjI1kJfVorRvaQerUgkCAwEAAQ==";

        System.out.println(dd.encrypt("123456",publicKey));





    }



    @Test

    public void testjiemi() throws Exception {

        String mima="RfhndH/hd6sH10ce/kVguIsldj2NPFHw62Hm90SuhI1lssrUvV+KNFMJ0+OMx6L0I09aeA/lI0LphPHyTUIF7w==";

        String   privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+Ue/gUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";

        // String   privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+UegUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";

        sdfasd dd = new sdfasd();

        System.out.println(dd.decrypt(mima,privateKey));

    }

}


