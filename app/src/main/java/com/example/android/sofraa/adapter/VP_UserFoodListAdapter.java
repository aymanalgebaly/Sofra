package com.example.android.sofraa.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserFoodListFragment;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserRatingFragment;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserShopInfoFragment;

public class VP_UserFoodListAdapter extends FragmentPagerAdapter {



    Fragment fragments []= {new UserFoodListFragment() , new UserRatingFragment() , new UserShopInfoFragment()};
    private String title;

    public VP_UserFoodListAdapter(FragmentManager childFragmentManager) {
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


