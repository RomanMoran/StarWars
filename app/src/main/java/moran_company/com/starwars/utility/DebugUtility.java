package moran_company.com.starwars.utility;

import android.util.Log;

/**
 * Created by roman on 12.03.2018.
 */

public class DebugUtility {
    private static final String PRE_TAG = "TEST";


    public static void logTest(String tag, String msg) {
        tag = PRE_TAG + " " + tag;
        if (msg == null)
            msg = "";
        Log.e(tag, msg);
    }



}