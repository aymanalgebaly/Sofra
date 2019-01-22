package com.example.android.sofraa.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolderBasketAdapter> {

    private Context context;
    private List<ItemFoodData>itemFoodDataList;

    public void setDataBasketAdapter(List<ItemFoodData> itemFoodDataList) {
        this.itemFoodDataList = itemFoodDataList;
    }

    public BasketAdapter(Context context) {
        this.context = context;
    }

    public BasketAdapter() {
    }

    @NonNull
    @Override
    public ViewHolderBasketAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_user_basket, viewGroup, false);
        return new ViewHolderBasketAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBasketAdapter holder, int i) {

        ItemFoodData itemFoodData = itemFoodDataList.get(i);

        int count = itemFoodData.getCounter();
        holder.count_num.setText(String.valueOf(count));
        String name = itemFoodData.getName();
        holder.txt_title.setText(name);
        String price = itemFoodData.getPrice();
        holder.txt_salary_num.setText(price);

        String photoUrl = itemFoodData.getPhotoUrl();
        Picasso.get().load(photoUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemFoodDataList != null ? itemFoodDataList.size():0;
    }

    public class ViewHolderBasketAdapter extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txt_title,txt_salary_num,txt_total_num,count_num;
        Button btn_plus,btn_min;
        public ViewHolderBasketAdapter(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.basket_img);

            txt_salary_num = itemView.findViewById(R.id.txt_basket_salary_num);
            txt_title = itemView.findViewById(R.id.txt_title_basket);
            txt_total_num = itemView.findViewById(R.id.txt_total_basket_num);
            count_num = itemView.findViewById(R.id.text_number_basket);
        }
    }
}
