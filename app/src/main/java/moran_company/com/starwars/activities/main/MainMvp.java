package moran_company.com.starwars.activities.main;

import java.util.List;

import moran_company.com.starwars.base_mvp.BaseMvp;
import moran_company.com.starwars.data.Result;

/**
 * Created by roman on 13.03.2018.
 */

public interface MainMvp {

    interface View extends BaseMvp.View{
        void showPeople(List<Result> people);
    }

    interface Presenter extends BaseMvp.Presenter{
        void loadPeople();
    }

}
