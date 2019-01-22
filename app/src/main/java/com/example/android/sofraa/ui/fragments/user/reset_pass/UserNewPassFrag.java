package com.example.android.sofraa.ui.fragments.user.reset_pass;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.reset_password.UserNewPassResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.ui.fragments.user.vp_food_list.UserFoodListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserNewPassFrag extends Fragment {

    @BindView(R.id.user_newPass_code)
    EditText ed_code;
    @BindView(R.id.user_newPass_pass)
    EditText ed_newPass;
    @BindView(R.id.user_newPass_confirmPass)
    EditText ed_confirmPass;
    @BindView(R.id.user_newPass_bu)
    Button userNewPassBu;
    Unbinder unbinder;


    public UserNewPassFrag() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_user_new_pass, container, false);
        unbinder = ButterKnife.bind(this, inflate);


        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.user_newPass_bu)
    public void onViewClicked() {
        CheckValidate();
    }

    private void CheckValidate() {
        String code = ed_code.getText().toString();
        String password = ed_newPass.getText().toString();
        String password_confirmation = ed_confirmPass.getText().toString();

        if (TextUtils.isEmpty(code)) {
            ed_code.setError("Enter the code");
        } else if (TextUtils.isEmpty(password)) {
            ed_newPass.setError("Enter the new password");
        } else if (TextUtils.isEmpty(password_confirmation)) {
            ed_confirmPass.setError("Enter password confirmation");
        } else
            response(code, password, password_confirmation);

    }

    //response to set new password
    private void response(String code, String password, String password_confirmation) {
        Retrofit retrofit = RetrofitClient.getUserInstance();
        API api = retrofit.create(API.class);
        Call<UserNewPassResponse> call = api.NewPass(code, password, password_confirmation);
        call.enqueue(new Callback<UserNewPassResponse>() {
            @Override
            public void onResponse(Call<UserNewPassResponse> call, Response<UserNewPassResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){

                        Toast.makeText(getContext(), "changed", Toast.LENGTH_SHORT).show();

                        Fragment fragment = new UserFoodListFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.user_frame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserNewPassResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
