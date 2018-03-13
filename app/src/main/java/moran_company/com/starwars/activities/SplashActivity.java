package moran_company.com.starwars.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import butterknife.BindView;
import moran_company.com.starwars.GlideApp;
import moran_company.com.starwars.R;
import moran_company.com.starwars.StarwarsApplication;
import moran_company.com.starwars.activities.base.BaseActivity;
import moran_company.com.starwars.activities.main.MainActivity;
import moran_company.com.starwars.utility.DebugUtility;
import moran_company.com.starwars.utility.Utility;
import moran_company.com.starwars.utility_api.ApiConstants;

/**
 * Created by roman on 12.03.2018.
 */

public class SplashActivity extends BaseActivity {

    public static final String TAG = SplashActivity.class.getName();

    @BindView(R.id.splashImage)
    ImageView splashImage;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlideApp.with(this)
                .asBitmap()
                .load(ApiConstants.SPLASH_IMAGE_URL)
                .listener(requestListener)
                .into(splashImage);

    }

    private RequestListener<Bitmap> requestListener = new RequestListener<Bitmap>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
            Utility.showToast(SplashActivity.this, !StarwarsApplication.hasNetwork() ?
                    R.string.network_disable_error : R.string.something_went_wrong);
            return false;
        }

        @Override
        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
            // everything worked out, so probably nothing to do
            new Handler().postDelayed(() -> BaseActivity.newInstance(SplashActivity.this, MainActivity.class, true), 2000);
            return false;
        }
    };
}
