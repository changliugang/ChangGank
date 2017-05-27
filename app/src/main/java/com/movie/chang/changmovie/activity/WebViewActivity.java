package com.movie.chang.changmovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebSettings;

import com.changlg.cn.tapechat.log.Loglg;
import com.movie.chang.changmovie.R;
import com.movie.chang.changmovie.view.ProgressWebView;

/**
 * 展示网页的WebViewActivity
 */
public class WebViewActivity extends AppCompatActivity {

    public static final String WEBVIEW_TITLE = "webview_title";
    public static final String WEBVIEW_URL = "webview_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        String title = "";
        String url = "";
        if (intent != null) {
            title = intent.getStringExtra(WEBVIEW_TITLE);
            url = intent.getStringExtra(WEBVIEW_URL);
        }
        setTitle(title);
        setupWebView(url);
    }

    private void setupWebView(String url) {
        if (!TextUtils.isEmpty(url)) {
            Loglg.i(url);
            ProgressWebView progressWebView = (ProgressWebView) findViewById(R.id.webview);
            progressWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            progressWebView.loadUrl(url);
        }
    }
}
