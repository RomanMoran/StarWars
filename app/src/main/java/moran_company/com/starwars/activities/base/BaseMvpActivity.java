package moran_company.com.starwars.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import moran_company.com.starwars.base_mvp.BaseMvp;


/**
 * Created by roman on 12.03.2018.
 */


public abstract class BaseMvpActivity<P extends BaseMvp.Presenter> extends BaseActivity {
    private static final String TAG = BaseMvpActivity.class.getName();


    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
    }
}
