package com.example.android.sofraa.ui.fragments.user.vp_food_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserFoodListAdapter;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ListOfRestaurantItemsData;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ListOfRestaurantItemsResponse;
import com.example.android.sofraa.data.model.list_of_restaurants.Datum;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.helper.OnEndless;
import com.example.android.sofraa.ui.activities.user.UserActivity;
import com.example.android.sofraa.ui.fragments.user.UserFoodListDetailFrag;
import com.example.android.sofraa.ui.fragments.user.UserFoodOrdersFrag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFoodListFragment extends Fragment {
    public static Datum RestaurantData;

    private UserFoodListAdapter adapter;
    private int maxPage;
    private List<ItemFoodData> itemFoodDataList;
    public static ItemFoodData itemFoodDataRestaurant;
    private RecyclerView recyclerView;
    public Integer currentPage,restaurantId;
    public String name;

    public UserFoodListFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_user_food_list, container, false);

       recyclerView = inflate.findViewById(R.id.user_foodList_rv);

       adapter = new UserFoodListAdapter(getActivity());

       restaurantId = RestaurantData.getId();
       name = RestaurantData.getName();

       UserRatingFragment.RestuarantId = restaurantId;
       UserShopInfoFragment.restId = restaurantId;
       UserRatingFragment.ResName = name;

       Bundle bundle = new Bundle();
       bundle.putParcelable("itemFoodData",itemFoodDataRestaurant);
       UserShopInfoFragment userShopInfoFragment = new UserShopInfoFragment();
       userShopInfoFragment.setArguments(bundle);



        // setup recycleView and get foodList response
        setupRecycle();
        getFoodListResponse(1);
        OnTouchAdapter();



        return inflate;
    }


    private void setupRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserFoodListAdapter(getContext());
        recyclerView.setAdapter(adapter);

        OnEndless onEndlesslistener = new OnEndless((LinearLayoutManager) layoutManager, 20) {
            @Override
            public void onLoadMore(int current_page) {
                // do something...
                if (current_page <= maxPage) {
                    getFoodListResponse(current_page);
                }
            }
        };
        recyclerView.addOnScrollListener(onEndlesslistener);

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

        adapter.setOnItemClickListner(new UserFoodListAdapter.onItemClickListner() {
            @Override
            public void onClick(ItemFoodData restaurant) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("restaurant",restaurant);

                UserFoodListDetailFrag fragment = new UserFoodListDetailFrag();
                fragment.setArguments(bundle);
                UserActivity userActivity = (UserActivity)getActivity();
                userActivity.displaySelectedFragment(fragment);

            }
        });

    }
}
