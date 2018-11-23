package com.odp.module.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odp.R;
import com.odp.databinding.FragmentAndroidBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author ChenHh
 * @time 2018/11/2 17:40
 * @des android的页面
 **/
public class AndroidFragment extends Fragment {
    private FragmentAndroidBinding binding;
    private static AndroidFragment androidFragment;

    public static AndroidFragment instance() {
        if (androidFragment == null) {
            androidFragment = new AndroidFragment();
        }
        return androidFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_android, container, false);
        }
        return binding.getRoot();
    }
}
