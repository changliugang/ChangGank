package com.movie.chang.changmovie.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.movie.chang.changmovie.R;

/**
 * 仿微信的带进度条WebView
 * Created by changliugang on 2017/5/24 10:10
 * mail：changliugang@sina.com
 */
public class ProgressWebView extends WebView {

    private ProgressBar progressbar;
    private boolean isAnimStart;// 动画是否开始,防止多次启动动画

    public ProgressWebView(Context context) {
        super(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                6, 0, 0));
        // ProgressBar的颜色
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        // 拦截url
        setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressbar.setVisibility(View.VISIBLE);
                progressbar.setAlpha(1.0f);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;   // 在当前webview内部打开url
            }
        });
        setWebChromeClient(new WebChromeClient());
        //是否可以缩放
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * progressBar递增动画
     */
    private void startProgressAnimation(int newProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(progressbar, "progress", progressbar.getProgress(), newProgress);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * progressBar消失动画
     */
    private void startDismissAnimation(final int progress) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(progressbar, "alpha", 1.0f, 0.0f);
        anim.setDuration(1500);  // 动画时长
        anim.setInterpolator(new DecelerateInterpolator());     // 减速
        // 关键, 添加动画进度监听器
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = valueAnimator.getAnimatedFraction();      // 0.0f ~ 1.0f
                int offset = 100 - progress;
                progressbar.setProgress((int) (progress + offset * fraction));
            }
        });

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
                progressbar.setProgress(0);
                progressbar.setVisibility(View.GONE);
                isAnimStart = false;
            }
        });
        anim.start();
    }

    private class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress >= 100 && !isAnimStart) {
                // 防止调用多次动画
                isAnimStart = true;
                progressbar.setProgress(newProgress);
                // 开启属性动画让进度条平滑消失
                startDismissAnimation(progressbar.getProgress());
            } else {
                // 开启属性动画让进度条平滑递增
                startProgressAnimation(newProgress);
            }
        }

    }
}
