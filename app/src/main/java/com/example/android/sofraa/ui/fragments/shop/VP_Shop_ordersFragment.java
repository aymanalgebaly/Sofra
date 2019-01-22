package com.example.android.sofraa.ui.fragments.shop;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.VP_ShopOrdersAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VP_Shop_ordersFragment extends Fragment {
    private TabLayout R20Tablayout;
    private ViewPager R20Viewpager;

//    @BindView(R.id.R20_tablayout)
//    TabLayout R20Tablayout;
//    @BindView(R.id.R20)
//    RelativeLayout R20;
//    @BindView(R.id.R20_viewpager)
//    ViewPager R20Viewpager;
//    Unbinder unbinder;

    public VP_Shop_ordersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vp__shop_orders, container, false);

        R20Tablayout = view.findViewById(R.id.R20_tablayout);
        R20Viewpager = view.findViewById(R.id.R20_viewpager);

        R20Tablayout.setupWithViewPager(R20Viewpager);

        VP_ShopOrdersAdapter vp_shopOrdersAdapter = new VP_ShopOrdersAdapter(getChildFragmentManager());
        R20Viewpager.setAdapter(vp_shopOrdersAdapter);

        View root = R20Tablayout.getChildAt(0);
        if (root instanceof LinearLayout){
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.black));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }


        return view;
    }
}
