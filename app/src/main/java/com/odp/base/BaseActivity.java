package com.odp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author ChenHh
 * @time 2018/11/27 16:17
 * @des activity 基类
 **/
public abstract class BaseActivity<BA extends ViewDataBinding> extends AppCompatActivity {

    protected BA binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayout());
    }

    protected abstract int getLayout();
}
