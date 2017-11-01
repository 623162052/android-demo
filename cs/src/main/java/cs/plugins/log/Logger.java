package cs.plugins.log;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger
 */
public class Logger {
    private static String FILE_NAME = "Log.log";
    private static SimpleDateFormat FILE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat MESSAGE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * INFO
     */
    public static void i(String TAG, String message) {
        Log.i(TAG, message);
//        writeFile(TAG, message);
    }

    /**
     * DEBUG
     */
    public static void d(String TAG, String message) {
        Log.d(TAG, message);
//        writeFile(TAG, message);
    }

    /**
     * ERROR
     */
    public static void e(String TAG, String message) {
        Log.e(TAG, message);
//        writeFile(TAG, message);
    }

    /**
     * 打开日志文件并写入日志
     */
    private static void writeFile(String TAG, String text) {
        boolean mediaMounted = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());

        if (mediaMounted) {
            Date now = new Date();
            String yyyyMmDd = FILE_FORMAT.format(now);
            String message = MESSAGE_FORMAT.format(now) + "---" + TAG + "---" + text;

            // Environment.DIRECTORY_PICTURES
            // File dir = Environment.getExternalStoragePublicDirectory();
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            Log.d(TAG, dir.getPath() + " - " + dir.getAbsolutePath());
            File file = new File(dir, yyyyMmDd + FILE_NAME);
            try {
                // true： 是否追加
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bufWriter = new BufferedWriter(fw);
                bufWriter.write(message);
                bufWriter.newLine();
                bufWriter.close();
                fw.close();
            } catch (IOException e) {
                // ;e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
    }

}
