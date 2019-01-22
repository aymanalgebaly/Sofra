package com.example.android.sofraa.ui.fragments.user;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.settings_media.SettingsMediaResponse;

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
public class UserAboutAppFrag extends Fragment {


    @BindView(R.id.txt_about_app)
    TextView txtAboutApp;
    Unbinder unbinder;

    private SharedPreferences preferences;
    private String email,password;

    public UserAboutAppFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_about_app, container, false);

        preferences = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        email = preferences.getString("email", "");
        password = preferences.getString("password", "");

        unbinder = ButterKnife.bind(this, view);

        AboutApp();
        return view;
    }

    private void AboutApp() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<SettingsMediaResponse> settingsMediaResponse = api.getSettingsMediaResponse(email, password);
        settingsMediaResponse.enqueue(new Callback<SettingsMediaResponse>() {
            @Override
            public void onResponse(Call<SettingsMediaResponse> call, Response<SettingsMediaResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){

                        txtAboutApp.setText(response.body().getData().getAboutApp());
                    }
                }
            }

            @Override
            public void onFailure(Call<SettingsMediaResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
