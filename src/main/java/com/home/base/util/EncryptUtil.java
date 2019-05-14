package com.home.base.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
/**
 * @author chenxf
 * @date 3/8/2018
 * @description
 */
public class EncryptUtil {

  private static String key = "ABCDKJDD";

  /**
   * 加密逻辑方法
   *
   * @param message
   * @param key
   * @return
   * @throws Exception
   */
  private static byte[] encryptProcess(String message, String key) throws Exception {
    return initCipher(Cipher.ENCRYPT_MODE).doFinal(message.getBytes("UTF-8"));
  }

  public static Cipher initCipher(int mode) throws Exception{
    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
    IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
    cipher.init(mode, secretKey, iv);
    return cipher;
  }

  /**
   * 解密逻辑方法
   *
   * @param message
   * @param key
   * @return
   * @throws Exception
   */
  private static String decryptProcess(String message, String key) throws Exception {
    byte[] retByte = initCipher(Cipher.DECRYPT_MODE).doFinal(convertHexString(message));
    return new String(retByte);
  }

  /**
   * 16进制数组数转化
   *
   * @param ss
   * @return
   */
  private static byte[] convertHexString(String ss) throws Exception {
    byte digest[] = new byte[ss.length() / 2];
    for (int i = 0; i < digest.length; i++) {
      String byteString = ss.substring(2 * i, 2 * i + 2);
      int byteValue = Integer.parseInt(byteString, 16);
      digest[i] = (byte) byteValue;
    }
    return digest;
  }

  /**
   * 十六进制数转化
   *
   * @param b
   * @return
   * @throws Exception
   */
  private static String toHexString(byte b[]) throws Exception {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      String plainText = Integer.toHexString(0xff & b[i]);
      if (plainText.length() < 2) {
        plainText = "0" + plainText;
      }
      hexString.append(plainText);
    }

    return hexString.toString();
  }

  public static String encrypt(String message) {
    return encrypt(message, key);
  }

  /**
   * 加密方法
   */
  public static String encrypt(String message, String key) {
    String enStr = null;
    try {
      String orignStr = java.net.URLEncoder.encode(message, "utf-8");
      enStr = toHexString(encryptProcess(orignStr, key));
    } catch (Exception e) {
    }
    return enStr;
  }

  public static String decrypt(String message) {
    return decrypt(message, key);
  }

  /**
   * 解密方法
   */
  public static String decrypt(String message, String key) {
    try {
      return java.net.URLDecoder.decode(decryptProcess(message, key), "utf-8");
    } catch (Exception e) {
    }
    return null;
  }

  public static String decrypt(String key, String iv, String encData) throws Exception {
    return decrypt( Base64.decode(key), Base64.decode(iv), Base64.decode(encData));
  }

  public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
    AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
    cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
    //解析解密后的字符串
    return new String(cipher.doFinal(encData),"UTF-8");
  }
  /**
   * 测试Main方法
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
//    byte[] encrypData = Base64.decode("66X2n3MbsYsFC7CS/ILgTxUNcbPVwuJw/mLxDoxAlxno/YAEB1zjFGEi3kAyUGyz1C1AQcssk9exnxnP4g7zIGsaJNXJb6Hq0f7jJuwrHk8eK60oa/ET3QEOQB0aSD/X2Y/3cfSSaokE7f0LFz9DmnWXTjL9gEaZxwaSx5fBvar1Fhst22KhTrWD7RE5GMj7zelhBj5/EhE8FN7k7Kl2JA==");
//    byte[] ivData = Base64.decode("VHbS0naZhsClfiXaXiG64g==");
//    byte[] sessionKey = Base64.decode("HyE7/XzIu905obQ0iGchUA==");
//    String data="66X2n3MbsYsFC7CS/ILgTxUNcbPVwuJw/mLxDoxAlxno/YAEB1zjFGEi3kAyUGyz1C1AQcssk9exnxnP4g7zIGsaJNXJb6Hq0f7jJuwrHk8eK60oa/ET3QEOQB0aSD/X2Y/3cfSSaokE7f0LFz9DmnWXTjL9gEaZxwaSx5fBvar1Fhst22KhTrWD7RE5GMj7zelhBj5/EhE8FN7k7Kl2JA==";
//    String iv="VHbS0naZhsClfiXaXiG64g==";
//    String key="HyE7/XzIu905obQ0iGchUA==";
//    System.out.println(decrypt(sessionKey,ivData,encrypData));
//
//    System.out.println(decrypt(key,iv,data));
  }
}
