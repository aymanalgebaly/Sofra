package com.example.android.sofraa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.restaurant_orders.RestaurantOrdersData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CurrentOrdersAdapter extends RecyclerView.Adapter<CurrentOrdersAdapter.ViewHolderCurrentOrders> {

    private Context context;
    private List<RestaurantOrdersData>ordersDataList;
    private onItemClickListner onItemClickedListner;


    public CurrentOrdersAdapter(List<RestaurantOrdersData> ordersDataList) {
        this.ordersDataList = ordersDataList;
    }
    public interface onItemClickListner {
        void onClick(RestaurantOrdersData restaurantOrdersData);//pass your object types.
    }

    public void onItemClickedListner(CurrentOrdersAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickedListner = onItemClickListner;
    }

    public CurrentOrdersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderCurrentOrders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.old_order, viewGroup, false);
        return new ViewHolderCurrentOrders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCurrentOrders holder, final int position) {
        final RestaurantOrdersData restaurantOrdersData = ordersDataList.get(position);

        holder.txt_addres.setText(restaurantOrdersData.getClient().getAddress());
        holder.txt_totalNum.setText(restaurantOrdersData.getTotal());
        holder.txt_deliveryNum.setText(restaurantOrdersData.getDeliveryCost());
        holder.txt_price.setText(restaurantOrdersData.getCost());
        holder.txt_name.setText(restaurantOrdersData.getClient().getName());
//        holder.txt_codeNum.setText(restaurantOrdersData.getId());

        Picasso.get().load(restaurantOrdersData.getRestaurant().getPhotoUrl()).into(holder.img);

        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RestaurantOrdersData restaurantOrdersData1 = ordersDataList.get(position);
                onItemClickedListner.onClick(restaurantOrdersData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ordersDataList != null ? ordersDataList.size():0;
    }

    public void setDataToAdapter(List<RestaurantOrdersData> data) {
        this.ordersDataList = data;
    }

    public class ViewHolderCurrentOrders extends RecyclerView.ViewHolder {

        TextView txt_codeNum,txt_name,txt_price,txt_deliveryNum,txt_totalNum,txt_addres;
        ImageView img;
        Button btn_confirm,btn_call;

        public ViewHolderCurrentOrders(@NonNull View itemView) {
            super(itemView);
            txt_codeNum = itemView.findViewById(R.id.txt_code_num_old_order);
            txt_name = itemView.findViewById(R.id.txt_client_name_old_order);
            txt_price = itemView.findViewById(R.id.textView30);
            txt_deliveryNum = itemView.findViewById(R.id.txt_delivery_num_old_order);
            txt_totalNum = itemView.findViewById(R.id.txt_total_num_old_order);
            txt_addres = itemView.findViewById(R.id.txt_address_details_old_order);

            img = itemView.findViewById(R.id.imageView6);

            btn_confirm = itemView.findViewById(R.id.btn_confirm_delivery_old_order);
            btn_call = itemView.findViewById(R.id.btn_call_old_order);
        }
    }
}
