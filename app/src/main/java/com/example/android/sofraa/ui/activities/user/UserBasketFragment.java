package com.example.android.sofraa.ui.activities.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.BasketAdapter;
import com.example.android.sofraa.adapter.UserBasketAdapter;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.example.android.sofraa.data.model.service_api.RoomDao;
import com.example.android.sofraa.data.model.service_api.RoomManger;
import com.example.android.sofraa.ui.fragments.user.UserDetailsOfferFragment;
import com.example.android.sofraa.ui.fragments.user.UserFoodOrdersFrag;

import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserBasketFragment extends Fragment {


    @BindView(R.id.user_basket_rv)
    RecyclerView recyclerView;
    @BindView(R.id.user_basket_cost)
    TextView userBasketCost;
    @BindView(R.id.user_basket_addMore_btn)
    Button btn_addMore;
    @BindView(R.id.user_basket_doRequest_btn)
    Button btn_doRequest;
    Unbinder unbinder;


    private BasketAdapter adapter;
    private RoomDao roomDao;
    private List<ItemFoodData> foodDataList;


    public UserBasketFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_user_basket, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        RoomManger roomManger = RoomManger.getInstance(getActivity());
        roomDao = roomManger.roomDao();

        addFoodItems();

        return inflate;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.user_basket_addMore_btn, R.id.user_basket_doRequest_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.user_basket_addMore_btn:
                Fragment fragment = new UserFoodOrdersFrag();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.user_frame,fragment).addToBackStack(null).commit();
                break;

            case R.id.user_basket_doRequest_btn:

//                saveItems(foodDataList);

                UserDetailsOfferFragment userDetailsOfferFragment = new UserDetailsOfferFragment();
                UserActivity userActivity = (UserActivity)getActivity();
                userActivity.displaySelectedFragment(userDetailsOfferFragment);
                break;
        }
    }




//    private void setupRecycle() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new BasketAdapter(getActivity());
//        recyclerView.setAdapter(adapter);
//    }
    private void addFoodItems() {

        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override

            public void run() {

                foodDataList = roomDao.getAllItem();


                adapter = new BasketAdapter(getActivity());

                adapter.setDataBasketAdapter(foodDataList);

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                recyclerView.setAdapter(adapter);

            }

        });
    }
//    private void saveItems(final List<ItemFoodData> foodDataList){
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 0; i <foodDataList.size() ; i++) {
//
//                    roomDao.insertItemToCar((ItemFoodData) foodDataList);
//                }
//            }
//        });
//
//    }
}
