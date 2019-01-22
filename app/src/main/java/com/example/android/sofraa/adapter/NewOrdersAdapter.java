package com.example.android.sofraa.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.restaurant_orders.RestaurantOrdersData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewOrdersAdapter extends RecyclerView.Adapter<NewOrdersAdapter.ViewHolderNewOrders> {

    public static RestaurantOrdersData s;
    private Context context;
    private List<RestaurantOrdersData>ordersDataList;
    private onItemClickListner onItemClickedListner;

    public NewOrdersAdapter() {
    }

    public NewOrdersAdapter(List<RestaurantOrdersData> ordersDataList) {
        this.ordersDataList = ordersDataList;
    }

    public interface onItemClickListner {
        void onClick(RestaurantOrdersData restaurantOrdersData);//pass your object types.
    }

    public void onItemClickedListner(NewOrdersAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickedListner = onItemClickListner;
    }

    public NewOrdersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderNewOrders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_order, viewGroup, false);
        return new ViewHolderNewOrders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNewOrders holder, final int position) {
        final RestaurantOrdersData restaurantOrdersData = ordersDataList.get(position);

        holder.txt_addres.setText(restaurantOrdersData.getClient().getAddress());
        holder.txt_totalNum.setText(restaurantOrdersData.getTotal());
        holder.txt_deliveryNum.setText(restaurantOrdersData.getDeliveryCost());
        holder.txt_price.setText(restaurantOrdersData.getCost());
        holder.txt_name.setText(restaurantOrdersData.getClient().getName());
//        holder.txt_codeNum.setText(restaurantOrdersData.getId());

        Picasso.get().load(restaurantOrdersData.getRestaurant().getPhotoUrl()).into(holder.img);

        holder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = restaurantOrdersData.getClient().getPhone();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(callIntent);
            }
            });
        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolderNewOrders extends RecyclerView.ViewHolder {

        TextView txt_codeNum,txt_name,txt_price,txt_deliveryNum,txt_totalNum,txt_addres;
        ImageView img;
        Button btn_accept,btn_decline,btn_call;

        public ViewHolderNewOrders(@NonNull View itemView) {
            super(itemView);
            txt_codeNum = itemView.findViewById(R.id.txt_code_num);
            txt_name = itemView.findViewById(R.id.txt_client_name);
            txt_price = itemView.findViewById(R.id.textView25);
            txt_deliveryNum = itemView.findViewById(R.id.txt_delivery_num);
            txt_totalNum = itemView.findViewById(R.id.txt_total_num);
            txt_addres = itemView.findViewById(R.id.txt_address_details);

            img = itemView.findViewById(R.id.imageView5);

            btn_accept = itemView.findViewById(R.id.btn_accept);
            btn_call = itemView.findViewById(R.id.btn_call);
            btn_decline = itemView.findViewById(R.id.btn_cancel_delivery);
        }
    }
}
