package pers.yangws.androiddevtools.helper;

import android.database.Cursor;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by 1yyg-安卓 on 2015/12/24.
 */
public class CloseHelper {

    private CloseHelper() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignore.
            }
        }
    }


    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

}
