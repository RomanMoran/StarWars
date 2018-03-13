package moran_company.com.starwars.utility_api;


import io.reactivex.Flowable;
import io.reactivex.Single;
import moran_company.com.starwars.data.Example;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by roman on 12.03.2018.
 */

public interface ApiService {

    @GET(ApiConstants.API_GET_PEOPLE)
    Flowable<Example> getPeople();


}
