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
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopFoodListAdapter extends RecyclerView.Adapter<ShopFoodListAdapter.foodListViewHolder> {


    private final Context context;
    private List<ItemFoodData> itemFoodDataList;
    onItemClickListner onItemClickListener;

    public ShopFoodListAdapter(Context context) {
        this.context = context;
    }

    public void sendDataToAdapter(List<ItemFoodData> itemFoodData) {
        this.itemFoodDataList = itemFoodData;
    }

    public void setOnItemClickListner(ShopFoodListAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListener = onItemClickListner;
    }


    public interface onItemClickListner {
        void onClick(ItemFoodData restaurant);//pass your object types.
    }


    @NonNull
    @Override
    public foodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_user_food_list, parent, false);
        return new foodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull foodListViewHolder holder, int i) {
         final ItemFoodData itemFoodData = itemFoodDataList.get(i);

        holder.tx_food_name.setText(itemFoodData.getName());
        holder.tx_food_salary.setText(itemFoodData.getPrice());
        holder.tx_food_desc.setText(itemFoodData.getDescription());

        Picasso.get().load(itemFoodData.getPhotoUrl()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(itemFoodData);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemFoodDataList != null ? itemFoodDataList.size(): 0;
    }


    class foodListViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tx_food_name, tx_food_salary,tx_food_desc;

        public foodListViewHolder(@NonNull View itemView) {
            super(itemView);


            img = itemView.findViewById(R.id.user_foodList_image);
            tx_food_name = itemView.findViewById(R.id.user_foodList_name);
            tx_food_salary = itemView.findViewById(R.id.user_foodList_salary);
            tx_food_desc = itemView.findViewById(R.id.user_foodList_description);

        }
    }

}

