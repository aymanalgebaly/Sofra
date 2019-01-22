package com.example.android.sofraa.ui.fragments.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ItemFoodData;
import com.example.android.sofraa.data.model.offer_details.OfferDetailsResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.service_api.RoomDao;
import com.example.android.sofraa.data.model.service_api.RoomManger;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;
import com.example.android.sofraa.ui.activities.user.UserActivity;
import com.example.android.sofraa.ui.activities.user.UserBasketFragment;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserShopInfoFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class UserFoodListDetailFrag extends Fragment {

    @BindView(R.id.checked_img)
    ImageView checkedImg;
    @BindView(R.id.text_checked_title)
    TextView textCheckedTitle;
    @BindView(R.id.text_checked_details)
    TextView textCheckedDetails;
    @BindView(R.id.text_checked_price)
    TextView textCheckedPrice;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.text_checked_time_title)
    TextView textCheckedTimeTitle;
    @BindView(R.id.checked_text_number)
    TextView checkedTextNumber;
    @BindView(R.id.text_special_order)
    TextView textSpecialOrder;
    @BindView(R.id.text_many_title)
    TextView textManyTitle;
    @BindView(R.id.min_btn)
    Button minBtn;
    @BindView(R.id.text_number)
    TextView textNumber;
    @BindView(R.id.plus_btn)
    Button plusBtn;
    @BindView(R.id.add_basket_btn)
    Button addBasketBtn;
    Unbinder unbinder;
    @BindView(R.id.txt_content)
    EditText txtContent;

    private int offer_id = 1;
    private int minteger;

    private RoomDao roomDao;
    public ItemFoodData itemFoodData;

    public UserFoodListDetailFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_food_list_detail, container, false);


        unbinder = ButterKnife.bind(this, view);

        RoomManger roomManger = RoomManger.getInstance(getContext());
        roomDao = roomManger.roomDao();

        Bundle bundle = getArguments();
        if (bundle != null) {
            itemFoodData = bundle.getParcelable("restaurant");

            textCheckedTitle.setText(itemFoodData.getName());
            textCheckedDetails.setText(itemFoodData.getDescription());
            textView10.setText(itemFoodData.getPrice());
            checkedTextNumber.setText(itemFoodData.getPreparingTime());

            Picasso.get().load(itemFoodData.getPhotoUrl()).into(checkedImg);

            String s = txtContent.getText().toString();




        }


//        ViewResponse();
        return view;
    }

//    private void ViewResponse() {
//        Retrofit retrofit = RetrofitClient.general();
//        API api = retrofit.create(API.class);
//        Call<OfferDetailsResponse> offerDetailsResponse = api.getOfferDetailsResponse(offer_id);
//        offerDetailsResponse.enqueue(new Callback<OfferDetailsResponse>() {
//            @Override
//            public void onResponse(Call<OfferDetailsResponse> call, Response<OfferDetailsResponse> response) {
//                if (response.body() != null) {
//                    if (response.body().getStatus() == 1) {
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OfferDetailsResponse> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void decreaseInteger() {

        minteger = minteger - 1;

        display(minteger);
    }
    public void increaseInteger() {

        minteger = minteger + 1;

        display(minteger);

    }
    private void display(int number) {

        textNumber.setText("" + number);

    }

    @OnClick({R.id.min_btn, R.id.plus_btn, R.id.add_basket_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.min_btn:
                decreaseInteger();
                break;
            case R.id.plus_btn:
                increaseInteger();
                break;
            case R.id.add_basket_btn:
                add(itemFoodData);
                break;
        }
    }
    private void add(final ItemFoodData foodItem) {

        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override

            public void run() {

                ItemFoodData newfoodItem = foodItem;

                String count = textNumber.getText().toString();
                newfoodItem.setCounter(Integer.parseInt(count));

                roomDao.insertItemToCar(newfoodItem);

                UserBasketFragment userBasketFragment = new UserBasketFragment();

                UserActivity userActivity = (UserActivity)getActivity();
                userActivity.displaySelectedFragment(userBasketFragment);

            }

        });

    }
}