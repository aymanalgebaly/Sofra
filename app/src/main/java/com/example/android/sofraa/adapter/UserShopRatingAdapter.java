package com.example.android.sofraa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.restaurant_reviews.Datum;
import com.example.android.sofraa.data.model.restaurant_reviews.Restaurant;

import java.util.List;


public class UserShopRatingAdapter extends RecyclerView.Adapter<UserShopRatingAdapter.ratingViewHolder> {


    private Context context;
    private List<Datum>datumList;

    public UserShopRatingAdapter(List<Datum> datumList) {
        this.datumList = datumList;
    }

    private List<Restaurant>restaurantList;


    public UserShopRatingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ratingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_user_resturant_rating, viewGroup, false);
        return  new ratingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ratingViewHolder holder, int i) {

        Datum datum = datumList.get(i);

        holder.tx_name.setText(datum.getClient().getName());
        holder.tx_comment.setText(datum.getComment());
        holder.tx_date.setText(datum.getCreatedAt());
        holder.ratingBar.setNumStars(datum.getRestaurant().getRate());
    }

    @Override
    public int getItemCount() {
        return datumList != null ?datumList.size():0;
    }

    public void sendToAdapter(List<Datum> datumList) {
        this.datumList = datumList;
    }

    class ratingViewHolder extends RecyclerView.ViewHolder {

        TextView tx_name,tx_comment,tx_date;
        RatingBar ratingBar;
        public ratingViewHolder(@NonNull View itemView) {
            super(itemView);

            tx_name = itemView.findViewById(R.id.rating_person_name);
            tx_comment = itemView.findViewById(R.id.buy_rating_desc);
            tx_date= itemView.findViewById(R.id.buy_rating_date);

            ratingBar = itemView.findViewById(R.id.buy_rating);
        }
    }

}
