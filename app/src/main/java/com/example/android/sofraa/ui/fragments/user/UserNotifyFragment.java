package com.example.android.sofraa.ui.fragments.user;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserNotifyAdapter;
import com.example.android.sofraa.data.model.notify.Datum;
import com.example.android.sofraa.data.model.notify.UserNotifyResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.helper.OnEndless;

import java.util.ArrayList;
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
public class UserNotifyFragment extends Fragment {


    @BindView(R.id.user_notify_rv)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private UserNotifyAdapter adapter;
    private int maxPage;
    private List<Datum> data;
    private SharedPreferences preferences;
    private String apiToken;
    private String api = "ZgcIpDetXKayCKVZcqIJKkVVeLZSVNFwCOAeBgeojQAqr4Tx3KBb9B6HfskT";
    private String api_token;

    public UserNotifyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_user_notify, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        preferences = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token","");

//        UserLoginFragment userLoginFragment = new UserLoginFragment();
//        apiToken = userLoginFragment.api_token;

        setupRecycle();
        viewResponse(1);

        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupRecycle() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserNotifyAdapter(getContext());
        recyclerView.setAdapter(adapter);

        OnEndless onEndlesslistener = new OnEndless((LinearLayoutManager) layoutManager, 20) {
            @Override
            public void onLoadMore(int current_page) {
                // do something...

                if (current_page <= maxPage) {
                    viewResponse(current_page);
                }
            }
        };

        recyclerView.addOnScrollListener(onEndlesslistener);
    }



    private void viewResponse(final int page) {

        Retrofit retrofit = RetrofitClient.getUserInstance();
        API api = retrofit.create(API.class);
        Call<UserNotifyResponse> call = api.getNotifivations(api_token, page);
        call.enqueue(new Callback<UserNotifyResponse>() {
            @Override
            public void onResponse(Call<UserNotifyResponse> call, Response<UserNotifyResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {

                        UserNotifyResponse body = response.body();
                        viewData(body, page);

                        Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();

                        maxPage = response.body().getData().getLastPage();

                    }else
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserNotifyResponse> call, Throwable t) {

            }
        });

    }

    private void viewData(UserNotifyResponse body, int page) {

        List<Datum> data = body.getData().getData();

        if (page == 1) {
            this.data = new ArrayList<>();
            this.data = body.getData().getData();
            adapter.sendDataToAdapter(this.data);
        } else
            this.data.addAll(body.getData().getData());

        adapter.notifyDataSetChanged();

    }



}
