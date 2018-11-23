package com.odp.module.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odp.R;
import com.odp.databinding.FragmentIosBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author ChenHh
 * @time 2018/11/2 17:41
 * @des IOS页面
 **/
public class IOSFragment extends Fragment {

    private FragmentIosBinding binding;
    public static IOSFragment instance() {
        IOSFragment iosFragment = new IOSFragment();
        return iosFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(binding==null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ios, container, false);
        }
        return binding.getRoot();
    }
}
