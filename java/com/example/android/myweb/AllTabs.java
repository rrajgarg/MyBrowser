package com.example.android.myweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myweb.NewTab.NewTab1;
import com.example.android.myweb.NewTab.NewTab2;
import com.example.android.myweb.PrivateTab.PrivateTab1;
import com.example.android.myweb.PrivateTab.PrivateTab2;

public class AllTabs extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5;
    WebView webView1,webView2,webView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tabs);
        final Intent intent = getIntent();
        textView1=findViewById(R.id.text_main);
        textView2=findViewById(R.id.text_new1);
        textView3=findViewById(R.id.text_new2);
        webView1=findViewById(R.id.main_tab);
        webView2=findViewById(R.id.new_tab1);
        webView3=findViewById(R.id.new_tab2);
        webView1.setWebViewClient(new AllTabs.myWebViewClient());
        webView2.setWebViewClient(new AllTabs.myWebViewClient());
        webView3.setWebViewClient(new AllTabs.myWebViewClient());
        webView1.getSettings().setBuiltInZoomControls(true);
        webView1.getSettings().setLoadsImagesAutomatically(true);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl(intent.getStringExtra("tab_1"));
        final String string = intent.getStringExtra("tab_2");
        if(!string.equals("nothing"))
        {
            webView2.getSettings().setBuiltInZoomControls(true);
            webView2.getSettings().setLoadsImagesAutomatically(true);
            webView2.getSettings().setJavaScriptEnabled(true);
            webView2.loadUrl(intent.getStringExtra("tab_2"));
        }
        final String string1 = intent.getStringExtra("tab_3");
        if(!string1.equals("nothing"))
        {
            webView3.getSettings().setBuiltInZoomControls(true);
            webView3.getSettings().setLoadsImagesAutomatically(true);
            webView3.getSettings().setJavaScriptEnabled(true);
            webView3.loadUrl(intent.getStringExtra("tab_3"));
        }
        textView4=findViewById(R.id.private_tab1);
        textView5=findViewById(R.id.private_tab2);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllTabs.this, PrivateTab1.class);
                startActivity(intent);
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllTabs.this,PrivateTab2.class);
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllTabs.this,string1,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AllTabs.this,NewTab2.class);
                intent.putExtra("load",string1);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllTabs.this,string,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AllTabs.this,NewTab1.class);
                intent.putExtra("load",string);
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllTabs.this,intent.getStringExtra("tab_1"),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AllTabs.this,Begin.class);
                String s = intent.getStringExtra("tab_1");
                intent.putExtra("load",s);
                startActivity(intent);
            }
        });
    }
    private class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
