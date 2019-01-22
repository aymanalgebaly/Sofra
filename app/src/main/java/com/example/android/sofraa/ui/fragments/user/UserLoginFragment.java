package com.example.android.sofraa.ui.fragments.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.login.UserLoginResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.ui.fragments.UserRegisterFragment;
import com.example.android.sofraa.ui.fragments.user.reset_pass.UserForgetPassFrag;

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
public class UserLoginFragment extends Fragment {


    private EditText userLoginMail, userLoginPass;
    private Button btn_login, btn_register;
    public String api_token;
    private SharedPreferences preferences;

    public UserLoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
//        unbinder = ButterKnife.bind(this, inflate);

        userLoginMail = view.findViewById(R.id.user_login_mail);
        userLoginPass = view.findViewById(R.id.user_login_pass);

        btn_register = view.findViewById(R.id.user_login_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new UserRegisterFragment();
                displaySelectedFragment(fragment);
            }
        });

        btn_login = view.findViewById(R.id.user_login_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidate();


            }
        });

        return view;
    }


    private void checkValidate() {

        String email = userLoginMail.getText().toString();
        String password = userLoginPass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            userLoginMail.setError("Pls enter your email");
        } else if (TextUtils.isEmpty(password)) {
            userLoginPass.setError("Pls enter your password");
        } else
            loginResponse(email, password);
    }


    private void loginResponse(String email, String password) {

        Retrofit retrofit = RetrofitClient.getUserInstance();
        API api = retrofit.create(API.class);
        Call<UserLoginResponse> call = api.setUserLogin(email, password);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {

                        api_token = response.body().getData().getApiToken();
                        SharedLogin();

                        Fragment fragment = new UserFoodOrdersFrag();
                        displaySelectedFragment(fragment);

                    } else
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {

            }
        });

    }


    public void displaySelectedFragment(Fragment fragment) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.user_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void SharedLogin() {
        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("user", MODE_PRIVATE).edit();

        editor.putBoolean("login", true);
        editor.putString("api_token", api_token);
        editor.apply();

    }
}
