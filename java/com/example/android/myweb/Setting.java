package com.example.android.myweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.myweb.PrivateTab.General;

public class Setting extends AppCompatActivity {
    TextView general,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        general = findViewById(R.id.general);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, General.class);
                startActivity(intent);
            }
        });
        search = findViewById(R.id.search_engine);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, SearchEngine.class);
                startActivity(intent);
            }
        });
    }
}
