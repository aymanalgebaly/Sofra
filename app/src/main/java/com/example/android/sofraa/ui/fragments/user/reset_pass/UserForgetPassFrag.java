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
import com.example.android.sofraa.data.model.reset_password.UserResetPassResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;

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
public class UserForgetPassFrag extends Fragment {


    @BindView(R.id.user_forgetPass_email)
    EditText ed_email;
    @BindView(R.id.user_forgetPass_bu)
    Button userForgetPassBu;
    Unbinder unbinder;


    public UserForgetPassFrag() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_user_forget_pass, container, false);
        unbinder = ButterKnife.bind(this, inflate);



        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.user_forgetPass_bu)
    public void onViewClicked() {
        checkValidate();

    }

    private void checkValidate() {
        String email = ed_email.getText().toString();

        if (TextUtils.isEmpty(email)){
            ed_email.setError("Pls enter your e-mail");
        }else
            response(email);

    }

    //send code to change pass
    private void response(String email) {
        Retrofit retrofit = RetrofitClient.getUserInstance();
        API api = retrofit.create(API.class);
        Call<UserResetPassResponse> call = api.ResetPass(email);
        call.enqueue(new Callback<UserResetPassResponse>() {
            @Override
            public void onResponse(Call<UserResetPassResponse> call, Response<UserResetPassResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){

                        response.body().getData();
                        Toast.makeText(getContext(), "Done...", Toast.LENGTH_SHORT).show();

                        Fragment fragment = new UserNewPassFrag();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.user_frame, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserResetPassResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
