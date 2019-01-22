package com.example.android.sofraa.ui.fragments.user.vp_food_list;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.VP_UserFoodListAdapter;
import com.example.android.sofraa.data.model.list_of_restaurants.Category;
import com.example.android.sofraa.data.model.list_of_restaurants.Datum;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class VP_FoodListFragment extends Fragment {


    @BindView(R.id.user_foodList_img)
    ImageView foodImg;
    @BindView(R.id.user_foodList_shopName)
    TextView txt_shopName;
    @BindView(R.id.user_foodList_shopDesc)
    TextView txt_shopDescription;
    @BindView(R.id.user_foodList_rating)
    RatingBar shopRating;
    @BindView(R.id.user_foodList_lowest_order)
    TextView txt_lowestOrder;
    @BindView(R.id.user_foodList_delevry_cost)
    TextView txt_delevryCost;
    @BindView(R.id.user_foodList_tabs)
    TabLayout tabLayout;
    @BindView(R.id.user_foodList_viewpager)
    ViewPager viewPager;
    Unbinder unbinder;

    public VP_FoodListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_vp__food_list, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        tabLayout.setupWithViewPager(viewPager);

        VP_UserFoodListAdapter fragmentsAdapter = new VP_UserFoodListAdapter(getChildFragmentManager());
        viewPager.setAdapter(fragmentsAdapter);

        View root = tabLayout.getChildAt(0);
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

            String availability = resturant_data.getAvailability();

            txt_shopName.setText(resturant_data.getName());
            txt_lowestOrder.setText(resturant_data.getMinimumCharger());
            txt_delevryCost.setText(resturant_data.getDeliveryCost());
            Picasso.get().load((resturant_data.getPhotoUrl())).into(foodImg);

            for (int i = 0; i < resturant_data.getCategories().size(); i++) {
                Category category = resturant_data.getCategories().get(i);
                txt_shopDescription.setText(category.getName());
            }
//            switch (availability) {
//                case "open":
//                    tx_OpenClose.setTextColor(getResources().getColor(R.color.green));
//                    tx_OpenClose.setText(availability);
//                    break;
//                case "close":
//                    tx_OpenClose.setTextColor(getResources().getColor(R.color.red));
//                    tx_OpenClose.setText(availability);
//                    break;
//            }
        }


        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
