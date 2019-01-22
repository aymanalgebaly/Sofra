package com.example.android.sofraa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sofraa.R;

public class UserBasketAdapter extends RecyclerView.Adapter<UserBasketAdapter.basketViewHolder> {


    private final Context context;

    public UserBasketAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public basketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_user_basket, viewGroup, false);
        return new basketViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull basketViewHolder basketViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class basketViewHolder extends RecyclerView.ViewHolder {
        public basketViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
