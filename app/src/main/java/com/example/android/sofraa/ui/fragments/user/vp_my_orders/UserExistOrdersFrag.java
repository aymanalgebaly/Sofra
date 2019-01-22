package com.example.android.sofraa.ui.fragments.user.vp_my_orders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserExistOrderAdapter;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.user_my_orders.UserOrderData;
import com.example.android.sofraa.data.model.user_my_orders.UserOrdersResponse;

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
public class UserExistOrdersFrag extends Fragment {


    @BindView(R.id.user_exist_orders_rv)
    RecyclerView recyclerView;
    Unbinder unbinder;


    private UserExistOrderAdapter adapter;
    private String api_token = "HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB" ,state = "current";
    private SharedPreferences preferences;

    public UserExistOrdersFrag() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_user_exist_orders, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        preferences = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
//        api_token = preferences.getString("api_token", "");
//        Log.i( "onCreateView: ",api_token);

        setupRecycle();
        ViewData(1);

        return inflate;
    }

    private void ViewData(final int page) {
        Retrofit retrofit = RetrofitClient.getUserInstance();
        API api = retrofit.create(API.class);
        Call<UserOrdersResponse> myOrdersResponse = api.getUserOrdersResponse(api_token,state,page);
        myOrdersResponse.enqueue(new Callback<UserOrdersResponse>() {
            @Override
            public void onResponse(Call<UserOrdersResponse> call, Response<UserOrdersResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        UserOrdersResponse body = response.body();
                        ViewResponse(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserOrdersResponse> call, Throwable t) {

            }
        });
    }

    private void ViewResponse(UserOrdersResponse body) {
        List<UserOrderData> data = body.getUserCurruntUserData().getData();
        adapter.sendDataToAdapter(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupRecycle() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserExistOrderAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }



}
