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
import moran_company.com.starwars.data.Result;

/**
 * Created by roman on 12.03.2018.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private List<Result> items;

    public CharactersAdapter() {
    }

    public CharactersAdapter(List<Result> items) {
        this.items = items;
    }

    public List<Result> getItems() {
        return items;
    }

    public void setItems(List<Result> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(Result result);
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
        Result result = items.get(position);
        holder.bind(result);
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
                Result result = items.get(getAdapterPosition());
                if (result != null)
                    onItemClickListener.onClick(items.get(getAdapterPosition()));
            });
        }

        public void bind(Result result) {
            character.setText(result.getName());
            birthYear.setText(result.getBirthYear());
            GlideApp.with(itemView.getContext())
                    .load(getDrawableResource(result.getGender()))
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
