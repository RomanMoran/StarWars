package moran_company.com.starwars.activities.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import moran_company.com.starwars.R;
import moran_company.com.starwars.activities.base.BaseMvpActivity;
import moran_company.com.starwars.adapter.CharactersAdapter;
import moran_company.com.starwars.data.Character;

public class MainActivity extends BaseMvpActivity<MainMvp.Presenter> implements MainMvp.View {

    @BindView(R.id.characters)
    RecyclerView characters;

    private CharactersAdapter charactersAdapter = new CharactersAdapter();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        characters.setAdapter(charactersAdapter);
        mPresenter.loadPeople();
        charactersAdapter.setOnItemClickListner(character -> {
            showBottomSheetDialog(character);
        });
    }

    @Override
    protected MainMvp.Presenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void showPeople(List<Character> people) {
        charactersAdapter.setItems(people);
    }
}
