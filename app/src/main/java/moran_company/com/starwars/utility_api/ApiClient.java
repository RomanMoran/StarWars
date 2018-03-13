package moran_company.com.starwars.utility_api;


import io.reactivex.Flowable;
import io.reactivex.Single;
import moran_company.com.starwars.data.Example;

/**
 * Created by roman on 12.03.2018.
 */

public class ApiClient {

    private static final String TAG = ApiClient.class.getName();


    private static ApiClient instance;

    private ApiService apiService = RetrofitUtils.getInstance().getApiService();

    protected ApiClient() {

    }

    public static ApiClient getInstance() {
        if (instance == null) instance = new ApiClient();
        return instance;
    }

    public Flowable<Example> getCharacters() {
        return apiService.getPeople()
                .compose(RxUtils.applyFlowableSchedulers());
    }


}
