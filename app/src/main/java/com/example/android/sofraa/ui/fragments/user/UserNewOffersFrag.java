package com.example.android.sofraa.ui.fragments.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserOffersAdapter;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.user_offers.Datum;
import com.example.android.sofraa.data.model.user_offers.UserOffersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserNewOffersFrag extends Fragment {

    private RecyclerView recyclerView;
    private UserOffersAdapter adapter;


    public UserNewOffersFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_new_offers, container, false);

        recyclerView = view.findViewById(R.id.user_offers_rv);

        setupRecycler();
        ViewData();
        return view;
    }

    private void ViewData() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<UserOffersResponse> userOffersResponse = api.getUserOffersResponse();
        userOffersResponse.enqueue(new Callback<UserOffersResponse>() {
            @Override
            public void onResponse(Call<UserOffersResponse> call, Response<UserOffersResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        UserOffersResponse body = response.body();
                        ViewResponse(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserOffersResponse> call, Throwable t) {

            }
        });
    }

    private void ViewResponse(UserOffersResponse body) {
        List<Datum> data = body.getUserOffersData().getData();
        adapter.sendToAdapterData(data);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserOffersAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

}
