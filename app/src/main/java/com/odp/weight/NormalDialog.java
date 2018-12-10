package com.odp.weight;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.odp.R;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

/**
 * @author : fangguiliang
 * @version : 1.0.0
 * @since : 2018/10/15
 */
public class NormalDialog extends BaseDialog {
    private boolean autoDismiss = true;
    private TextView tvTitle;
    private TextView tvMsg;
    private TextView tvLeft;
    private TextView tvRight;


    public NormalDialog(Context context) {
        super(context);
        initView();
    }

    public NormalDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public NormalDialog(Context context, boolean cancelable) {
        super(context, cancelable, null);
        initView();
    }

    public NormalDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public NormalDialog(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_normal, null);
        tvTitle = view.findViewById(R.id.tv_dialog_title);
        tvMsg = view.findViewById(R.id.tv_dialog_msg);
        tvLeft = view.findViewById(R.id.tv_dialog_left);
        tvRight = view.findViewById(R.id.tv_dialog_right);
        setContentView(view);
        setNormal();
    }

    public void setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
    }

    /**
     * @param titleId 标题
     */
    @Override
    public void setTitle(int titleId) {
        setTvTitle(titleId);
    }

    /**
     * @param title 标题
     */
    @Override
    public void setTitle(@Nullable CharSequence title) {
        setTvTitle(title);
    }

    private void setTvTitle(int resId) {
        if (resId > 0) {
            tvTitle.setText(resId);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    private void setTvTitle(CharSequence sequence) {
        if (sequence != null && sequence.length() > 0) {
            tvTitle.setText(sequence);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    /**
     * @param resId 中间内容
     */
    public void setMessage(int resId) {
        if (resId > 0) {
            tvMsg.setText(resId);
            tvMsg.setVisibility(View.VISIBLE);
        } else {
            tvMsg.setVisibility(View.GONE);
        }
    }

    /**
     * @param sequence 中间内容
     */
    public void setMessage(CharSequence sequence) {
        if (sequence != null && sequence.length() > 0) {
            tvMsg.setText(sequence);
            tvMsg.setVisibility(View.VISIBLE);
        } else {
            tvMsg.setVisibility(View.GONE);
        }
    }

    /**
     * 左边按钮
     */
    public void setLeft(@StringRes int resId) {
        this.setLeft(resId, R.color.colorPrimary, null);
    }

    /**
     * 左边按钮
     */
    public void setLeft(@StringRes int resId, View.OnClickListener listener) {
        this.setLeft(resId, R.color.colorPrimary, listener);
    }

    /**
     * 左边按钮
     *
     * @param resId
     * @param color
     * @param listener
     */
    public void setLeft(int resId, @ColorRes int color, View.OnClickListener listener) {
        if (resId > 0) {
            tvLeft.setText(resId);
            tvLeft.setVisibility(View.VISIBLE);
        } else {
            tvLeft.setVisibility(View.GONE);
        }
        tvLeft.setTextColor(ContextCompat.getColor(getContext(), color));
        tvLeft.setOnClickListener(v -> {
            if (autoDismiss) {
                dismiss();
            }
            if (listener != null) {
                listener.onClick(v);
            }
        });
    }


    /**
     * 右边按钮
     *
     * @param resId
     */
    public void setRight(@StringRes int resId) {
        this.setRight(resId, R.color.colorPrimary, null);
    }

    /**
     * 右边按钮
     *
     * @param resId
     * @param listener
     */
    public void setRight(@StringRes int resId, View.OnClickListener listener) {
        this.setRight(resId, R.color.colorPrimary, listener);
    }

    /**
     * 右边按钮
     *
     * @param resId
     * @param color
     * @param listener
     */
    public void setRight(int resId, @ColorRes int color, View.OnClickListener listener) {
        if (resId > 0) {
            tvRight.setText(resId);
            tvRight.setVisibility(View.VISIBLE);
        } else {
            tvRight.setVisibility(View.GONE);
        }
        tvRight.setTextColor(ContextCompat.getColor(getContext(), color));
        tvRight.setOnClickListener(v -> {
            if (autoDismiss) {
                dismiss();
            }
            if (listener != null) {
                listener.onClick(v);
            }
        });
    }

}
