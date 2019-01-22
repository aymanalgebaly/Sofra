package com.example.android.sofraa.ui.fragments.user.vp_my_orders;


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
import com.example.android.sofraa.adapter.VP_UserOrdersAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class VP_MyOrdersFragment extends Fragment {


    @BindView(R.id.user_orders_tabs)
    TabLayout tabLayout;
    @BindView(R.id.user_orders_viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public VP_MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_vp__my_orders, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        tabLayout.setupWithViewPager(viewPager);
        VP_UserOrdersAdapter adapter = new VP_UserOrdersAdapter(getChildFragmentManager());
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
