package com.example.android.myweb.PrivateTab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.myweb.R;
import com.squareup.picasso.Picasso;

public class General extends AppCompatActivity {
    TextView textView;
    EditText editText;
    ImageView imageView;
    Button button;
    LinearLayout linearLayout;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        textView = findViewById(R.id.homepage);
        editText = findViewById(R.id.homeview);
        url=editText.getText().toString();
        imageView = findViewById(R.id.home9);
        linearLayout = findViewById(R.id.home);
        button = findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromUrl(url);
            }
        });
    }
    private void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView,new com.squareup.picasso.Callback(){

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}
