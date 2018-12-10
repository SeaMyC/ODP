package com.odp.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ChenHh
 * @time 2018/11/30 16:54
 * @des 管理文件的工具类
 **/
public class FileUtil {
    public static void saveCroppedImage(Context context,Bitmap bmp) {
        // 判断是否可以对SDcard进行操作
        if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED))
        {	  // 获取SDCard指定目录下
            String  sdCardDir = Environment.getExternalStorageDirectory()+ "/Pictures/";
            //目录转化成文件夹
            File dirFile  = new File(sdCardDir);
            //如果不存在，那就建立这个文件夹
            if (!dirFile .exists()) {
                dirFile .mkdirs();
            }
            // 在SDcard的目录下创建图片文,以当前时间为其命名，注意文件后缀，若生成为JPEG则为.jpg,若为PNG则为.png
            File file = new File(sdCardDir, System.currentTimeMillis()+".jpg");
            String fileName = file.toString();
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
                //将bitmap（数值100表示不压缩）存储到out输出流中去，图片格式为JPEG
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                //bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //通知相册更新
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    bmp, fileName, null);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);

            Toast.makeText(context, "图片保存成功~", Toast.LENGTH_SHORT).show();

        }
    }
}
