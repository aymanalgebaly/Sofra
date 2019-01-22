package com.example.android.sofraa.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.sofraa.ui.fragments.user.vp_contact_us.UserComplaintFragment;
import com.example.android.sofraa.ui.fragments.user.vp_contact_us.UserEnquiryFragment;
import com.example.android.sofraa.ui.fragments.user.vp_contact_us.UserSuggestionFragment;

public class VP_UserContactUsAdapter extends FragmentPagerAdapter {

    Fragment [] fragments1 = {new UserEnquiryFragment(),new UserSuggestionFragment(),new UserComplaintFragment()};
    private String title;

    public VP_UserContactUsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments1[position];
    }

    @Override
    public int getCount() {
        return fragments1.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                title = "استعلام";
                break;
            case 1:
                title = "اقتراح";
                break;
            case 2:
                title = "شكوي";
                break;
        }
        return title;

    }
}
