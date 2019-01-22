package com.example.android.sofraa.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.data.model.service_api.API;
import com.example.android.sofraa.data.model.service_api.RetrofitClient;
import com.example.android.sofraa.settings_media.SettingsMediaResponse;
import com.example.android.sofraa.ui.activities.shop.ShopActivity;
import com.example.android.sofraa.ui.activities.user.UserActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity {


    private ConstraintLayout constraintLayout;
    private Button btn_order_food, btn_sell_food;
    private SharedPreferences preferences;
    private String email, password, instagram, twitter;
    private ImageView inista_img, twitter_img;
    private String x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        x = FirebaseInstanceId.getInstance().getToken();

        Log.d("", "onCreate: " + x);

        constraintLayout = findViewById(R.id.constraint_splash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        constraintLayout.setAnimation(animation);

        btn_sell_food = findViewById(R.id.sell_food_btn);
        btn_order_food = findViewById(R.id.order_food_btn);

        btn_order_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SplashActivity.this, UserActivity.class));
            }
        });

        btn_sell_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SplashActivity.this, ShopActivity.class));

            }
        });

        inista_img = findViewById(R.id.insta_img);
        inista_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse(instagram));
                startActivity(browserIntent5);
            }
        });

        twitter_img = findViewById(R.id.twitter_img);
        twitter_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse(twitter));
                startActivity(browserIntent6);
            }
        });

        preferences = getSharedPreferences("user", MODE_PRIVATE);
        email = preferences.getString("email", "");
        password = preferences.getString("password", "");

        MediaResponse();
    }

    private void MediaResponse() {
        Retrofit retrofit = RetrofitClient.general();
        API api = retrofit.create(API.class);
        Call<SettingsMediaResponse> settingsMediaResponse = api.getSettingsMediaResponse(email, password);
        settingsMediaResponse.enqueue(new Callback<SettingsMediaResponse>() {
            @Override
            public void onResponse(Call<SettingsMediaResponse> call, Response<SettingsMediaResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 1) {
                        SettingsMediaResponse body = response.body();

                        instagram = body.getData().getInstagram();
                        twitter = body.getData().getTwitter();
                    }
                }
            }

            @Override
            public void onFailure(Call<SettingsMediaResponse> call, Throwable t) {

            }
        });
    }
}
