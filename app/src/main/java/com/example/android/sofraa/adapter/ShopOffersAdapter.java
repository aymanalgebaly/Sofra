package com.example.android.sofraa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.offers.OfferData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopOffersAdapter extends RecyclerView.Adapter<ShopOffersAdapter.ViewHolderShopOffers> {

    private Context context;
    private List<OfferData>datumList;

    public ShopOffersAdapter(List<OfferData> datumList1) {
        this.datumList = datumList1;
    }

    public ShopOffersAdapter() {
    }

    public ShopOffersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderShopOffers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_shop_offers, viewGroup, false);
        return new ViewHolderShopOffers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderShopOffers holder, int position) {

        OfferData offerData = datumList.get(position);

        holder.txt_description.setText(offerData.getDescription());
        holder.txt_title.setText(offerData.getName());
        holder.txt_offer_time.setText(offerData.getStartingAt());
        holder.txt_offer_price.setText(offerData.getEndingAt());

        Picasso.get().load(offerData.getPhoto()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datumList != null ? datumList.size():0;
    }

    public void setOffersToList(List<OfferData> data) {
        this.datumList = data;
    }

    public class ViewHolderShopOffers extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txt_description,txt_title,txt_offer_time,txt_offer_price;
        public ViewHolderShopOffers(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.user_foodList_image_offer);
            txt_description = itemView.findViewById(R.id.txt_description_offer);
            txt_title = itemView.findViewById(R.id.user_foodList_name_offer);
            txt_offer_price = itemView.findViewById(R.id.textView18_offer);
            txt_offer_time = itemView.findViewById(R.id.user_foodList_description_offer);
        }
    }
}
