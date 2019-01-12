package com.example.android.myweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchEngine extends AppCompatActivity {
    TextView google,yahooo,duckduckgo,bing;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Expense");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_engine);
        google=findViewById(R.id.google);
        yahooo=findViewById(R.id.yahoooo);
        duckduckgo=findViewById(R.id.duckduckgo);
        bing=findViewById(R.id.bing);
        final String id = databaseExpenses.push().getKey();
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySearch mySearch = new MySearch("https://www.google.com/search?q=");
                databaseExpenses.child(id).setValue(mySearch);
                Toast.makeText(SearchEngine.this,"Google has been set as default search engine",Toast.LENGTH_LONG).show();
            }
        });
        yahooo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySearch mySearch = new MySearch("https://in.search.yahoo.com/search?p=");
                databaseExpenses.child(id).setValue(mySearch);
                Toast.makeText(SearchEngine.this,"Yahoo has been set as default search engine",Toast.LENGTH_LONG).show();
            }
        });
        duckduckgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySearch mySearch = new MySearch("https://www.duckduckgo.com/search?q=");
                databaseExpenses.child(id).setValue(mySearch);
                Toast.makeText(SearchEngine.this,"DuckDuckGo has been set as default search engine",Toast.LENGTH_LONG).show();
            }
        });
        bing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySearch mySearch = new MySearch("https://www.bing.com/search?q=");
                databaseExpenses.child(id).setValue(mySearch);
                Toast.makeText(SearchEngine.this,"Bing has been set as default search engine",Toast.LENGTH_LONG).show();
            }
        });
    }
}
