package com.hd.general;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;



/**
 * DES��һ�ֶԳƼ����㷨����ν�ԳƼ����㷨�������ܺͽ���ʹ����ͬ��Կ���㷨��
 * 
 * @author xiangze
 *
 */
public class DESUtil {

    private static Key key;
    // ������Կkey
    private static String KEY_STR = "myKey";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    static {
        try {
            // ����DES�㷨����
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // ����SHA1��ȫ����
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // ��������Կ����
            secureRandom.setSeed(KEY_STR.getBytes());
            // ��ʼ������SHA1���㷨����
            generator.init(secureRandom);
            // ������Կ����
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ��ȡ���ܺ����Ϣ
     * 
     * @param str
     * @return
     */
    public static String getEncryptString(String str) {
        // ����BASE64���룬����byte[]��ת����String
        //BASE64Encoder base64encoder = new BASE64Encoder();
        Base64 base64encoder = new Base64();
    	try {
            // ��UTF8����
            byte[] bytes = str.getBytes(CHARSETNAME);
            // ��ȡ���ܶ���
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // ��ʼ��������Ϣ
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // ����
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[]to encode�õ�String������
            bytes = base64encoder.encode(doFinal);
            String encodeString = new String(bytes);
            return encodeString;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    /**
     * ��ȡ����֮�����Ϣ
     * 
     * @param str
     * @return
     */
    public static String getDecryptString(String str) {
        // ����BASE64���룬����byte[]��ת����String
        //BASE64Decoder base64decoder = new BASE64Decoder();
        Base64 base64decoder = new Base64();
        try {
            // ���ַ���decode��byte[]
            //byte[] bytes = base64decoder.decodeBuffer(str);
        	 byte[] bytes = str.getBytes();
        	 bytes = base64decoder.decode(bytes);
        	// ��ȡ���ܶ���
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // ��ʼ��������Ϣ
            cipher.init(Cipher.DECRYPT_MODE, key);
            // ����
            byte[] doFinal = cipher.doFinal(bytes);
            // ���ؽ���֮�����Ϣ
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getEncryptString("201527010214"));
        System.out.println(getDecryptString("bIAMMUafM4nccEUWL21q3w=!"));
    }

}