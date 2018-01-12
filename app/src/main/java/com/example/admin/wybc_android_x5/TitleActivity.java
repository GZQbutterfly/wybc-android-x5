package com.example.admin.wybc_android_x5;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebView;

/**
 * Created by Admin on 2018/1/9.
 */

public class TitleActivity extends Activity implements OnClickListener {
    private View layoutHeader;
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button moreButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_main);
        //RelativeLayout containerLayout = (RelativeLayout) findViewById(R.id.layout_titlebar);
        //mTitleTextView = (TextView) findViewById(R.id.text_title);
        //mBackwardbButton = (Button) findViewById(R.id.button_backward);
    }

    public void setStatusBarHeight() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int statusBarHeight1 = -1;
                //获取status_bar_height资源的ID
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    //根据资源ID获取响应的尺寸值
                    statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
                    View containerLayout = (View) findViewById(R.id.layout_titlebar);
                    containerLayout.setPadding(0, statusBarHeight1, 0, 0);
                }
                Log.e("WangJ", "状态栏-方法1:" + statusBarHeight1);
            }
        }, 500);
    }

    private View getLayoutHeader() {
        if (null == layoutHeader) {
            layoutHeader = (View) findViewById(R.id.layout_titlebar);
        }
        return layoutHeader;
    }

    private Button getBackButton() {
        if (null == mBackwardbButton) {
            mBackwardbButton = getLayoutHeader().findViewById(R.id.button_backward);
        }
        return mBackwardbButton;
    }


    /**
     * 是否显示返回按钮
     *
     * @param backwardResid 文字
     * @param show          true则显示
     */
    protected void showBackwardView(int backwardResid, boolean show) {
        mBackwardbButton = getBackButton();
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(backwardResid);
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        }
    }


    /**
     * 返回按钮点击后触发
     *
     * @param backwardView
     */
    protected void onBackward(View backwardView) {
        goBack(webView);
    }

    public void goBack(WebView view) {
        if (view.canGoBack()) {
            view.goBack();//返回上一页面
        } else {
            moveTaskToBack(true);
        }
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    private TextView getTitleTextView() {
        if (null == mTitleTextView) {
            mTitleTextView = getLayoutHeader().findViewById(R.id.text_title);
        }
        return mTitleTextView;
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        getTitleTextView().setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }


//    private Button getMoreButton() {
//        if (null == moreButton) {
//            moreButton = getLayoutHeader().findViewById(R.id.button_more);
//        }
//        return moreButton;
//    }

    //protected void onMoreButton(View backwardView) {
    //    getMoreButton();
    //}


    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     * 按钮点击调用的方法
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;
//            case R.id.button_more:
//                onMoreButton(v);
            default:
                break;
        }
    }


}
