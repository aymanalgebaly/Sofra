package com.example.android.sofraa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.user_offers.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserOffersAdapter extends RecyclerView.Adapter<UserOffersAdapter.ViewHolderUserOffers> {

    private Context context;
    private List<Datum>datumList;

    public UserOffersAdapter() {
    }

    public UserOffersAdapter(Context context) {
        this.context = context;
    }

    public void sendToAdapterData(List<Datum>datumList){
        this.datumList = datumList;
    }


    @NonNull
    @Override
    public ViewHolderUserOffers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_shop_offers, viewGroup, false);
        return new ViewHolderUserOffers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUserOffers holder, int position) {

        Datum datum = datumList.get(position);

        holder.txt_offer_time_user.setText(datum.getEndingAt());
        holder.txt_offer_price_user.setText(datum.getPrice());
        holder.txt_title_user.setText(datum.getName());
        holder.txt_description_user.setText(datum.getDescription());

        Picasso.get().load(datum.getPhoto()).into(holder.imageView_user);

    }

    @Override
    public int getItemCount() {
        return datumList != null ? datumList.size():0;
    }

    public class ViewHolderUserOffers extends RecyclerView.ViewHolder {

        ImageView imageView_user;
        TextView txt_description_user,txt_title_user,txt_offer_time_user,txt_offer_price_user;

        public ViewHolderUserOffers(@NonNull View itemView) {
            super(itemView);

            imageView_user = itemView.findViewById(R.id.user_foodList_image_offer);
            txt_description_user = itemView.findViewById(R.id.txt_description_offer);
            txt_title_user = itemView.findViewById(R.id.user_foodList_name_offer);
            txt_offer_price_user = itemView.findViewById(R.id.textView18_offer);
            txt_offer_time_user = itemView.findViewById(R.id.user_foodList_description_offer);
        }
    }
}
