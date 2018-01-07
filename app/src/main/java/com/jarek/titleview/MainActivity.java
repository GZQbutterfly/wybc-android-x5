package com.jarek.titleview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.KeyEvent;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        webView = (WebView) findViewById(R.id.webView);
        //WebView加载web资源
        webView.loadUrl("http://qaservice.365bencao.cn");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        setConfig();
    }

    private void setConfig() {
        WebSettings websetting = webView.getSettings();

        websetting.setJavaScriptEnabled(true);   //与JS交互
        websetting.setDomStorageEnabled(true);    //开启DOM形式存储
        websetting.setDatabaseEnabled(true);   //开启数据库形式存储
        String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();   //缓存数据的存储地址
        websetting.setAppCachePath(appCacheDir);
        websetting.setAppCacheEnabled(true);  //开启缓存功能
        websetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);      //缓存模式
        websetting.setAllowFileAccess(true);

        //自动加载图片
        websetting.setLoadsImagesAutomatically(true);
        websetting.setBuiltInZoomControls(true);
        //自动缩放
        websetting.setSupportZoom(true);
        //设置webview的插件转状态
        websetting.setPluginState(WebSettings.PluginState.ON);
        //设置默认的字符编码
        websetting.setDefaultTextEncodingName("utf-8");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    System.out.println("loaded");
                } else {
                    // 加载中
                    System.out.println("loading");
                }
            }
        });
    }

    private long firstClickBack = System.currentTimeMillis();

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                long secondClickBack = System.currentTimeMillis();
                if (secondClickBack - firstClickBack > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstClickBack = secondClickBack;
                } else {
                    System.exit(0);//退出程序
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
