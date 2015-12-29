package pers.yangws.androiddevtools.helper.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


/***
 * @author 杨文松
 * @描述 文件帮助工具类
 * @时间 2015-12-06
 */
public class FileHelper {


    /**
     * 获取文件名称
     *
     * @param path
     */
    public static String getFileName(String path) {
        return new File(path).getName();
    }


    /***
     * 获取文件的后缀名
     *
     * @param path
     */
    public static String getSuffixType(String path) {
        if (TextUtils.isEmpty(path)) return null;
        File file = new File(path);
        String filename = file.getName();

        return filename.substring(filename.lastIndexOf(".") + 1, filename.length());
    }


    /**
     * 保存文件
     */
    public static boolean saveFile(Context context, String filepath, byte[] contents) {
        boolean isSave = false;
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(filepath, Context.MODE_PRIVATE);
            fos.write(contents);
            isSave = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            isSave = false;
        } catch (IOException e) {
            e.printStackTrace();
            isSave = false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fos = null;
            }
        }
        return isSave;
    }
    
    
    /**
     * 移动文件
     * @param srcFile
     * @param targetFile
     * */
    public void moveFile(File srcFile, File targetFile){
        
    }
    


    /**
     * 判断是否已经插入SD卡
     */
    public static boolean isMediaMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }


}
