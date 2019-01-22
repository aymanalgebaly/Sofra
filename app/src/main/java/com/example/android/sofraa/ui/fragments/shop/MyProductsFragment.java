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
import android.widget.Button;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.ShopProductsAdapter;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.shop_products.Datum;
import com.example.android.sofraa.data.model.shop_products.ProductsResponse;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProductsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btn_add_product;
    private ShopProductsAdapter adapter;
    private String api_token;
    private SharedPreferences preferences;


    public MyProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_products, container, false);

        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token","");

        recyclerView = view.findViewById(R.id.product_rv);
        btn_add_product = view.findViewById(R.id.btn_add_new_product);
        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewProductFragment fragment = new AddNewProductFragment();
                ShopActivity shopActivity = (ShopActivity)getActivity();
                shopActivity.displaySelectedFragment(fragment);
            }
        });

        setupRecycler();
        viewData(1);

        return view;
    }

    private void viewData(final int page) {
        Retrofit retrofit = RetrofitClient.getShopInstance();
        API api = retrofit.create(API.class);
        Call<ProductsResponse> productsResponse = api.getProductsResponse(api_token,page);
        productsResponse.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        ProductsResponse body = response.body();
                        ViewDataResponse(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ViewDataResponse(ProductsResponse body) {
        List<Datum> datumList = body.getProductsData().getData();
        adapter.setDataToAdapter(datumList);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ShopProductsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

//    public void displaySelectedFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.user_frame, fragment);
//        fragmentTransaction.addToBackStack(null).commit();
//    }
}
