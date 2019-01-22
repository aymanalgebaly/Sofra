package com.example.android.sofraa.ui.fragments.shop;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.add_new_offer_shop.AddNewOfferShopResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.api.widget.Widget;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewOrderFragment extends Fragment {


    private static RequestBody requestBody;
    @BindView(R.id.et_name_of_offer)
    EditText etNameOfOffer;
    @BindView(R.id.ed_details_of_offer)
    EditText edDetailsOfOffer;
    @BindView(R.id.ed_price_of_offer)
    EditText edPriceOfOffer;
//    @BindView(R.id.addOffer_startAt)
//    TextView addOfferStartAt;
//    @BindView(R.id.addOffer_endAt)
//    TextView addOfferEndAt;
    @BindView(R.id.txt_add_imager_offer)
    TextView txtAddImagerOffer;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.add_new_offer)
    Button addNewOffer;
    Unbinder unbinder;

    private String s_etNameOfOffer, s_edDetailsOfOffer, s_edPriceOfOffer,
            api_token, s_addOfferStartAt, s_addOfferEndAt;
    private TextView addOfferStartAt,addOfferEndAt;
    private ArrayList<AlbumFile> ImagesFiles;
    private String path;
    private Album album;
    private SharedPreferences preferences;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener dateEnd;
    private DatePickerDialog.OnDateSetListener date;

    public AddNewOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_order, container, false);

        addOfferStartAt = view.findViewById(R.id.addOffer_startAt);
        addOfferEndAt = view.findViewById(R.id.addOffer_endAt);

        preferences = getActivity().getSharedPreferences("shop", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };

        addOfferStartAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateEnd();
            }

        };

        addOfferEndAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), dateEnd, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void updateDateEnd() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        addOfferEndAt.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateDate() {

        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        addOfferStartAt.setText(sdf.format(myCalendar.getTime()));

    }

    private void addNewOffer() {
        s_etNameOfOffer = etNameOfOffer.getText().toString();
        s_edDetailsOfOffer = edDetailsOfOffer.getText().toString();
        s_edPriceOfOffer = edPriceOfOffer.getText().toString();
        s_addOfferStartAt = addOfferStartAt.getText().toString();
        s_addOfferEndAt = addOfferEndAt.getText().toString();

        if (TextUtils.isEmpty(s_etNameOfOffer)) {
            etNameOfOffer.setError("Name Of Offer is Required");

        } else if (TextUtils.isEmpty(s_edDetailsOfOffer)) {
            edDetailsOfOffer.setError("Details Of Offer is Required");

        } else if (TextUtils.isEmpty(s_edPriceOfOffer)) {
            edPriceOfOffer.setError("Price Of Offer is Required");

        } else if (TextUtils.isEmpty(s_addOfferStartAt)) {
            addOfferStartAt.setError("Empty Value");

        } else if (TextUtils.isEmpty(s_addOfferEndAt)) {
            addOfferEndAt.setError("Empty Value");

        } else {
            Retrofit retrofit = RetrofitClient.getShopInstance();
            API api = retrofit.create(API.class);
            Call<AddNewOfferShopResponse> newOfferShopResponseCall = api.getAddNewOfferShopResponse(
                    convertToRequestBody(s_edDetailsOfOffer),
                    convertToRequestBody(s_edPriceOfOffer),
                    convertToRequestBody(s_addOfferStartAt),
                    convertToRequestBody(s_etNameOfOffer),
                    getImageToUpload(path, "photo"),
                    convertToRequestBody(s_addOfferEndAt),
                    convertToRequestBody(api_token));
            newOfferShopResponseCall.enqueue(new Callback<AddNewOfferShopResponse>() {
                @Override
                public void onResponse(Call<AddNewOfferShopResponse> call, Response<AddNewOfferShopResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getActivity(), "Successful Add New Offer", Toast.LENGTH_SHORT).show();

                            ShopActivity shopActivity = (ShopActivity) getActivity();
                            MyOrdersFragment myOrdersFragment = new MyOrdersFragment();
                            shopActivity.displaySelectedFragment(myOrdersFragment);
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddNewOfferShopResponse> call, Throwable t) {

                }
            });
        }
    }

    public static RequestBody convertToRequestBody(String part) {
        if (part != null) {
            requestBody = RequestBody.create(MediaType.parse("multipart / form - data"), part);
            return requestBody;
        } else {
            return null;
        }
    }

    private void addImage() {
        album = new Album();
        Album.initialize(AlbumConfig.newBuilder(getActivity())
                .setAlbumLoader(new MediaLoader())
                .setLocale(Locale.ENGLISH).build());
        album.image(this)// Image and video mix options.
                .multipleChoice()// Multi-Mode, Single-Mode: singleChoice().
                .columnCount(3) // The number of columns in the page list.
                .selectCount(1)  // Choose up to a few images.
                .camera(true) // Whether the camera appears in the Item.
                .checkedList(ImagesFiles) // To reverse the list.
                .widget(Widget.newLightBuilder(getActivity())
                        .title("")
                        .statusBarColor(Color.WHITE) // StatusBar color.
                        .toolBarColor(Color.WHITE) // Toolbar color.
                        .navigationBarColor(Color.WHITE) // Virtual NavigationBar color of Android5.0+.
                        .mediaItemCheckSelector(Color.BLUE, Color.GREEN) // Image or video selection box.
                        .bucketItemCheckSelector(Color.RED, Color.YELLOW) // Select the folder selection box.
                        .build())
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        // TODO accept the result.
                        ImagesFiles = new ArrayList<>();
                        ImagesFiles.addAll(result);
                        path = result.get(0).getPath();
                        Glide.with(getActivity())
                                .load(path)
                                .crossFade()
                                .into(imageView3);

                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        // The user canceled the operation.
                    }
                })
                .start();
    }

    public static MultipartBody.Part getImageToUpload(String pathImageFile, String Key) {

        File file = new File(pathImageFile);

        RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);

        return Imagebody;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.imageView3, R.id.add_new_offer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView3:
                addImage();
                break;
            case R.id.add_new_offer:
                addNewOffer();
                break;
        }
    }

    public class MediaLoader implements AlbumLoader {

        @Override
        public void load(ImageView imageView, AlbumFile albumFile) {
            load(imageView, albumFile.getPath());
        }

        @Override
        public void load(ImageView imageView, String url) {
            Glide.with(imageView.getContext())
                    .load(url)
//                    .error(R.drawable.placeholder)
//                    .placeholder(R.drawable.placeholder)
                    .crossFade()
                    .into(imageView);
        }
    }
//    public void displaySelectedFragment(Fragment fragment) {
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.user_frame, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }
}
