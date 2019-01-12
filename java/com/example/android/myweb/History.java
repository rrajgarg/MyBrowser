package com.example.android.myweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter myPlacesAdapter;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    List<UUrl> MyPlacesList;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Expense");
    //List<MyFriend> MyFriendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //     //MyFriendList = new ArrayList<>();
        MyPlacesList = new ArrayList<>();
        recyclerView = findViewById(R.id.IAmRecycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseExpenses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MyPlacesList.clear();
                for(DataSnapshot myPlacesSnapShot: dataSnapshot.getChildren())
                {
                    UUrl myPlaces = myPlacesSnapShot.getValue(UUrl.class);
                    if(myPlaces!=null && myPlaces.getUrl()!=null)
                    {
                        MyPlacesList.add(myPlaces);
                        myPlacesAdapter = new Adapter(History.this, MyPlacesList);
                        recyclerView.setAdapter(myPlacesAdapter);
                    }
                    else
                    {
                        Toast.makeText(History.this,"NULLLLLLLL",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //   MyPlacesList.clear();
            }
        });
    }
}