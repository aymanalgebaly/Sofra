package com.example.android.sofraa.ui.activities.shop;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.sofraa.R;
import com.example.android.sofraa.ui.fragments.shop.CommisionFragment;
import com.example.android.sofraa.ui.fragments.shop.MyOrdersFragment;
import com.example.android.sofraa.ui.fragments.shop.MyProductsFragment;
import com.example.android.sofraa.ui.fragments.shop.ShopLoginFragment;
import com.example.android.sofraa.ui.fragments.shop.ShopRegisterFragment;
import com.example.android.sofraa.ui.fragments.shop.VP_Shop_ordersFragment;
import com.example.android.sofraa.ui.fragments.shop.Vp_Shop_FoodListFragment;
import com.example.android.sofraa.ui.fragments.user.UserAboutAppFrag;
import com.example.android.sofraa.ui.fragments.user.vp_contact_us.VP_ContactUsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.shop_toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view_shop)
    NavigationView navView;
    @BindView(R.id.shop_drawer_layout)
    DrawerLayout drawerLayout;
    private TextView title;
    ShopRegisterFragment shopRegisterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        title = findViewById(R.id.txt_title);
        title.setText("منتجاتي");


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

        if (id == R.id.nav_home) {
            Fragment fragment = new Vp_Shop_FoodListFragment();
            displaySelectedFragment(fragment);
            title.setText("الصفحة الرئيسية");

        }else if (id == R.id.nav_singin){

            Fragment fragment = new ShopLoginFragment();
            displaySelectedFragment(fragment);
            title.setText("تسجيل الدخول");

        } else if (id == R.id.nav_products) {
            Fragment fragment = new MyProductsFragment();
            displaySelectedFragment(fragment);
            title.setText("منتجاتي");

        } else if (id == R.id.nav_offerProducts) {
            Fragment fragment = new VP_Shop_ordersFragment();
            displaySelectedFragment(fragment);
            title.setText("الطلبات المقدمة");

        } else if (id == R.id.nav_comission) {
            Fragment fragment = new CommisionFragment();
            displaySelectedFragment(fragment);
            title.setText("العموله");

        } else if (id == R.id.nav_offers) {
            Fragment fragment = new MyOrdersFragment();
            displaySelectedFragment(fragment);
            title.setText("عروضي");

        } else if (id == R.id.nav_aboutApp) {
            Fragment fragment = new UserAboutAppFrag();
            displaySelectedFragment(fragment);
            title.setText("عن التطبيق");

        } else if (id == R.id.nav_conditions) {

        } else if (id == R.id.nav_contactUs) {
            Fragment fragment = new VP_ContactUsFragment();
            displaySelectedFragment(fragment);
            title.setText("تواصل معنا");
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.shop_frame, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

}
