package cp;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by chenpeng on 2017/6/1.
 */
public class DESedeCoder {
    public static final String KEY_ALGORITHM = "DESede";
    public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
    private static Key toKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESedeKeySpec dks = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generateSecret(dks);
    }
    public static byte[] decrypt(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,k);
        return cipher.doFinal(data);
    }
    public static byte[] encrypt(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,k);
        return cipher.doFinal(data);
    }
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(168);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {
        String inputStr = "DESede";
        byte[] inputData = inputStr.getBytes();
        System.err.println("原文:\t"+inputStr);
        byte[] key = DESedeCoder.initKey();
        System.err.println("密钥:\t"+ Base64.encodeBase64String(key));
        inputData = DESedeCoder.encrypt(inputData,key);
        System.err.println("加密后:\t"+Base64.encodeBase64String(inputData));
        byte[] outputData = DESedeCoder.decrypt(inputData,key);
        String outputStr = new String(outputData);
        System.err.println("解密后:\t"+outputStr);
        System.err.println(inputStr.equals(outputStr));
    }
}
