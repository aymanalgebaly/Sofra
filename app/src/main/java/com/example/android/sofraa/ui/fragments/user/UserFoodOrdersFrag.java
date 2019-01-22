package com.example.android.sofraa.ui.fragments.user;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserFoodOrdersAdapter;
import com.example.android.sofraa.data.model.list_of_restaurants.Datum;
import com.example.android.sofraa.data.model.list_of_restaurants.ListOfRestaurantsData;
import com.example.android.sofraa.data.model.list_of_restaurants.UserListOfRestaurantResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.helper.OnEndless;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserFoodListFragment;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.VP_FoodListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFoodOrdersFrag extends Fragment {


    @BindView(R.id.user_foodOrders_search)
    EditText userFoodOrdersSearch;
    @BindView(R.id.user_foodOrders_spinner)
    Spinner userFoodOrdersSpinner;
//    @BindView(R.id.user_foodOrders_rv)
//    RecyclerView recyclerView;
    Unbinder unbinder;

    public String api_token,restaurantId;
    private int maxPage;
    private List<Datum> data;
    private UserFoodOrdersAdapter adapter;
    private RecyclerView recyclerView;
    public UserFoodOrdersFrag() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View inflate = inflater.inflate(R.layout.fragment_user_food_orders, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        recyclerView = inflate.findViewById(R.id.user_foodOrders_rv);

        SharedPreferences preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        setupRecycle();
        viewResponse(1);

        adapter.setOnItemClickListner(new UserFoodOrdersAdapter.onItemClickListner() {
            @Override
            public void onClick(Datum datum) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("resturant_data" , datum);

                UserFoodListFragment.RestaurantData = datum;

                VP_FoodListFragment fragment = new VP_FoodListFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.user_frame, fragment).addToBackStack(null).commit();

            }
        });



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
        adapter = new UserFoodOrdersAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        OnEndless onEndlesslistener = new OnEndless((LinearLayoutManager) layoutManager, 10) {
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

        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<UserListOfRestaurantResponse> call = api.getResturants(page);
        call.enqueue(new Callback<UserListOfRestaurantResponse>() {
            @Override
            public void onResponse(Call<UserListOfRestaurantResponse> call, Response<UserListOfRestaurantResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {


                        ListOfRestaurantsData listOfRestaurantsData = response.body().getListOfRestaurantsData();

                        viewData(listOfRestaurantsData, page);

                        maxPage = response.body().getListOfRestaurantsData().getLastPage();


                    }else
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserListOfRestaurantResponse> call, Throwable t) {

            }
        });

    }

    private void viewData(ListOfRestaurantsData listOfRestaurantsData, int page) {

        if (page == 1) {
            this.data = new ArrayList<>();
            this.data = listOfRestaurantsData.getData();
            adapter.sendDataToAdapter(data);
        } else
            data.addAll(listOfRestaurantsData.getData());

        adapter.notifyDataSetChanged();

    }


}
