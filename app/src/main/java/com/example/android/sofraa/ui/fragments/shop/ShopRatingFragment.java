package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserShopRatingAdapter;
import com.example.android.sofraa.data.model.restaurant_reviews.Datum;
import com.example.android.sofraa.data.model.restaurant_reviews.RestaurantReviewsResponse;
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
public class ShopRatingFragment extends Fragment {


    @BindView(R.id.textView22)
    TextView textView22;
//    @BindView(R.id.buy_addRate_rv)
//    RecyclerView buyAddRateRv;
    Unbinder unbinder;
    private int restaurant_id;
    private String api_token;
    private UserShopRatingAdapter adapter;
    private SharedPreferences preferences;
    private int id;
    private RecyclerView buyAddRateRv;


    public ShopRatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_rating, container, false);

        buyAddRateRv = view.findViewById(R.id.buy_addRate_rv);

        preferences = getActivity().getSharedPreferences("shop", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        restaurant_id = preferences.getInt("id", id);

        setupRecycle();
        ViewData(1);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void ViewData(final int page) {
        Retrofit retrofit = RetrofitClient.getShopInstance();
        API api = retrofit.create(API.class);
        Call<RestaurantReviewsResponse> restaurantReviewsResponse = api.getRestaurantReviewsResponse(api_token, restaurant_id, page);
        restaurantReviewsResponse.enqueue(new Callback<RestaurantReviewsResponse>() {
            @Override
            public void onResponse(Call<RestaurantReviewsResponse> call, Response<RestaurantReviewsResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        RestaurantReviewsResponse body = response.body();
                        ViewResponseRate(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantReviewsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ViewResponseRate(RestaurantReviewsResponse body) {
        List<Datum> data = body.getRestaurantReviewsData().getData();
        adapter.sendToAdapter(data);
        adapter.notifyDataSetChanged();
    }


    public void setupRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        buyAddRateRv.setLayoutManager(layoutManager);
        adapter = new UserShopRatingAdapter(getActivity());
        buyAddRateRv.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
