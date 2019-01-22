package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.add_new_item_shop.AddNewItemShopResponse;
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
import java.util.ArrayList;
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
public class AddNewProductFragment extends Fragment {


    private static RequestBody requestBody;
    @BindView(R.id.addFood_name)
    EditText addFoodName;
    @BindView(R.id.addFood_description)
    EditText addFoodDescription;
    @BindView(R.id.addFood_salary)
    EditText addFoodSalary;
    @BindView(R.id.addFood_timeOrder)
    EditText addFoodTimeOrder;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.addFood_img)
    ImageView addFoodImg;
    @BindView(R.id.addFood_btn_add)
    Button addFoodBtnAdd;
    Unbinder unbinder;

    private String s_addFoodName, s_addFoodDescription, s_addFoodSalary, s_addFoodTimeOrder,
            api_token ;
    private ArrayList<AlbumFile> ImagesFiles;
    private Album album;
    private String path;
    private SharedPreferences preferences;

    public AddNewProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_product, container, false);

        preferences = getActivity().getSharedPreferences("shop",Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token", "");

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    private void CreateNewItem() {
        s_addFoodName = addFoodName.getText().toString();
        s_addFoodDescription = addFoodDescription.getText().toString();
        s_addFoodSalary = addFoodSalary.getText().toString();
        s_addFoodTimeOrder = addFoodTimeOrder.getText().toString();

        if (TextUtils.isEmpty(s_addFoodName)) {
            addFoodName.setError("Item Name is Required");
        } else if (TextUtils.isEmpty(s_addFoodDescription)) {
            addFoodDescription.setError("Description is Required");
        } else if (TextUtils.isEmpty(s_addFoodSalary)) {
            addFoodSalary.setError("Item Salary is Required");
        } else if (TextUtils.isEmpty(s_addFoodTimeOrder)) {
            addFoodTimeOrder.setError("Time For Item Ready is Required");
        } else {

            Retrofit retrofit = RetrofitClient.getShopInstance();
            API api = retrofit.create(API.class);
            Call<AddNewItemShopResponse> itemShopResponseCall = api.getAddNewItemShopResponse(convertToRequestBody(s_addFoodDescription), convertToRequestBody(s_addFoodSalary),
                    convertToRequestBody(s_addFoodTimeOrder), convertToRequestBody(s_addFoodName), getImageToUpload(path, "photo"),
                    convertToRequestBody(api_token));
            itemShopResponseCall.enqueue(new Callback<AddNewItemShopResponse>() {
                @Override
                public void onResponse(Call<AddNewItemShopResponse> call, Response<AddNewItemShopResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getContext(), "Successful Add New Item", Toast.LENGTH_SHORT).show();

                            MyProductsFragment fragment = new MyProductsFragment();
                            ShopActivity shopActivity = (ShopActivity) getActivity();
                            shopActivity.displaySelectedFragment(fragment);
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddNewItemShopResponse> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.addFood_img, R.id.addFood_btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addFood_img:
                addImage();
                break;
            case R.id.addFood_btn_add:
                CreateNewItem();
                break;
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
                                .into(addFoodImg);

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
//        transaction.replace(R.id.shop_frame, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }
}
