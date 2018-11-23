package com.odp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @author ChenHh
 * @time 2018/11/20 11:28
 * @des baseFragment
 **/
public abstract class BaseFragment<VB extends ViewDataBinding> extends Fragment {

    protected VB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContentView(), null, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    public abstract int setContentView();
}
