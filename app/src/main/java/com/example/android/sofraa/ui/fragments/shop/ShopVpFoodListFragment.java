package com.example.android.sofraa.ui.fragments.shop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sofraa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopVpFoodListFragment extends Fragment {


    public ShopVpFoodListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_vp_food_list, container, false);

        return view;
    }

}
