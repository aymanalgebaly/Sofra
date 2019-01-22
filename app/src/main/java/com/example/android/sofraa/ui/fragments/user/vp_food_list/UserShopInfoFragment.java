package com.example.android.sofraa.ui.fragments.user.vp_food_list;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.example.android.sofraa.data.model.list_of_restaurants.Datum;
import com.example.android.sofraa.data.model.restaurant_details.RestaurantDetailsResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.ui.fragments.user.UserFoodListDetailFrag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserShopInfoFragment extends Fragment {


    public static Integer restId;
    @BindView(R.id.user_shopInfo_open)
    TextView userShopInfoOpen;
    @BindView(R.id.user_shopInfo_city)
    TextView userShopInfoCity;
    @BindView(R.id.user_shopInfo_location)
    TextView userShopInfoLocation;
    @BindView(R.id.user_shopInfo_timeOrder)
    TextView userShopInfoTimeOrder;
    @BindView(R.id.user_shopInfo_timeDelevry)
    TextView userShopInfoTimeDelevry;
    @BindView(R.id.user_shopInfo_DelevryWay)
    TextView userShopInfoDelevryWay;
    @BindView(R.id.user_shopInfo_lowOrder)
    TextView userShopInfoLowOrder;
    @BindView(R.id.user_shopInfo_DelevryCost)
    TextView userShopInfoDelevryCost;
    Unbinder unbinder;

    private String preparingTime;
    private ItemFoodData itemFoodData;
    private TextView checkedTextNumber;


    public UserShopInfoFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_user_shop_info, container, false);
        unbinder = ButterKnife.bind(this, inflate);

//        Bundle bundle = getArguments();
//        if (bundle != null){
//            itemFoodData = bundle.getParcelable("itemFoodData");
//            preparingTime = itemFoodData.getPreparingTime();
//
//        }

//        UserFoodListDetailFrag userFoodListDetailFrag = new UserFoodListDetailFrag();
//        ItemFoodData itemFoodData = userFoodListDetailFrag.itemFoodData;
//        preparingTime = itemFoodData.getPreparingTime();


        getDetails();

        return inflate;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getDetails() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<RestaurantDetailsResponse> restaurantDetailsResponse = api.getRestaurantDetailsResponse(restId);
        restaurantDetailsResponse.enqueue(new Callback<RestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<RestaurantDetailsResponse> call, Response<RestaurantDetailsResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        RestaurantDetailsResponse body = response.body();

                        userShopInfoLocation.setText(String.valueOf(body.getRestaurantDetailsData().getRegion()));
                        userShopInfoOpen.setText(body.getRestaurantDetailsData().getAvailability());
                        userShopInfoDelevryCost.setText(body.getRestaurantDetailsData().getDeliveryCost());
                        userShopInfoLowOrder.setText(body.getRestaurantDetailsData().getMinimumCharger());
                        userShopInfoCity.setText(body.getRestaurantDetailsData().getRegion().getCity().getName());
//                        userShopInfoTimeOrder.setText(preparingTime);
//                        userShopInfoTimeDelevry.setText(preparingTime);
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantDetailsResponse> call, Throwable t) {

            }
        });
    }

}