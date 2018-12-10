package com.odp.weight;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.odp.util.ScreenUtils;

/**
 * @author : fangguiliang
 * @version : 1.0.0
 * @since : 2018/4/2
 */

public class BaseDialog extends Dialog {

    protected Context context;

    protected WindowManager.LayoutParams layoutParams;

    public WindowManager.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public BaseDialog(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.context = context;
        Window window = this.getWindow();
        layoutParams = window.getAttributes();
        layoutParams.alpha = 1f;
        window.setAttributes(layoutParams);
        if (layoutParams != null) {
            layoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.gravity = Gravity.CENTER;
        }
    }

    /**
     * @param context
     * @param alpha   透明度 0.0f--1f(不透明)
     * @param gravity 方向(Gravity.BOTTOM,Gravity.TOP,Gravity.LEFT,Gravity.RIGHT)
     */
    public BaseDialog(Context context, float alpha, int gravity) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.context = context;
        Window window = this.getWindow();
        layoutParams = window.getAttributes();
        layoutParams.alpha = 1f;
        window.setAttributes(layoutParams);
        if (layoutParams != null) {
            layoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.gravity = gravity;
        }
    }

    /**
     * 隐藏头部导航栏状态栏
     */
    public void skipTools() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置全屏显示
     */
    public void setFullScreen() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    /**
     * 设置宽度match_parent
     */
    public void setFullScreenWidth() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    /**
     * 设置高度为match_parent
     */
    public void setFullScreenHeight() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    public void setOnWhole() {
        getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
    }

    /**
     * 设置全屏显示
     */
    public void setFullScreen(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        Window window = getWindow();
        window.getDecorView().setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    /**
     * 设置全屏显示
     */
    public void setNormal() {
        int padding = (int) ScreenUtils.dpToPx(context, 30);
        Window window = getWindow();
        window.getDecorView().setPadding(padding, padding, padding, padding);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

}

