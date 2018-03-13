package moran_company.com.starwars.activities.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moran_company.com.starwars.R;
import moran_company.com.starwars.StarwarsApplication;
import moran_company.com.starwars.base_mvp.BaseMvp;
import moran_company.com.starwars.data.Result;
import moran_company.com.starwars.fragments.BottomSheetFragment;
import moran_company.com.starwars.utility.DialogUtility;
import moran_company.com.starwars.utility.Utility;


/**
 * Created by roman on 12.03.2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseMvp.View {
    private static final String TAG = BaseActivity.class.getName();


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Nullable
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @Nullable
    @BindView(R.id.titleBar)
    TextView titleBar;


    private FragmentManager mCurrentFragmentManager;
    private Dialog mDialog;
    private long mBackPressed;
    private float lastTranslate = 0.0f;

    public static boolean newInstance(Context context, final Class<? extends AppCompatActivity> activityClass) {
        return newInstance(context, activityClass, false);
    }

    public static boolean newInstance(Context context, final Class<? extends AppCompatActivity> activityClass, boolean clearBackStack) {
       /* if (activityClass == context.getClass()) {
            return false;
        }*/
        Intent intent = new Intent(context, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        if (clearBackStack)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ActivityCompat.startActivity(context, intent, null);
        return true;
    }

    public abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutResId();
        if (layoutResId != 0) {
            setContentView(layoutResId);
            ButterKnife.bind(this);

        }


        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            //get primary theme color
            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int color = typedValue.data;
            mToolBar.setBackgroundColor(color);
        }
        mDialog = DialogUtility.getWaitDialog(this, this.getString(R.string.wait_loading), false);
        getCurrentFragmentManager();
    }

    public void showBottomSheetDialog(Result result){
        BottomSheetFragment bottomSheetFragment = BottomSheetFragment.newInstance(result);
        bottomSheetFragment.show(getCurrentFragmentManager(),BottomSheetFragment.TAG);
    }


    public FragmentManager getCurrentFragmentManager() {
        if (mCurrentFragmentManager == null)
            mCurrentFragmentManager = getSupportFragmentManager();
        return mCurrentFragmentManager;
    }


    public void showDialog() {
        if (!isFinishing()) {
            if (mDialog != null) {
                hideDialog();
                mDialog.show();
            }
        }
    }

    public void hideDialog() {
        DialogUtility.closeDialog(mDialog);
    }

    @Override
    public void onNetworkDisable() {
        showToast(R.string.network_disable_error);
    }


    @Override
    public void showToast(String text) {
        runOnUiThread(() -> Utility.showToast(this, text));
    }

    @Override
    public void showToast(int textId) {
        runOnUiThread(() -> Utility.showToast(this, textId));
    }

    @Override
    public void setProgressIndicator(boolean active) {
        runOnUiThread(() -> {
            if (active)
                showDialog();
            else
                hideDialog();
        });
    }

    public boolean haveFragmentInBackStack() {
        return mCurrentFragmentManager != null ? mCurrentFragmentManager.getBackStackEntryCount() > 0 : false;
    }

    public boolean haveActivityInBackStack() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = activityManager.getRunningTasks(10);
        if (taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (!haveFragmentInBackStack() && !haveActivityInBackStack()) {
            if (mBackPressed + 2000 > System.currentTimeMillis()) {
                finish();
            } else
                Utility.showToast(getBaseContext(), R.string.go_exit);
            mBackPressed = System.currentTimeMillis();
        } else
            super.onBackPressed();
    }

    public void closeKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void showFragment(int container, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = getCurrentFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(container, fragment, tag);
        if (addToBackStack) ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
    }


    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null)
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public StarwarsApplication getStarwarsApplication() {
        return (StarwarsApplication) getApplication();
    }


}
