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
import com.example.android.sofraa.data.model.restaurant_orders.RestaurantOrdersData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OldOrdersAdapter extends RecyclerView.Adapter<OldOrdersAdapter.ViewHolderOldOrders> {

    private Context context;
    private List<RestaurantOrdersData>ordersDataList;

    public OldOrdersAdapter(List<RestaurantOrdersData> ordersDataList) {
        this.ordersDataList = ordersDataList;
    }

    public OldOrdersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderOldOrders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.current_order, viewGroup, false);
        return new ViewHolderOldOrders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOldOrders holder, int position) {
        RestaurantOrdersData restaurantOrdersData = ordersDataList.get(position);

        holder.txt_addres.setText(restaurantOrdersData.getClient().getAddress());
        holder.txt_totalNum.setText(restaurantOrdersData.getTotal());
        holder.txt_deliveryNum.setText(restaurantOrdersData.getDeliveryCost());
        holder.txt_price.setText(restaurantOrdersData.getCost());
        holder.txt_name.setText(restaurantOrdersData.getClient().getName());
//        holder.txt_codeNum.setText(restaurantOrdersData.getId());

        Picasso.get().load(restaurantOrdersData.getRestaurant().getPhotoUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return ordersDataList != null ? ordersDataList.size():0;
    }

    public void setDataToAdapter(List<RestaurantOrdersData> data) {
        this.ordersDataList = data;
    }

    public class ViewHolderOldOrders extends RecyclerView.ViewHolder {

        TextView txt_codeNum,txt_name,txt_price,txt_deliveryNum,txt_totalNum,txt_addres;
        ImageView img;

        public ViewHolderOldOrders(@NonNull View itemView) {
            super(itemView);
            txt_codeNum = itemView.findViewById(R.id.txt_code_num_current_order);
            txt_name = itemView.findViewById(R.id.txt_client_name_current_order);
            txt_price = itemView.findViewById(R.id.textView40);
            txt_deliveryNum = itemView.findViewById(R.id.txt_delivery_num_current_order);
            txt_totalNum = itemView.findViewById(R.id.txt_total_num_current_order);
            txt_addres = itemView.findViewById(R.id.txt_address_details_current_order);

            img = itemView.findViewById(R.id.imageView7);
        }
    }
}

