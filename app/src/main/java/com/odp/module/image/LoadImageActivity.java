package com.odp.module.image;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.odp.R;
import com.odp.base.BaseActivity;
import com.odp.databinding.ActivityImageBinding;
import com.odp.util.FileUtil;
import com.odp.weight.NormalDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.Nullable;


/**
 * @author ChenHh
 * @time 2018/11/27 16:15
 * @des 加载图片
 **/
public class LoadImageActivity extends BaseActivity<ActivityImageBinding> {
    private static final String WEB_URL = "web_url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //无title  全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        String url = getIntent().getStringExtra(WEB_URL);
        Log.d("odp", "url:" + url);
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.img_default_meizi)
                .error(R.drawable.load_err)
                .into(binding.odpIv);

        binding.odpIv.setLongPressListener(view -> {
            NormalDialog normalDialog = new NormalDialog(this);
            normalDialog.setMessage(R.string.dialog_keep_bg_in_phone);
            normalDialog.show();
            normalDialog.setRight(R.string.comment_sure, v -> {
                RxPermissions permissions = new RxPermissions(LoadImageActivity.this);
                permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) {
                                FileUtil.saveCroppedImage(LoadImageActivity.this, getBitmap(binding.odpIv));
                            } else {
                                Toast.makeText(LoadImageActivity.this, "保存图片需要权限喔~", Toast.LENGTH_SHORT).show();
                            }
                        });
            });
            normalDialog.setLeft(R.string.comment_cancel, v -> normalDialog.dismiss());
        });

    }


    public Bitmap getBitmap(View view) {
        //获取view的长宽
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        //若传入的view长或宽为小于等于0，则返回，不生成图片
        if (width <= 0 || height <= 0) {
            return null;
        }
        //生成一个ARGB8888的bitmap，宽度和高度为传入view的宽高
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        if (bm == null) {
            return null;
        }
        //根据bitmap生成一个画布
        Canvas canvas = new Canvas(bm);
        //注意：这里是解决图片透明度问题，给底色上白色，若存储时保存的为png格式的图，则无需此步骤
        canvas.drawColor(Color.WHITE);
        //手动将这个视图渲染到指定的画布上
        view.draw(canvas);
        return bm;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_image;
    }

    public static class Builder {

        Intent intent;

        public Builder(Context context) {
            intent = new Intent(context, LoadImageActivity.class);
        }

        public Intent putWebUrl(String url) {
            intent.putExtra(WEB_URL, url);
            return intent;
        }
    }
}
