package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.ShopFoodListAdapter;
import com.example.android.sofraa.adapter.UserFoodListAdapter;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ListOfRestaurantItemsData;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ListOfRestaurantItemsResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.helper.OnEndless;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;
import com.example.android.sofraa.ui.fragments.user.UserFoodListDetailFrag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ShopListOrdersFragment extends Fragment {


    @BindView(R.id.shop_list_rv)
    RecyclerView shopListRv;
    Unbinder unbinder;


    private ShopFoodListAdapter adapter;
    private int maxPage,restaurantId;
    private List<ItemFoodData> itemFoodDataList;
    public static ItemFoodData itemFoodDataRestaurant;
    private SharedPreferences preferences;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_list_orders, container, false);

        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
        restaurantId = preferences.getInt("id", id);

        unbinder = ButterKnife.bind(this, view);

        adapter = new ShopFoodListAdapter(getActivity());

        setupRecycle();
        getFoodListResponse(1);
        OnTouchAdapter();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void setupRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        shopListRv.setLayoutManager(layoutManager);
        adapter = new ShopFoodListAdapter(getContext());
        shopListRv.setAdapter(adapter);

        OnEndless onEndlesslistener = new OnEndless((LinearLayoutManager) layoutManager, 20) {
            @Override
            public void onLoadMore(int current_page) {
                // do something...
                if (current_page <= maxPage) {
                    getFoodListResponse(current_page);
                }
            }
        };
        shopListRv.addOnScrollListener(onEndlesslistener);

    }


    private void getFoodListResponse(final int page) {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<ListOfRestaurantItemsResponse> call = api.getFoodList(restaurantId, page);
        call.enqueue(new Callback<ListOfRestaurantItemsResponse>() {
            @Override
            public void onResponse(Call<ListOfRestaurantItemsResponse> call, Response<ListOfRestaurantItemsResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){

                        ListOfRestaurantItemsData listOfRestaurantItemsData = response.body().getListOfRestaurantItemsData();

                        viewFoodListResponse(listOfRestaurantItemsData, page);
                        maxPage = response.body().getListOfRestaurantItemsData().getLastPage();

                    }
                }else
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ListOfRestaurantItemsResponse> call, Throwable t) {

            }
        });

    }

    private void viewFoodListResponse(ListOfRestaurantItemsData body, int page) {
        if (page == 1){
            this.itemFoodDataList = new ArrayList<>();
            this.itemFoodDataList = body.getData();
            adapter.sendDataToAdapter(itemFoodDataList);

        }else
            itemFoodDataList.addAll(body.getData());

        adapter.notifyDataSetChanged();

    }
    public void OnTouchAdapter(){

        adapter.setOnItemClickListner(new ShopFoodListAdapter.onItemClickListner() {
            @Override
            public void onClick(ItemFoodData restaurant) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("restaurant",restaurant);

                UserFoodListDetailFrag fragment = new UserFoodListDetailFrag();
                fragment.setArguments(bundle);
                ShopActivity shopActivity = (ShopActivity)getActivity();
                shopActivity.displaySelectedFragment(fragment);

            }
        });

    }

}
