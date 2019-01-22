package com.example.android.sofraa.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.sofraa.ui.fragments.shop.CurrentOrdersFragment;
import com.example.android.sofraa.ui.fragments.shop.NewOrdersFragment;
import com.example.android.sofraa.ui.fragments.shop.OldOrdersFragment;

public class VP_ShopOrdersAdapter extends FragmentPagerAdapter {

    Fragment fragments[]={new NewOrdersFragment(),new CurrentOrdersFragment(),new OldOrdersFragment()};

    String title;
    public VP_ShopOrdersAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            title = "New Orders";
        }
        if (position == 1){
            title = "Current Orders";
        }
        if (position == 2){
            title = "Old Orders";
        }
        return title;
    }
}
