package com.odp.util;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.odp.R;

/**
 * @author ChenHh
 * @time 2018/12/29 15:01
 * @des toast 工具类
 **/
public class ToastUtils {
    private Toast toast;
    private LinearLayout toastView;

    /**
     * 完全自定义布局Toast
     */
    public ToastUtils(Context context, View view, int duration) {
        toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(duration);
    }

    /**
     * 向Toast中添加自定义View
     */
    public ToastUtils addView(View view, int position) {
        toastView = (LinearLayout) toast.getView();
        toastView.addView(view, position);
        return this;
    }

    /**
     * 设置Toast字体及背景
     */
    public ToastUtils setToastBackground(int messageColor, int background) {
        View view = toast.getView();
        if (view != null) {
            TextView message = (TextView) view.findViewById(R.id.message);
            message.setBackgroundResource(background);
            message.setTextColor(messageColor);
        }

        return this;
    }

    /**
     * 短时间显示Toast
     */
    public ToastUtils Short(Context context, CharSequence message) {
        if (toast == null
                || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }

    /**
     * 长时间显示toast
     */
    public ToastUtils Long(Context context, CharSequence message) {
        if (toast == null
                || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        return this;
    }

    /**
     * 自定义显示Toast的时长
     */
    public ToastUtils Indefinite(Context context, CharSequence message,
                                 int duration) {
        if (toast == null
                || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, duration);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }

        return this;
    }

    /**
     * 显示Toast
     */
    public ToastUtils show() {
        toast.show();
        return this;
    }

    /**
     * 获取Toast
     */
    public Toast getToast() {
        return toast;
    }
}