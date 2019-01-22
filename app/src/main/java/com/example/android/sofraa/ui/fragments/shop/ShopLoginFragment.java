package com.example.android.sofraa.ui.fragments.shop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.Guideline;
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

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.data.model.shop_login.LoginShopResponse;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopLoginFragment extends Fragment {


    @BindView(R.id.guideline1000)
    Guideline guideline1000;
    @BindView(R.id.guideline1001)
    Guideline guideline1001;
    @BindView(R.id.guideline1002)
    Guideline guideline1002;
    @BindView(R.id.guideline1003)
    Guideline guideline1003;
    @BindView(R.id.guideline1004)
    Guideline guideline1004;
    @BindView(R.id.guideline1005)
    Guideline guideline1005;
    @BindView(R.id.guideline1006)
    Guideline guideline1006;
    @BindView(R.id.guideline1007)
    Guideline guideline1007;
    @BindView(R.id.txt_shop_login)
    TextView txtShopLogin;
    @BindView(R.id.img_shop_login)
    ImageView imgShopLogin;
    @BindView(R.id.shop_login_mail)
    EditText shopLoginMail;
    @BindView(R.id.shop_login_pass)
    EditText shopLoginPass;
    @BindView(R.id.shop_login_login)
    Button shopLoginLogin;
    @BindView(R.id.shop_forget_pass)
    TextView shopForgetPass;
    @BindView(R.id.shop_login_register)
    Button shopLoginRegister;
    Unbinder unbinder;

    private String s_shopLoginMail,s_shopLoginPass;
    private SharedPreferences preferences;
    private String api_token;
    private Integer id;

    public ShopLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_login, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.shop_login_login, R.id.shop_forget_pass, R.id.shop_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_login_login:

                Login();

                break;
            case R.id.shop_forget_pass:
                break;
            case R.id.shop_login_register:
                ShopActivity shopActivity1 = (ShopActivity)getActivity();
                Fragment fragment = new ShopRegisterFragment();
                shopActivity1.displaySelectedFragment(fragment);
                break;
        }
    }

    private void Login() {
        s_shopLoginMail = shopLoginMail.getText().toString();
        s_shopLoginPass = shopLoginPass.getText().toString();

        if (TextUtils.isEmpty(s_shopLoginMail)){
            shopLoginMail.setError("Email is Required");
        }else if (TextUtils.isEmpty(s_shopLoginPass)){
            shopLoginPass.setError("Password is Required");
        }else {
            Retrofit retrofit = RetrofitClient.getShopInstance();
            API api = retrofit.create(API.class);
            Call<LoginShopResponse> loginShopResponse = api.getLoginShopResponse(s_shopLoginMail, s_shopLoginPass);
            loginShopResponse.enqueue(new Callback<LoginShopResponse>() {
                @Override
                public void onResponse(Call<LoginShopResponse> call, Response<LoginShopResponse> response) {
                    if (response.body() != null){
                        if (response.body().getStatus() == 1){

                            api_token = response.body().getShopLoginData().getApiToken();
                            id = response.body().getShopLoginData().getUser().getId();

                            SharedLogin();

                            ShopActivity shopActivity = (ShopActivity)getActivity();
                            MyProductsFragment fragment1 = new MyProductsFragment();
                            shopActivity.displaySelectedFragment(fragment1);

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginShopResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
    private void SharedLogin(){

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("shop", MODE_PRIVATE).edit();

//        preferences = getActivity().getSharedPreferences("shop", Context.MODE_PRIVATE);

        editor.putString("api_token",api_token);
        editor.putInt("id",id);
        editor.apply();
    }

}
