package moran_company.com.starwars.utility;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by roman on 12.03.2018.
 */

public class Utility {
    private final static String TAG = Utility.class.getName();

    public static void showToast(Context context, int textId) {
        String text;
        try {
            text = context.getString(textId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        showToast(context, text);
    }



    public static void showToast(Context context, String text) {
        try {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }



}
