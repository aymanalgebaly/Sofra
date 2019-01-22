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
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.CurrentOrdersAdapter;
import com.example.android.sofraa.adapter.NewOrdersAdapter;
import com.example.android.sofraa.adapter.OldOrdersAdapter;
import com.example.android.sofraa.data.model.accept_delivery_order.AcceptDeliveryResponse;
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
public class CurrentOrdersFragment extends Fragment {


//    @BindView(R.id.current_order_rv)
//    RecyclerView currentOrderRv;
//    Unbinder unbinder;

    private RecyclerView currentOrdersRv;
    private SharedPreferences preferences;
    private String state = "current",api_token = "quW3tUS7GVL5lv1BfAT0Orm4CXBtmRVREu3tCP6B5WebYsVaIQYdeoyg7yay";
    private CurrentOrdersAdapter adapter;

    public CurrentOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_orders, container, false);

        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
//        api_token = preferences.getString("api_token", "");

        currentOrdersRv = view.findViewById(R.id.current_order_rv);

        setupRecycler();
        viewResponse();
        onTouch();
        return view;
    }
    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        currentOrdersRv.setLayoutManager(layoutManager);
        adapter = new CurrentOrdersAdapter(getActivity());
        currentOrdersRv.setAdapter(adapter);
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
    private void onTouch(){
        adapter.onItemClickedListner(new CurrentOrdersAdapter.onItemClickListner() {
            @Override
            public void onClick(RestaurantOrdersData restaurantOrdersData) {
                Integer id = restaurantOrdersData.getId();

                Retrofit retrofit = RetrofitClient.getShopInstance();
                API api = retrofit.create(API.class);
                Call<AcceptDeliveryResponse> acceptDeliveryResponse = api.getAcceptDeliveryResponse(api_token, id);
                acceptDeliveryResponse.enqueue(new Callback<AcceptDeliveryResponse>() {
                    @Override
                    public void onResponse(Call<AcceptDeliveryResponse> call, Response<AcceptDeliveryResponse> response) {
                        if (response.body() != null){
                            if (response.body().getStatus() == 1){
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AcceptDeliveryResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
