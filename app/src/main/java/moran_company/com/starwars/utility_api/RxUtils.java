package moran_company.com.starwars.utility_api;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moran_company.com.starwars.utility.DebugUtility;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


/**
 * Created by roman on 12.03.2018.
 */

public class RxUtils {

    public static final String TAG = RxUtils.class.getName();


    public static <T> FlowableTransformer<T, T> applyFlowableSchedulers() {
        return Flowable -> Flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
