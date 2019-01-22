package com.example.android.sofraa.ui.fragments.shop;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import com.example.android.sofraa.adapter.VP_UserFoodListAdapter;
import com.example.android.sofraa.data.model.list_of_restaurants.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VpMyOrdersShopFragment extends Fragment {


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
    @BindView(R.id.shop_foodList_tabs)
    TabLayout shopFoodListTabs;
    @BindView(R.id.R1)
    RelativeLayout R1;
    @BindView(R.id.shop_foodList_viewpager)
    ViewPager shopFoodListViewpager;
    Unbinder unbinder;

    public VpMyOrdersShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vp_my_orders_shop, container, false);
        unbinder = ButterKnife.bind(this, view);

        shopFoodListTabs.setupWithViewPager(shopFoodListViewpager);

        VP_UserFoodListAdapter fragmentsAdapter = new VP_UserFoodListAdapter(getChildFragmentManager());
        shopFoodListViewpager.setAdapter(fragmentsAdapter);

        View root = shopFoodListTabs.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.black));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        Bundle bundle = getArguments();
        if (bundle != null ){
            Datum resturant_data = bundle.getParcelable("resturant_data");

            shopFoodListShopName.setText(resturant_data.getName());
            shopFoodListLowestOrder.setText(resturant_data.getMinimumCharger());
            shopFoodListDelevryCost.setText(resturant_data.getDeliveryCost());
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
