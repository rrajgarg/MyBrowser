package com.example.android.myweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity {
    EditText editText;
    Button button;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Expense");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = findViewById(R.id.chats);
        button = findViewById(R.id.sends);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseExpenses.push().getKey();
                MyChat chat =new MyChat(editText.getText().toString(),"aaa","bbb");
                databaseExpenses.child(id).setValue(chat);
            }
        });
    }
}
