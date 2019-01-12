package com.example.android.myweb;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Share extends AppCompatActivity {
    TextView wa,gm;
    PackageManager pm;
    String book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Intent intent = getIntent();
        book = intent.getStringExtra("key_1");
        wa = findViewById(R.id.whattsapp);
        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pm = getPackageManager();
                try
                {
                    Intent waintent = new Intent(Intent.ACTION_SEND);
                    waintent.setType("text/plain");
                    PackageInfo info = pm.getPackageInfo("com.whattsapp",PackageManager.GET_META_DATA);
                    waintent.setPackage("com.whattsapp");
                    waintent.putExtra(Intent.EXTRA_TEXT,book);
                    startActivity(Intent.createChooser(waintent,"ShareWith"));
                }
                catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(Share.this,"WhattsApp not installed",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        gm = findViewById(R.id.gmail);
        gm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pm = getPackageManager();
                String[] TO={""};
                String[] CC={""};
                    Intent waintent = new Intent(Intent.ACTION_SEND);
                    waintent.setData(Uri.parse("mailto:"));
                    waintent.setType("text/plain");
                    waintent.putExtra(Intent.EXTRA_EMAIL, TO);
                    waintent.putExtra(Intent.EXTRA_CC, CC);
                    waintent.putExtra(Intent.EXTRA_SUBJECT,"");
                  //  PackageInfo info = pm.getPackageInfo("com.gmail",PackageManager.GET_META_DATA);
                    waintent.putExtra(Intent.EXTRA_TEXT,book);
                    try
                    {
                        startActivity(Intent.createChooser(waintent,"SendEmail......."));
                        finish();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(Share.this,"Some error Ocurred",Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}
