package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.NewOrdersAdapter;
import com.example.android.sofraa.adapter.OldOrdersAdapter;
import com.example.android.sofraa.data.model.restaurant_orders.RestaurantOrdersData;
import com.example.android.sofraa.data.model.restaurant_orders.RestaurantOrdersResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class OldOrdersFragment extends Fragment {

//
//    @BindView(R.id.old_orders_rv)
//    RecyclerView oldOrdersRv;
//    Unbinder unbinder;

    private RecyclerView oldOrdersRv;
    private SharedPreferences preferences;
    private String state = "completed",api_token = "quW3tUS7GVL5lv1BfAT0Orm4CXBtmRVREu3tCP6B5WebYsVaIQYdeoyg7yay";
    private OldOrdersAdapter adapter;

    public OldOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_old_orders, container, false);


        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
//        api_token = preferences.getString("api_token", "");

        oldOrdersRv = view.findViewById(R.id.old_orders_rv);

        setupRecycler();
        viewResponse();
        return view;
    }
    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        oldOrdersRv.setLayoutManager(layoutManager);
        adapter = new OldOrdersAdapter(getActivity());
        oldOrdersRv.setAdapter(adapter);
    }

    private void viewResponse() {
        Retrofit retrofit = RetrofitClient.getShopInstance();
        API api = retrofit.create(API.class);
        Call<RestaurantOrdersResponse> restaurantOrdersResponse = api.getRestaurantOrdersResponse(api_token, state);
        restaurantOrdersResponse.enqueue(new Callback<RestaurantOrdersResponse>() {
            @Override
            public void onResponse(Call<RestaurantOrdersResponse> call, Response<RestaurantOrdersResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        Log.i( "onResponse: ",response.body()+"");
                        RestaurantOrdersResponse body = response.body();
                        ViewData(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantOrdersResponse> call, Throwable t) {

            }
        });
    }

    private void ViewData(RestaurantOrdersResponse body) {
        List<RestaurantOrdersData> data = body.getData().getData();
        adapter.setDataToAdapter(data);
        adapter.notifyDataSetChanged();

    }
}
