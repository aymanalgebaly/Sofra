package com.example.android.sofraa.ui.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.cities.CitiesResponse;
import com.example.android.sofraa.data.model.cities.Datum;
import com.example.android.sofraa.data.model.region.RegionResponse;
import com.example.android.sofraa.data.model.register.UserRegisterResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.user_register_token.RegisterTokenResponse;
import com.example.android.sofraa.ui.activities.user.UserActivity;
import com.example.android.sofraa.ui.fragments.user.UserFoodOrdersFrag;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText name,email,address,pass,confirm_pass,phone;
    private Spinner city_sp,region_sp;
    private Button btn_register;
    private String s_name,s_email,s_address,s_pass,s_confirm_pass,s_phone;
    private SharedPreferences preferences;
    private int city_id;
    private int region_id;
    private FirebaseInstanceId firebaseInstanceId;
    private String token,type="android";
    private String api_token;


    public UserRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_register, container, false);

        name = view.findViewById(R.id.user_register_name);
        email = view.findViewById(R.id.user_register_email);
        address = view.findViewById(R.id.user_register_address);
        pass = view.findViewById(R.id.user_register_pass);
        confirm_pass = view.findViewById(R.id.user_register_confirm_pass);
        phone = view.findViewById(R.id.edite_phone);

        token = FirebaseInstanceId.getInstance().getToken();

        city_sp = view.findViewById(R.id.user_register_spinner_city);
        city_sp.setOnItemSelectedListener(this);

        region_sp = view.findViewById(R.id.user_register_spinner_region);
        region_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                region_id = region_sp.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_register = view.findViewById(R.id.user_register_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateUser();

//                Intent intent = new Intent(getActivity(),UserActivity.class);
//                startActivity(intent);
            }
        });

        viewCity();

        return view;
    }

    private void viewCity() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<CitiesResponse> citiesResponse = api.getCitiesResponse();
        citiesResponse.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){

                        CitiesResponse body = response.body();
                        VireCitiesResponse(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {

            }
        });
    }

    private void VireCitiesResponse(CitiesResponse body) {
        List<Datum> data = body.getCitiesData().getData();
        ArrayAdapter cityList = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,data);
        cityList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_sp.setAdapter(cityList);
    }

    private void CreateUser() {

        s_name = name.getText().toString();
        s_email = email.getText().toString();
        s_address = address.getText().toString();
        s_pass = pass.getText().toString();
        s_confirm_pass = confirm_pass.getText().toString();
        s_phone = phone.getText().toString();

        if (TextUtils.isEmpty(s_name)){
            name.setError("Username is Required");
        }else if (TextUtils.isEmpty(s_phone)){
            phone.setError("Phone Number is Required");
        } else if (TextUtils.isEmpty(s_email)){
            email.setError("Email is Required");
        }else if (TextUtils.isEmpty(s_address)){
            address.setError("Address is Required");
        }else if (TextUtils.isEmpty(s_pass)){
            pass.setError("Password is Required");
        }else if (TextUtils.isEmpty(s_confirm_pass) && !TextUtils.equals(s_pass,s_confirm_pass)){
            confirm_pass.setError("Confirm Password not match With Password");
        }else {

            Retrofit retrofit = RetrofitClient.getUserInstance();
            API api = retrofit.create(API.class);
            Call<UserRegisterResponse> userRegister = api.getUserRegister
                    (s_name, s_email, s_pass, s_confirm_pass, s_phone, s_address, region_id);
            userRegister.enqueue(new Callback<UserRegisterResponse>() {
                @Override
                public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                    if (response.body() != null){
                        if (response.body().getStatus() == 1){

                            api_token = response.body().getUserRegisterData().getApiToken();

                            SharedLogin();
                            notification();

                            UserActivity userActivity = (UserActivity) getActivity();
                            UserFoodOrdersFrag userFoodOrdersFrag = new UserFoodOrdersFrag();
                            userActivity.displaySelectedFragment(userFoodOrdersFrag);

                        }
                    }
                }

                @Override
                public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void notification() {
        Retrofit retrofit = RetrofitClient.getUserInstance();
        API api = retrofit.create(API.class);
        final Call<RegisterTokenResponse> registerTokenResponse = api.getRegisterTokenResponse(api_token, token, type);
        registerTokenResponse.enqueue(new Callback<RegisterTokenResponse>() {
            @Override
            public void onResponse(Call<RegisterTokenResponse> call, Response<RegisterTokenResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterTokenResponse> call, Throwable t) {

            }
        });
    }

    public void SharedLogin(){
        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("user", MODE_PRIVATE).edit();

        editor.putBoolean("login", true);

        editor.putString("name", s_name);
        editor.putString("email", s_email);
        editor.putString("phone", s_phone);
        editor.putString("pass",s_pass);
        editor.putString("address",s_address);
//        editor.putString("api_token", api_token);
        editor.apply();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        city_id = city_sp.getSelectedItemPosition() +1 ;

        viewRegion();
    }

    private void viewRegion() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        final Call<RegionResponse> regionResponse = api.getRegionResponse(city_id);
        regionResponse.enqueue(new Callback<RegionResponse>() {
            @Override
            public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        RegionResponse body = response.body();
                        ViewRegionResponse(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionResponse> call, Throwable t) {

            }
        });
    }

    private void ViewRegionResponse(RegionResponse body) {
        List<com.example.android.sofraa.data.model.region.Datum> data = body.getRegionData().getData();
        ArrayAdapter regionList = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,data);
        regionList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region_sp.setAdapter(regionList);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
