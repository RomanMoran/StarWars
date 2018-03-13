package moran_company.com.starwars.activities.main;

import moran_company.com.starwars.base_mvp.BasePresenterImpl;

/**
 * Created by roman on 13.03.2018.
 */

public class MainPresenter extends BasePresenterImpl<MainMvp.View> implements MainMvp.Presenter {
    public MainPresenter(MainMvp.View view) {
        super(view);
    }

    @Override
    public void loadPeople() {
        mView.setProgressIndicator(true);
        getApiClient()
                .getCharacters()
                .subscribe(example -> {
                    if (isExistsView())
                        mView.showPeople(example.getCharacters());
                }, this::onError, this::onComplete);
    }
}
