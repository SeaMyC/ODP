package com.odp.module.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.odp.R;
import com.odp.databinding.ActivityMainBinding;
import com.odp.module.main.adapter.TabPageAdapter;
import com.odp.module.video.VideoActivity;
import com.odp.util.ToastUtils;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();

        initEvent();
    }

    private void initView() {
        toolbar = binding.appBarMain.toolbar;
        drawer = binding.drawerLayout;
        tabLayout = binding.appBarMain.contentMain.tlType;
        viewPager = binding.appBarMain.contentMain.vpData;
    }

    private void initEvent() {
        setSupportActionBar(toolbar);

        viewPager.setAdapter(new TabPageAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        binding.appBarMain.fab.setOnClickListener(v -> Snackbar.make(v, "author email:   365919006@qq.com",
                Snackbar.LENGTH_LONG).setAction("Action",
                null).show());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("WrongConstant")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        TextView tv = new TextView(this);
        tv.setTextColor(getColor(R.color.white));
        tv.setBackgroundColor(getColor(R.color.gray));
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_video:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case R.id.nav_version:
                try {
                    String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                    tv.setText(String.valueOf("version: " + versionName));
                    new ToastUtils(this, tv, 200).show();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.nav_author:
                tv.setText(String.valueOf("author: ODP"));
                new ToastUtils(this, tv, 200).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
