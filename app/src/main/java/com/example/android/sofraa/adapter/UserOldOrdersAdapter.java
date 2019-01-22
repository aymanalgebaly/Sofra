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
import com.example.android.sofraa.data.model.user_my_orders.UserOrderData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserOldOrdersAdapter extends RecyclerView.Adapter<UserOldOrdersAdapter.oldOrdersViewHolder> {


    private final Context context;
    private List<UserOrderData> datumList;

    public UserOldOrdersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public oldOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_user_old_orders, viewGroup, false);
        return new oldOrdersViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull oldOrdersViewHolder holder, int position) {

        UserOrderData datum = datumList.get(position);
        holder.txt_title.setText(datum.getRestaurant().getName());
        holder.txt_delivery_cost_num.setText(datum.getCost());
        holder.txt_total_num.setText(datum.getTotal());
//        holder.txt_code_num.setText(myordersData.);
        Picasso.get().load(datum.getRestaurant().getPhoto()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return datumList != null ?datumList.size():0;
    }

    public void sendDataToAdapter(List<UserOrderData> data) {
        this.datumList = data;
    }

    class oldOrdersViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txt_title,txt_salary,txt_delivery_cost,txt_total,txt_salary_num,txt_delivery_cost_num,txt_total_num,
        txt_code,txt_code_num;
        Button btn_accept,btn_reject;
        public oldOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_old_orders_user);
            txt_code = itemView.findViewById(R.id.code_res_name);
            txt_delivery_cost = itemView.findViewById(R.id.txt_delivery_cost_res);
            txt_salary = itemView.findViewById(R.id.txt_salary_res);
            txt_title = itemView.findViewById(R.id.txt_title_name_res);
            txt_total = itemView.findViewById(R.id.txt_total_old_orders_res);
            txt_code_num = itemView.findViewById(R.id.code_res_num);
            txt_delivery_cost_num = itemView.findViewById(R.id.delivery_cost_res_num);
            txt_salary_num = itemView.findViewById(R.id.txt_salary_num_res);
            txt_total_num = itemView.findViewById(R.id.txt_total_res_num);

            btn_accept = itemView.findViewById(R.id.btn_accept_res);
            btn_reject = itemView.findViewById(R.id.btn_declice_res);

        }
    }

}
