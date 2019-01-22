package com.example.android.sofraa.ui.fragments.shop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.commission.CommissionData;
import com.example.android.sofraa.data.model.commission.CommissionResponse;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommisionFragment extends Fragment {
    TextView rest_sales,app_commission,rest_paid,residual;
    private String api_token = "EuqQtEiKiG4OfshU49UltxUnvySicD3T1eW4BBjdjIlMqyGJPlYauzTOH0lv";
    private String S_rest_sales;
    private String S_app_commission;
    private String S_rest_paid;
    private String S_residual;
    private Integer total,commissions,payments;
    private String commission;


    public CommisionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commision, container, false);

        rest_sales = view.findViewById(R.id.txt_restaurant_sales_number);
        app_commission = view.findViewById(R.id.txt_app_commission_number);
        rest_paid = view.findViewById(R.id.txt_what_has_been_paid_number);
        residual = view.findViewById(R.id.txt_Residual_number);

//        S_rest_sales = rest_sales.getText().toString();
//        S_app_commission = app_commission.getText().toString();
//        S_rest_paid = rest_paid.getText().toString();
//        S_residual = rest_sales.getText().toString();

        Retrofit retrofit = RetrofitClient.getShopInstance();
        API api = retrofit.create(API.class);
        Call<CommissionResponse> commissionResponse = api.getCommissionResponse(api_token);
        commissionResponse.enqueue(new Callback<CommissionResponse>() {
            @Override
            public void onResponse(Call<CommissionResponse> call, Response<CommissionResponse> response) {
                if (response.body() != null){
                    if (response.body().getStatus() == 1){
                        CommissionData body = response.body().getCommissionData();

//                         total = body.getTotal();
//                         commissions = body.getCommissions();
//                         payments = body.getPayments();
//                         commission = body.getCommission();

                        rest_sales.setText(String.valueOf(body.getTotal()));
                        rest_paid.setText(String.valueOf(body.getCommissions()));
                        residual.setText(String.valueOf(body.getPayments()));
                        app_commission.setText(String.valueOf(body.getCommission()));

                    }
                }
            }

            @Override
            public void onFailure(Call<CommissionResponse> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
