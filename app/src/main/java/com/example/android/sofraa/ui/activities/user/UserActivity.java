package com.example.android.sofraa.ui.activities.user;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.ui.fragments.user.UserAboutAppFrag;
import com.example.android.sofraa.ui.fragments.user.UserFoodListDetailFrag;
import com.example.android.sofraa.ui.fragments.user.UserFoodOrdersFrag;
import com.example.android.sofraa.ui.fragments.user.UserLoginFragment;
import com.example.android.sofraa.ui.fragments.user.UserNewOffersFrag;
import com.example.android.sofraa.ui.fragments.user.UserNotifyFragment;
import com.example.android.sofraa.ui.fragments.user.vp_contact_us.VP_ContactUsFragment;
import com.example.android.sofraa.ui.fragments.user.vp_my_orders.VP_MyOrdersFragment;


public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView toolbar_title;
    private ImageView basket_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = toolbar.findViewById(R.id.user_toolbar_title);
        basket_img = findViewById(R.id.user_toolbar_basket_img);

        setSupportActionBar(toolbar);
        toolbar_title.setText("طلب طعام");

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_View =  navigationView.getHeaderView(0);
        ImageView nav_setting = nav_View.findViewById(R.id.user_nav_setting);
        nav_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UserLoginFragment();
                displaySelectedFragment(fragment);
                drawer.closeDrawer(GravityCompat.START);
            }
        });


        basket_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UserBasketFragment();
                displaySelectedFragment(fragment);
                toolbar.setTitle("سلة الطلبات");
            }
        });

        navigationView.setCheckedItem(R.id.home);
        Fragment fragment = new UserFoodOrdersFrag();
        displaySelectedFragment(fragment);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            Fragment fragment = new UserFoodOrdersFrag();
            displaySelectedFragment(fragment);
            toolbar_title.setText("طلب طعام");


        } else if (id == R.id.orders) {

            Fragment fragment = new VP_MyOrdersFragment();
            displaySelectedFragment(fragment);
            toolbar_title.setText("طلباتي");

        } else if (id == R.id.notification) {

            Fragment fragment = new UserNotifyFragment();
            displaySelectedFragment(fragment);
            toolbar_title.setText("الاشعارات");


        } else if (id == R.id.new_offers) {

            Fragment fragment = new UserNewOffersFrag();
            displaySelectedFragment(fragment);


        } else if (id == R.id.about_app) {

            Fragment fragment = new UserAboutAppFrag();
            displaySelectedFragment(fragment);
            toolbar_title.setText("عن التطبيق");

        } else if (id == R.id.condition) {

        } else if (id == R.id.share) {

        } else if (id == R.id.contact_us) {

            Fragment fragment = new VP_ContactUsFragment();
            displaySelectedFragment(fragment);
            toolbar_title.setText("تواصل معنا");

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.user_frame, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }


}
