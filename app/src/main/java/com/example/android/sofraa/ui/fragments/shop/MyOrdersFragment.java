package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.ShopOffersAdapter;
import com.example.android.sofraa.data.model.offer_list_shop.AddOfferShopListResponse;
import com.example.android.sofraa.data.model.offers.OfferData;
import com.example.android.sofraa.data.model.offers.OffersResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.shop_offers.Datum;
import com.example.android.sofraa.data.model.shop_offers.OffersShopResponse;
import com.example.android.sofraa.data.model.shop_offers_new.ShopOffersDataNew;
import com.example.android.sofraa.data.model.shop_offers_new.ShopOffersResponse;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btn_add_offer;
    private ShopOffersAdapter adapter;
    private String api_token ;
    private SharedPreferences preferences;


    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        recyclerView = view.findViewById(R.id.offers_rv);
        btn_add_offer = view.findViewById(R.id.btn_add_new_offer);
        btn_add_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopActivity shopActivity = (ShopActivity)getActivity();
                AddNewOrderFragment addNewOrderFragment = new AddNewOrderFragment();
                shopActivity.displaySelectedFragment(addNewOrderFragment);
            }
        });

        setupRecycler();
        ShowMyOffers();

        return view;
    }

    private void ShowMyOffers() {
        Retrofit retrofit = RetrofitClient.getShopInstance();
        API api = retrofit.create(API.class);
        final Call<OffersResponse> offersResponse = api.getOffersResponse(api_token);
        offersResponse.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){

                        OffersResponse body = response.body();
                        ViewData(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {
                Log.i( "onFailure: ",t.getMessage());
            }
        });
    }

    private void ViewData(OffersResponse body) {

        List<OfferData> data = body.getData().getData();
        adapter.setOffersToList(data);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ShopOffersAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }
}
