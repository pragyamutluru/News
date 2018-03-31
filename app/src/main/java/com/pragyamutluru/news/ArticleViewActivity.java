package com.pragyamutluru.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ArticleViewActivity extends AppCompatActivity {
    WebView webView;
    final static String URL_KEY="com.pragyamutluru.news.articleurl";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        webView=findViewById(R.id.article_webview);
        url=getIntent().getStringExtra(URL_KEY);
        webView.loadUrl(url);
    }
}
