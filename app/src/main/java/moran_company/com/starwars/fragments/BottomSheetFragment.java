package moran_company.com.starwars.fragments;

/**
 * Created by roman on 13.03.2018.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moran_company.com.starwars.R;
import moran_company.com.starwars.data.Character;
import moran_company.com.starwars.utility.Utility;

@FragmentWithArgs
public class BottomSheetFragment extends BottomSheetDialogFragment {

    public static final String TAG = BottomSheetFragment.class.getName();

    @Arg(bundler = ParcelerArgsBundler.class)
    Character character;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.mass)
    TextView mass;
    @BindView(R.id.gender)
    TextView gender;

    @BindView(R.id.starshipsCount)
    TextView starshipsCount;
    @BindView(R.id.filmsCount)
    TextView filmsCount;
    @BindView(R.id.vehiclesCount)
    TextView vehiclesCount;

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    public static BottomSheetFragment newInstance(Character character) {
        return new BottomSheetFragmentBuilder(character).build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        fillFields();
    }

    private void fillFields() {
        name.setText(character.getName());
        height.setText(character.getHeight());
        mass.setText(character.getMass());
        gender.setText(character.getGender());

        int starshipsSize = character.getStarships() == null ? 0 : character.getStarships().size();
        int filmsSize = character.getFilms() == null ? 0 : character.getFilms().size();
        int vehiclesSize = character.getVehicles() == null ? 0 : character.getVehicles().size();

        starshipsCount.setText(Integer.toString(starshipsSize));
        filmsCount.setText(Integer.toString(filmsSize));
        vehiclesCount.setText(Integer.toString(vehiclesSize));

    }


    @OnClick(R.id.close)
    void close(){
        dismiss();
    }

    @OnClick(R.id.openBrowser)
    void openUrl(){
        if (!TextUtils.isEmpty(character.getUrl())) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(character.getUrl()));
            startActivity(intent);
        }else
            Utility.showToast(getContext(),R.string.site_is_not_exist);

    }


}