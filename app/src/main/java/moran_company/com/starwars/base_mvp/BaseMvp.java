package moran_company.com.starwars.base_mvp;


/**
 * Created by roman on 12.03.2018.
 */

public interface BaseMvp {

    interface View {

        void onNetworkDisable();

        void showToast(String text);

        void showToast(int textId);

        void setProgressIndicator(boolean active);


    }

    interface Presenter {
        void onDestroy();
    }

    interface InteractorFinishedListener {

        void onError(String text);

        void onError(int textId);

        void onError(Throwable throwable);

        void onNetworkDisable();

        void onComplete();

        void setProgressIndicator(boolean active);
    }
}

