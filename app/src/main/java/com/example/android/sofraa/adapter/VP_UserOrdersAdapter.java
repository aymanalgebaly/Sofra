package com.example.android.sofraa.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.sofraa.ui.fragments.user.vp_my_orders.UserExistOrdersFrag;
import com.example.android.sofraa.ui.fragments.user.vp_my_orders.UserOldOrdersFrag;


public class VP_UserOrdersAdapter extends FragmentPagerAdapter {

    Fragment [] fragments = {new UserExistOrdersFrag(),new UserOldOrdersFrag()};
    String title;

    public VP_UserOrdersAdapter(FragmentManager fm) {
        super(fm);
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
        switch (position){
            case 0:
                title = "طلبات سابقة";
                break;

            case 1:
                title = "طلبات حالية";
                break;


        }
        return title;
    }
}
