/**
 *
 */
package pers.yangws.androiddevtools.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @作者: 杨文松
 * @时间: 2015年12月11日
 * @描述:
 */
public class Md5Helper {

    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }

        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        int length = md5code.length();
        for (int i = 0; i < 32 - length; i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String getBytesMd5(byte[] bytes) {
        StringBuilder sb = new StringBuilder("");
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(bytes, 0, bytes.length);


            byte[] jimbyte = md.digest();
            for (byte b : jimbyte) {
                int bdata = b & 0xff;
                String hex = Integer.toHexString(bdata);
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                sb.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sb.toString();
    }


    public static String getFileMd5(File file) {
        String filepath = file.getPath();

        return getFileMd5(filepath);
    }


    //获取文件的md5
    public static String getFileMd5(String file) {
        StringBuilder sb = new StringBuilder("");
        try {
            MessageDigest md = MessageDigest.getInstance("md5");

            FileInputStream fis = new FileInputStream(file);
            byte buffer[] = new byte[2048];
            int len = fis.read(buffer);
            while (len != -1) {
                md.update(buffer, 0, len);
                len = fis.read(buffer);
            }
            fis.close();

            byte[] jimbyte = md.digest();
            for (byte b : jimbyte) {
                int bdata = b & 0xff;
                String hex = Integer.toHexString(bdata);
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                sb.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }


}
