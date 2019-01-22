package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.restaurant_details.Region;
import com.example.android.sofraa.data.model.restaurant_details.RestaurantDetailsResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopInfoFragment extends Fragment {


    @BindView(R.id.txt_case_open)
    TextView txtCaseOpen;
    @BindView(R.id.txt_city)
    TextView txtCity;
    @BindView(R.id.txt_city_name)
    TextView txtCityName;
    @BindView(R.id.txt_region)
    TextView txtRegion;
    @BindView(R.id.txt_region_name)
    TextView txtRegionName;
    @BindView(R.id.txt_time_to_order)
    TextView txtTimeToOrder;
    @BindView(R.id.txt_time_to_order_num)
    TextView txtTimeToOrderNum;
    @BindView(R.id.txt_time_to_delivery)
    TextView txtTimeToDelivery;
    @BindView(R.id.txt_time_to_delivery_num)
    TextView txtTimeToDeliveryNum;
    @BindView(R.id.txt_how_to_delivery)
    TextView txtHowToDelivery;
    @BindView(R.id.txt_how_to_delivery_num)
    TextView txtHowToDeliveryNum;
    @BindView(R.id.txt_minimum)
    TextView txtMinimum;
    @BindView(R.id.txt_minimum_num)
    TextView txtMinimumNum;
    @BindView(R.id.txt_delivery_fee)
    TextView txtDeliveryFee;
    @BindView(R.id.txt_delivery_fee_num)
    TextView txtDeliveryFeeNum;
    @BindView(R.id.switch_id)
    Switch switchId;
    @BindView(R.id.guideline9116)
    Guideline guideline9116;
    @BindView(R.id.save_btn)
    Button saveBtn;
    Unbinder unbinder;

    private SharedPreferences preferences;
    private int iid;
    private int id;

    public ShopInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_info, container, false);

        unbinder = ButterKnife.bind(this, view);

        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
        iid = preferences.getInt("id",id);

        getDetails();
        return view;
    }

    private void getDetails() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<RestaurantDetailsResponse> restaurantDetailsResponse = api.getRestaurantDetailsResponse(iid);
        restaurantDetailsResponse.enqueue(new Callback<RestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<RestaurantDetailsResponse> call, Response<RestaurantDetailsResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        RestaurantDetailsResponse body = response.body();

                        txtRegionName.setText(String.valueOf(body.getRestaurantDetailsData().getRegion()));
                        txtCaseOpen.setText(body.getRestaurantDetailsData().getAvailability());
                        txtDeliveryFeeNum.setText(body.getRestaurantDetailsData().getDeliveryCost());
                        txtMinimumNum.setText(body.getRestaurantDetailsData().getMinimumCharger());
                        txtCityName.setText(body.getRestaurantDetailsData().getRegion().getCity().getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantDetailsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.switch_id, R.id.save_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switch_id:
                break;
            case R.id.save_btn:
                break;
        }
    }
}
