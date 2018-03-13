package moran_company.com.starwars.utility_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import moran_company.com.starwars.utility.DebugUtility;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by roman on 12.03.2018.
 */

public class RetrofitUtils {

    public static final String API_BASE_URL = "https://swapi.co/api/";
    private static final String TAG = RetrofitUtils.class.getName();
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;

    private static RetrofitUtils instance;
    private static Gson gson;
    private ApiService apiService;


    private RetrofitUtils() {
        initApiService();
    }

    public static RetrofitUtils getInstance() {
        if (instance == null) instance = new RetrofitUtils();
        return instance;
    }

    public static Gson getGson() {
        return new GsonBuilder()

                .create();
    }

    private ApiService initApiService() {
        return apiService = provideRetrofit(API_BASE_URL).create(ApiService.class);
    }

    private Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient provideOkHttpClient() {
        return provideOkHttpBuilder().build();
    }

    private OkHttpClient.Builder provideOkHttpBuilder() {

        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(provideHttpLoggingInterceptor());

    }

    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> DebugUtility.logTest(TAG, message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpLoggingInterceptor;
    }


    public ApiService getApiService() {
        return apiService != null ? apiService : initApiService();
    }


}
