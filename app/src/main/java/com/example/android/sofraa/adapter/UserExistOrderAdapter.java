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
import com.example.android.sofraa.data.model.user_my_orders.UserOrderData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserExistOrderAdapter extends RecyclerView.Adapter<UserExistOrderAdapter.existOrderViewHolder> {


    private Context context;
    private List<UserOrderData>datumList = new ArrayList<>();

    public UserExistOrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public existOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_user_exist_orders, viewGroup, false);
        return new existOrderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull existOrderViewHolder holder, int position) {

        UserOrderData datum = datumList.get(position);

        holder.txt_total.setText(datum.getTotal());
        holder.txt_delivery.setText(datum.getDeliveryCost());
        holder.txt_salary.setText(datum.getCost());
        holder.txt_name.setText(datum.getRestaurant().getName());

        Picasso.get().load(datum.getRestaurant().getPhoto()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datumList != null ?datumList.size():0;
    }

    public void sendDataToAdapter(List<UserOrderData> data) {
        this.datumList = data;
    }

    class existOrderViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_salary,txt_delivery,txt_total,txt_code;
        ImageView imageView;
        public existOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_current_order);
            txt_name = itemView.findViewById(R.id.tx_name_current_order);
            txt_salary = itemView.findViewById(R.id.txt_current_order_salary_num);
            txt_delivery = itemView.findViewById(R.id.txt_current_order_delivery_num);
            txt_code = itemView.findViewById(R.id.txt_current_order_code_num);
            txt_total = itemView.findViewById(R.id.txt_current_order_total_num);

        }
    }

}
