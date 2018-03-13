package moran_company.com.starwars.base_mvp;


import moran_company.com.starwars.StarwarsApplication;
import moran_company.com.starwars.utility.DebugUtility;
import moran_company.com.starwars.utility_api.ApiClient;
import retrofit2.HttpException;


/**
 * Created by roman on 12.03.2018.
 */


public abstract class BasePresenterImpl<V extends BaseMvp.View> implements BaseMvp.Presenter, BaseMvp.InteractorFinishedListener {
    public static final String TAG = BasePresenterImpl.class.getName();
    protected V mView;

    private ApiClient apiClient = ApiClient.getInstance();

    public BasePresenterImpl(V view) {
        this.mView = view;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    protected boolean isExistsView() {
        return mView != null;
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onError(String text) {
        DebugUtility.logTest(TAG, "onError " + text);
        if (mView != null) {
            mView.setProgressIndicator(false);
            mView.showToast(text);
        }
    }

    @Override
    public void onError(int textId) {
        DebugUtility.logTest(TAG, "onError " + textId);
        if (mView != null) {
            mView.setProgressIndicator(false);
            mView.showToast(textId);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable != null) {
            if (throwable.getMessage().contains("No address associated") && !StarwarsApplication.hasNetwork()) {
                onNetworkDisable();
                return;
            }
            onError(throwable.getMessage());
        }

    }

    @Override
    public void onComplete() {
        setProgressIndicator(false);
    }


    @Override
    public void setProgressIndicator(boolean active) {
        if (mView != null)
            mView.setProgressIndicator(active);
    }


    @Override
    public void onNetworkDisable() {
        if (mView != null) {
            mView.setProgressIndicator(false);
            mView.onNetworkDisable();
        }
    }


}
