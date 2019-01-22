package com.example.android.sofraa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.shop_products.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopProductsAdapter extends RecyclerView.Adapter<ShopProductsAdapter.ViewHolderShopProducts> {

    private Context context;
    private List<Datum> datumList;

    public ShopProductsAdapter(List<Datum> datumList) {
        this.datumList = datumList;
    }

    public ShopProductsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderShopProducts onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_shop_products, viewGroup, false);
        return new ViewHolderShopProducts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderShopProducts holder, int i) {

        Datum datum = datumList.get(i);
        holder.getTxt_salary_num.setText(datum.getPrice());
        holder.txt_details.setText(datum.getDescription());
        holder.txt_name.setText(datum.getName());

        Picasso.get().load(datum.getPhotoUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return datumList !=null ? datumList.size():0;
    }

    public void setDataToAdapter(List<Datum> datumList) {
        this.datumList = datumList;
    }

    public class ViewHolderShopProducts extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txt_name , txt_details , txt_salary , getTxt_salary_num;
        public ViewHolderShopProducts(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.user_foodList_image);
            txt_name = itemView.findViewById(R.id.user_foodList_name);
            txt_details = itemView.findViewById(R.id.user_foodList_description);
            txt_salary = itemView.findViewById(R.id.textView10);
            getTxt_salary_num = itemView.findViewById(R.id.user_foodList_salary);
        }
    }
}
