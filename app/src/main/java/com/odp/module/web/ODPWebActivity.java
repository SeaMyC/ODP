package com.odp.module.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.odp.R;
import com.odp.databinding.ActivityWebBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @author ChenHh
 * @time 2018/11/23 17:31
 * @des 加载网页的页面
 **/
public class ODPWebActivity extends AppCompatActivity {
    public static final String WEB_URL = "web_url";
    private WebView webView;
    private ProgressBar progressBar;
    private String url;
    private ActivityWebBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        url = getIntent().getStringExtra(WEB_URL);
        webView = binding.wvDetail;
        progressBar = binding.progressbar;
        initWebView();
    }

    private void initWebView() {
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
        webView.addJavascriptInterface(this, "android");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//没有缓存
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl(url);
    }

    public static class Builder {
        Intent intent;

        public Builder(Context context) {
            intent = new Intent(context, ODPWebActivity.class);
        }

        public Intent putWebUrl(String url) {
            intent.putExtra(WEB_URL, url);
            return intent;
        }
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://www.google.com/")) {
                Toast.makeText(ODPWebActivity.this, "国内不能访问google,拦截该url", Toast.LENGTH_LONG).show();
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {//点击返回按钮的时候判断有没有上一页
            webView.goBack(); // goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * JS调用android的方法
     *
     * @param str
     */
    @JavascriptInterface //仍然必不可少
    public void getClient(String str) {
        Log.i("odp", "html调用客户端:" + str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        webView.destroy();
        webView = null;
    }
}