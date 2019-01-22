package com.example.android.sofraa.ui.fragments.user.vp_contact_us;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.VP_UserContactUsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class VP_ContactUsFragment extends Fragment {


    @BindView(R.id.user_contactus_tabs)
    TabLayout tabLayout;
    @BindView(R.id.user_contactus_viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public VP_ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_user_contact_us, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        tabLayout.setupWithViewPager(viewPager);
        VP_UserContactUsAdapter adapter = new VP_UserContactUsAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.black));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }



        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
