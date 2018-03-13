package moran_company.com.starwars;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import moran_company.com.starwars.utility.DebugUtility;

/**
 * Created by roman on 12.03.2018.
 */

public final class StarwarsApplication extends Application {

    private static final String TAG = StarwarsApplication.class.getName();

    private static StarwarsApplication instance;

    public static StarwarsApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static boolean hasNetwork() {
        boolean hasNetwork = instance.checkIfHasNetwork();
        DebugUtility.logTest(TAG, "hasNetwork = " + hasNetwork);
        return hasNetwork;
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
