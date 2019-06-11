package com.xtkj.paopaoxiche.view.WebView;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {


    private String url ;

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subdirectory)
    ImageView subdirectory;
    @BindView(R.id.autorenew)
    ImageView autorenew;
    @BindView(R.id.open_browser)
    ImageView openBrowser;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        Toast toast = Toast.makeText(getApplicationContext(),
                "为保证更好的浏览体验，您可点击右上方的HTTP按钮在默认浏览器中打开。", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        Intent intent = getIntent();
        url = intent.getStringExtra(AppConstant.WEB_NTENT_URL);

//        webview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    webview.getSettings()
                            .setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                }
            }
        });

        webview.loadUrl(url);


        webview.loadUrl(url);
    }

    @OnClick({R.id.back_button, R.id.subdirectory, R.id.autorenew, R.id.open_browser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.subdirectory:
                webview.goBack();
                break;
            case R.id.autorenew:
                webview.reload();
                break;
            case R.id.open_browser:
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}
