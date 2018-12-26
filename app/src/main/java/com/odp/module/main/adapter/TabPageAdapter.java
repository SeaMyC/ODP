package com.odp.module.main.adapter;

import android.view.ViewGroup;

import com.odp.module.main.view.AndroidFragment;
import com.odp.module.main.view.IOSFragment;
import com.odp.module.main.view.SportFragment;
import com.odp.module.main.view.WealFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author ChenHh
 * @time 2018/11/2 15:10
 * @des 展示fragment的适配器
 **/
public class TabPageAdapter extends FragmentPagerAdapter {
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();


    public TabPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        intData();

    }

    private void intData() {
        titles.clear();
        titles.add("Girl");
        titles.add("Android");
        titles.add("iOS");
        titles.add("Sport");
        fragments.add(WealFragment.instance());
        fragments.add(AndroidFragment.instance());
        fragments.add(new IOSFragment());
        fragments.add(new SportFragment());

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
