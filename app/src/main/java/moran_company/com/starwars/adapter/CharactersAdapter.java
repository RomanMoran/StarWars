package moran_company.com.starwars.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moran_company.com.starwars.GlideApp;
import moran_company.com.starwars.R;
import moran_company.com.starwars.data.Character;

/**
 * Created by roman on 12.03.2018.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private List<Character> items;

    public CharactersAdapter() {
    }

    public void setItems(List<Character> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(Character character);
    }

    public void setOnItemClickListner(OnItemClickListener onItemClickListner) {
        this.onItemClickListener = onItemClickListner;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_character, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = items.get(position);
        holder.bind(character);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.character)
        TextView character;
        @BindView(R.id.birthYear)
        TextView birthYear;
        @BindView(R.id.gender)
        ImageView gender;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                Character character = items.get(getAdapterPosition());
                if (character != null)
                    onItemClickListener.onClick(items.get(getAdapterPosition()));
            });
        }

        public void bind(Character character) {
            this.character.setText(character.getName());
            birthYear.setText(character.getBirthYear());
            GlideApp.with(itemView.getContext())
                    .load(getDrawableResource(character.getGender()))
                    .centerCrop()
                    .into(gender);
        }

        private int getDrawableResource(String gender) {
            int resourceId = 0;
            switch (gender) {
                case "male":
                    resourceId = R.drawable.ic_human_male;
                    break;
                case "female":
                    resourceId = R.drawable.ic_human_female;
                    break;
            }
            return resourceId;
        }
    }


}
