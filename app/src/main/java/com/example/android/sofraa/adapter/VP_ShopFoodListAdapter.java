package com.example.android.sofraa.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.sofraa.ui.fragments.shop.ShopInfoFragment;
import com.example.android.sofraa.ui.fragments.shop.ShopListOrdersFragment;
import com.example.android.sofraa.ui.fragments.shop.ShopRatingFragment;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserFoodListFragment;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserRatingFragment;

public class VP_ShopFoodListAdapter extends FragmentPagerAdapter {



    Fragment fragments []= {new ShopListOrdersFragment() , new ShopRatingFragment() , new ShopInfoFragment()};
    private String title;

    public VP_ShopFoodListAdapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            title = "قائمة الطعام";
        }
        if (position == 1){
            title = "التعليقات و التقييم";
        }
        if (position == 2){
            title = "معلومات المتجر";
        }
        return title;
    }
}


