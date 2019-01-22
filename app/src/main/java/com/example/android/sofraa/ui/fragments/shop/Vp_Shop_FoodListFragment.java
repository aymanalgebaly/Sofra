package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.VP_ShopFoodListAdapter;
import com.example.android.sofraa.data.model.restaurant_details.RestaurantDetailsResponse;
import com.example.android.sofraa.data.model.restaurant_details.getRestaurantDetailsData;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.squareup.picasso.Picasso;

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
public class Vp_Shop_FoodListFragment extends Fragment {


    @BindView(R.id.shop_foodList_img)
    ImageView shopFoodListImg;
    @BindView(R.id.shop_foodList_shopName)
    TextView shopFoodListShopName;
    @BindView(R.id.shop_foodList_shopDesc)
    TextView shopFoodListShopDesc;
    @BindView(R.id.shop_foodList_rating)
    RatingBar shopFoodListRating;
    @BindView(R.id.textView60)
    TextView textView60;
    @BindView(R.id.textView70)
    TextView textView70;
    @BindView(R.id.shop_foodList_lowest_order)
    TextView shopFoodListLowestOrder;
    @BindView(R.id.shop_foodList_delevry_cost)
    TextView shopFoodListDelevryCost;
    @BindView(R.id.R0)
    ConstraintLayout R0;
//    @BindView(R.id.shop_foodList_tabs)
//    TabLayout shopFoodListTabs;
    @BindView(R.id.R10)
    RelativeLayout R10;
//    @BindView(R.id.shop_foodList_viewpager)
//    ViewPager shopFoodListViewpager;
    Unbinder unbinder;
    private TabLayout shopFoodListTabs;
    private ViewPager shopFoodListViewpager;
    private SharedPreferences preferences;
    private int restaurantId;
    private int id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vp__shop__food_list, container, false);

        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
        restaurantId = preferences.getInt("id", id);

        shopFoodListTabs = view.findViewById(R.id.shop_foodList_tabs);
        shopFoodListViewpager = view.findViewById(R.id.shop_foodList_viewpager);

        shopFoodListTabs.setupWithViewPager(shopFoodListViewpager);

        VP_ShopFoodListAdapter vp_shopFoodListAdapter = new VP_ShopFoodListAdapter(getChildFragmentManager());
        shopFoodListViewpager.setAdapter(vp_shopFoodListAdapter);

        View root = shopFoodListTabs.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.black));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }


        unbinder = ButterKnife.bind(this, view);

        LoadData();

        return view;

    }

    private void LoadData() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<RestaurantDetailsResponse> restaurantDetailsResponse = api.getRestaurantDetailsResponse(restaurantId);
        restaurantDetailsResponse.enqueue(new Callback<RestaurantDetailsResponse>() {
            @Override
            public void onResponse(Call<RestaurantDetailsResponse> call, Response<RestaurantDetailsResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        getRestaurantDetailsData restaurantDetailsData = response.body().getRestaurantDetailsData();

                        shopFoodListDelevryCost.setText(restaurantDetailsData.getDeliveryCost());
                        shopFoodListLowestOrder.setText(restaurantDetailsData.getMinimumCharger());
                        shopFoodListShopName.setText(restaurantDetailsData.getName());

                        Picasso.get().load(restaurantDetailsData.getPhotoUrl()).into(shopFoodListImg);
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
}
