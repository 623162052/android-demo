package cs.plugins.storage.sqlite;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileService {

    private Context context;

    /**
     * 构造方法
     */
    public FileService(Context context){
        this.context = context;
    }

    /**
     * 写入SD卡内容
     */
    public boolean saveToSdc(String fileName, String content) {

        FileOutputStream fos = null;

        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                fos = new FileOutputStream(file);
                fos.write(content.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return false;
    }


    /**
     * 读取SD卡内容
     */
    public String getContentSdc(String fileName) {
        FileInputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            try {
                is = new FileInputStream(file);
                int len = 0;
                byte[] data = new byte[1024];
                while ((len = is.read(data)) != -1) {
                    baos.write(data, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return new String(baos.toByteArray());
    }
}
