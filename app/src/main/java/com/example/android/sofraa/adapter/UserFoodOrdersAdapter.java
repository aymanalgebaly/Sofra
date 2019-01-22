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
import com.example.android.sofraa.data.model.list_of_restaurants.Datum;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserFoodListFragment;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UserFoodOrdersAdapter extends RecyclerView.Adapter<UserFoodOrdersAdapter.ordersViewHolder> {


    private final Context context;
    private List<Datum> dataList;

    onItemClickListner onItemClickListner;

    public UserFoodOrdersAdapter(Context context) {
        this.context = context;
    }


    public void sendDataToAdapter(List<Datum> data) {
        this.dataList = data;
    }

    public void setOnItemClickListner(UserFoodOrdersAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }


    public interface onItemClickListner {
        void onClick(Datum restaurant);//pass your object types.
    }


    @NonNull
    @Override
    public ordersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_user_food_orders, viewGroup, false);
        return new ordersViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ordersViewHolder holder, int i) {

        final Datum datum = dataList.get(i);

        holder.tx_name.setText(datum.getName());
        holder.tx_delevry_cost.setText(datum.getDeliveryCost());
        holder.tx_minCharger.setText(datum.getMinimumCharger());

        Picasso.get().load(datum.getPhotoUrl()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListner.onClick(datum);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }


    class ordersViewHolder extends RecyclerView.ViewHolder {

        TextView tx_name, tx_delevry_cost, tx_minCharger,tx_restuarant_descrip;
        ImageView img;

        public ordersViewHolder(@NonNull View itemView) {
            super(itemView);

            tx_name = itemView.findViewById(R.id.req_food_shop_name);
            tx_delevry_cost = itemView.findViewById(R.id.req_food_order_cost);
            tx_minCharger = itemView.findViewById(R.id.req_food_lowest_order);
            img = itemView.findViewById(R.id.req_food_img);
            tx_restuarant_descrip = itemView.findViewById(R.id.req_food_desc);

        }
    }

}
