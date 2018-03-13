package moran_company.com.starwars.utility;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import moran_company.com.starwars.R;


/**
 * Created by roman on 12.03.2018.
 */

public class DialogUtility {


    public static Dialog getWaitDialog(Context context, String text, boolean isShow) {
        return getWaitDialog(context, text, isShow, false);
    }

    public static Dialog getWaitDialog(Context context, String text, boolean isShow, boolean cancelable) {
        Dialog dialog = new Dialog(context, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_wait);
        dialog.setCancelable(cancelable);
        //CircularProgressView progressBarCircular = (CircularProgressView) dialog.findViewById(R.id.progressBarCircular);

        TextView textTextView =  dialog.findViewById(R.id.textTextView);
        if (text != null && text.length() != 0) {
            textTextView.setVisibility(View.VISIBLE);
            textTextView.setText(text);
        } else {
            textTextView.setVisibility(View.GONE);
        }
        try {
            if (isShow) {
                try {
                    dialog.show();
                } catch (Exception ignored) {

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dialog;
    }
    public static void closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
