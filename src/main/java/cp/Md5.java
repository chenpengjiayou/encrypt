package cp;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenpeng on 2017/5/25.
 */
public class Md5 {
    public static void main(String[] args) throws Exception {
        /*byte[] input = "md5".getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(input), md);
        dis.read(input, 0, input.length);
        byte[] output = dis.getMessageDigest().digest();
        dis.close();
        System.out.println(new String(output));*/
        byte[] input = "md5".getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestOutputStream dos = new DigestOutputStream(new ByteArrayOutputStream(),md);
        dos.write(input,0,input.length);
        byte[] output = dos.getMessageDigest().digest();
        dos.flush();
        dos.close();

        String s = Md5.stringMD5("cp");
        System.out.println(s);
        s = EncoderByMd5("cp");
        System.out.println(s);
        s = Md5.getMD5("cp");
        System.out.println(s);
    }

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    public static String getMD5(String str) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值

            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误");
        }
    }
    public static String output(){

        return "";
    }

    public static String stringMD5(String input) {

        try {

            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");


            // 输入的字符串转换成字节数组

            byte[] inputByteArray = input.getBytes();


            // inputByteArray是输入字符串转换得到的字节数组

            messageDigest.update(inputByteArray);


            // 转换并返回结果，也是字节数组，包含16个元素

            byte[] resultByteArray = messageDigest.digest();


            // 字符数组转换成字符串返回

            return byteArrayToHex(resultByteArray);


        } catch (NoSuchAlgorithmException e) {

            return null;

        }
    }

    public static String byteArrayToHex(byte[] byteArray) {

        // 首先初始化一个字符数组，用来存放每个16进制字符

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））

        char[] resultCharArray = new char[byteArray.length * 2];


        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去

        int index = 0;

        for (byte b : byteArray) {

            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];

            resultCharArray[index++] = hexDigits[b & 0xf];

        }


        // 字符数组组合成字符串返回

        return new String(resultCharArray);
    }
}
