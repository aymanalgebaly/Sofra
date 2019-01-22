package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.categories_list.CategoriesData;
import com.example.android.sofraa.data.model.categories_list.CategoriesResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.MultiSelectionSpinner;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.shop_register.RegisterData;
import com.example.android.sofraa.data.model.shop_register.ShopRegisterResponse;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.api.widget.Widget;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopRegisterTwoFragment extends Fragment {


    private List<CategoriesData> CategoriesResponseData = new ArrayList<>();
    public int x;
    public int region_id;

    @BindView(R.id.register2_lowest_to_order)
    EditText register2LowestToOrder;
    @BindView(R.id.register2_delevry_cost)
    EditText register2DelevryCost;
    @BindView(R.id.whatsapp)
    EditText whatsapp;
    @BindView(R.id.phone_num)
    EditText phoneNumedt;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.register2_register)
    Button register2Register;
    @BindView(R.id.register2_delevry_way)
    MultiSelectionSpinner register2DelevryWay;
    Unbinder unbinder;

    private String minimum_order, delivery_fee, phoneNum, whats, name, email, password, confirm_password;
    private int regionId;
    private Spinner categories;
    private static RequestBody requestBody;
    private Album album;
    private String path;
    private ArrayList<AlbumFile> ImagesFiles = new ArrayList<>();
    private ArrayList<CategoriesData> categoriesList = new ArrayList<>();

    //    private EditText register2LowestToOrder, register2DelevryCost, phoneNumedt, whatsapp;
    private Button btn_register;
    final ArrayList<Integer> mUserItems = new ArrayList<>();
    private SharedPreferences preferences;
    private Integer id;

    public ShopRegisterTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_register_two, container, false);

        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            RegisterData resturant_data = bundle.getParcelable("data");
            name = resturant_data.getName();
            email = resturant_data.getEmail();
            password = resturant_data.getPassword();
            confirm_password = resturant_data.getConfirm_password();
            regionId = region_id;

        }

        getCategories();

        return view;
    }

    private void getCategories() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<CategoriesResponse> categoriesResponse = api.getCategoriesResponse();
        categoriesResponse.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        CategoriesResponseData = response.body().getData();
                        MultiSpinnerSelected(CategoriesResponseData);
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public static RequestBody convertToRequestBody(String part) {
        if (part != null) {
            requestBody = RequestBody.create(MediaType.parse("multipart / form - data"), part);
            return requestBody;
        } else {
            return null;
        }
    }

    private void CreateUser() {
        minimum_order = register2LowestToOrder.getText().toString();
        delivery_fee = register2DelevryCost.getText().toString();
        phoneNum = phoneNumedt.getText().toString();
        whats = whatsapp.getText().toString();

        path = ImagesFiles.get(0).getPath();

        if (TextUtils.isEmpty(minimum_order)) {
            register2LowestToOrder.setError("Minimum Order is Required");
        } else if (TextUtils.isEmpty(delivery_fee)) {
            register2DelevryCost.setError("Delivery Fee is Required");
        } else if (TextUtils.isEmpty(phoneNum)) {
            phoneNumedt.setError("Phone Number is Required");
        } else if (TextUtils.isEmpty(whats)) {
            whatsapp.setError("Phone Number is Required");
        } else {

            ArrayList<RequestBody> category = new ArrayList<>();
            category.add(convertToRequestBody(String.valueOf(mUserItems)));

            List<RequestBody> categoriesItems = new ArrayList<>();

            for (int i = 0; i < CategoriesResponseData.size(); i++) {
                for (int j = 0; j < register2DelevryWay.getSelectedStrings().size(); j++) {
                    if (CategoriesResponseData.get(i).getName().equals(register2DelevryWay.getSelectedStrings().get(j))) {
                        categoriesItems.add(convertToRequestBody(String.valueOf(CategoriesResponseData.get(i).getId())));
                    }
                }
            }

            Retrofit retrofit = RetrofitClient.getShopInstance();
            API api = retrofit.create(API.class);
            Call<ShopRegisterResponse> call = api.getShopRegisterResponse
                    (convertToRequestBody(name),
                            convertToRequestBody(email),
                            convertToRequestBody(password),
                            convertToRequestBody(confirm_password),
                            convertToRequestBody(phoneNum),
                            convertToRequestBody(whats),
                            convertToRequestBody(String.valueOf(regionId)),
                            categoriesItems,
                            convertToRequestBody(delivery_fee),
                            convertToRequestBody(minimum_order),
                            getImageToUpload(path, "photo")
                            , convertToRequestBody("open"));
            call.enqueue(new Callback<ShopRegisterResponse>() {
                @Override
                public void onResponse(Call<ShopRegisterResponse> call, Response<ShopRegisterResponse> response) {

                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {

//                            id = response.body().getRegisterData().getId();

                            sharedLogin();

                            Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();

                            ShopActivity shopActivity = (ShopActivity) getActivity();
                            MyProductsFragment fragment = new MyProductsFragment();
                            shopActivity.displaySelectedFragment(fragment);

                        }
                    }
                }

                @Override
                public void onFailure(Call<ShopRegisterResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

//    public void displaySelectedFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.shop_frame, fragment);
//        fragmentTransaction.addToBackStack(null).commit();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                                .into(imageView4);

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

    @OnClick({R.id.imageView4, R.id.register2_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register2_delevry_way:
                break;
            case R.id.imageView4:
                addImage();
                break;
            case R.id.register2_register:

                CreateUser();

                break;
        }
    }

    private void MultiSpinnerSelected(List<CategoriesData> categoriesData) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < categoriesData.size(); i++) {
            items.add(categoriesData.get(i).getName());
        }

        register2DelevryWay.setItems(items);
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
    public void sharedLogin() {

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("shop",MODE_PRIVATE).edit();

//        preferences = getActivity().getSharedPreferences("shop", Context.MODE_PRIVATE);

        editor.putBoolean("login", true);

        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("phone",phoneNum);
        editor.putString("whats",whats);
        editor.putInt("region_id",region_id);
        editor.putString("delivery_fee", delivery_fee);
        editor.putString("path",path);
//        editor.putInt("id",id);
        editor.putString("minimum_order",minimum_order);
        editor.apply();
    }
}
