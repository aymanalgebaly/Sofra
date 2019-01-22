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
import com.example.android.sofraa.data.model.notify.Datum;

import java.util.List;



public class UserNotifyAdapter extends RecyclerView.Adapter<UserNotifyAdapter.NotificationsViewHolder> {


    private Context context;
    private List<Datum> dataList;


    public UserNotifyAdapter (Context context) {
        this.context = context;
    }

    public void sendDataToAdapter(List<Datum> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_user_notification, viewGroup, false);
        return new NotificationsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int i) {

        Datum datum = dataList.get(i);
        holder.notify_title.setText(datum.getTitle());
        holder.date.setText(datum.getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }


    public class NotificationsViewHolder extends RecyclerView.ViewHolder {

        TextView time,date,notify_title;

        public NotificationsViewHolder(View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.user_notify_time);
            date = itemView.findViewById(R.id.user_notify_date);
            notify_title = itemView.findViewById(R.id.user_notify_title);

        }
    }
}

