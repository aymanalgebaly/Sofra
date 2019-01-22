package com.example.android.sofraa.ui.fragments.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.adapter.UserDetailsOfferAdapter;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.example.android.sofraa.data.model.service_api.RoomDao;
import com.example.android.sofraa.data.model.service_api.RoomManger;

import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsOfferFragment extends Fragment {


    @BindView(R.id.rv_userDetailsOffer)
    RecyclerView rvUserDetailsOffer;
    @BindView(R.id.txt_total_offer_details)
    TextView txtTotalOfferDetails;
    @BindView(R.id.txt_delivery_address)
    TextView txtDeliveryAddress;
    @BindView(R.id.radioButton_cssh)
    RadioButton radioButtonCssh;
    @BindView(R.id.textView15)
    TextView textView15;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.textView27)
    TextView textView27;
    @BindView(R.id.textView28)
    TextView textView28;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.textView33)
    TextView textView33;
    @BindView(R.id.textView34)
    TextView textView34;
    @BindView(R.id.button)
    Button button;
    Unbinder unbinder;
    private RoomDao roomDao;
    private UserDetailsOfferAdapter adapter;

    public UserDetailsOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details_offer, container, false);


        unbinder = ButterKnife.bind(this, view);

        RoomManger roomManger = RoomManger.getInstance(getActivity());
        roomDao = roomManger.roomDao();

//        addFoodItems();
        return view;
    }

//    private void addFoodItems() {
//
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//
//            @Override
//
//            public void run() {
//
//                List<ItemFoodData> foodDataList = roomDao.getAllItem();
//
//
//                adapter = new UserDetailsOfferAdapter(getActivity());
//
//                adapter.setUserDetailsAdapter(foodDataList);
//
//                rvUserDetailsOffer.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//                rvUserDetailsOffer.setAdapter(adapter);
//
//            }
//
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
