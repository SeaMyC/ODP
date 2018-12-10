package com.odp.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class ScreenUtils {
    private static int screen_height = 0;
    private static int screen_width = 0;

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpInt(Context context, float px) {
        return (int) (pxToDp(context, px) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        if (screen_width == 0) {
            screen_width = context.getResources().getDisplayMetrics().widthPixels;
        }
        return screen_width;
    }

    public static int getScreenHeight(Context context) {
        if (screen_height == 0) {
            screen_height = context.getResources().getDisplayMetrics().heightPixels;
        }
        return screen_height;
    }

    public static float getRatio(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        float ratioWidth = (float) screenWidth / 480;
        float ratioHeight = (float) screenHeight / 800;

        return Math.min(ratioWidth, ratioHeight);
    }

    public static int getStatusBarHeight(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
