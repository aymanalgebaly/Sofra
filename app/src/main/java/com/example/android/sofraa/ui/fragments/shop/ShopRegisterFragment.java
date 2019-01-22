package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
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
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.shop_register.RegisterData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopRegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private String userName, email, pass, confirmPass;

    private EditText txtRestuarantName, txtRestuarantEmail, txtRestuarantPass, txtRestuarantConfiPass;
    private Button btn_continue;
    private Spinner city, region;
    private int city_id, region_id;
    private List<com.example.android.sofraa.data.model.region.Datum> data = new ArrayList<>();
    private SharedPreferences preferences;


    public ShopRegisterFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_register, container, false);

        txtRestuarantName = view.findViewById(R.id.txt_restuarant_name);
        txtRestuarantEmail = view.findViewById(R.id.txt_restuarant_email);
        txtRestuarantPass = view.findViewById(R.id.txt_restuarant_pass);
        txtRestuarantConfiPass = view.findViewById(R.id.txt_restuarant_confi_pass);

        city = view.findViewById(R.id.spinner_city_restuarant);
        city.setOnItemSelectedListener(this);

        region = view.findViewById(R.id.spinner_region_restuarant);
        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                region_id = data.get(region.getSelectedItemPosition()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_continue = view.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        ViewCities();
        return view;

    }

    private void ViewCities() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<CitiesResponse> citiesResponse = api.getCitiesResponse();
        citiesResponse.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        CitiesResponse body = response.body();
                        ViewDataResponse(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ViewDataResponse(CitiesResponse body) {
        List<Datum> data = body.getCitiesData().getData();
        ArrayAdapter cityList = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, data);
        cityList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityList);
    }

    private void createUser() {

        userName = txtRestuarantName.getText().toString();
        email = txtRestuarantEmail.getText().toString();
        pass = txtRestuarantPass.getText().toString();
        confirmPass = txtRestuarantConfiPass.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            txtRestuarantName.setError("UserName is Required");
        } else if (TextUtils.isEmpty(email)) {
            txtRestuarantEmail.setError("Email is Required");
        } else if (TextUtils.isEmpty(pass)) {
            txtRestuarantPass.setError("password is Required");
        } else if (TextUtils.isEmpty(confirmPass) && !TextUtils.equals(pass, confirmPass)) {
            txtRestuarantConfiPass.setError("Confirm Password is not Match with Password");
        } else {

            RegisterData data = new RegisterData();
            data.setName(userName);
            data.setEmail(email);
            data.setPassword(pass);
            data.setConfirm_password(confirmPass);


            ShopRegisterTwoFragment shopRegisterTwoFragment = new ShopRegisterTwoFragment();
            shopRegisterTwoFragment.region_id = region_id;
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", data);
            shopRegisterTwoFragment.setArguments(bundle);
//            shopRegisterTwoFragment.x =data.getId();
            displaySelectedFragment(shopRegisterTwoFragment);


        }
    }


    public void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.shop_frame, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        city_id = city.getSelectedItemPosition() + 1;
        viewRegion();

    }

    private void viewRegion() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<RegionResponse> regionResponse = api.getRegionResponse(city_id);
        regionResponse.enqueue(new Callback<RegionResponse>() {
            @Override
            public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        RegionResponse body = response.body();
                        ViewRegionData(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionResponse> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ViewRegionData(RegionResponse body) {
        data = body.getRegionData().getData();
        ArrayAdapter regionList = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, data);
        regionList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(regionList);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
