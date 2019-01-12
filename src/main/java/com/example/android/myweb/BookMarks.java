package com.example.android.myweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookMarks extends AppCompatActivity {
    RecyclerView recyclerView;
    BookAdapter myPlacesAdapter;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    List<BookMark> MyPlacesList;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Expense");
    //List<MyFriend> MyFriendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_marks);
        //     //MyFriendList = new ArrayList<>();
        MyPlacesList = new ArrayList<>();
        recyclerView = findViewById(R.id.IAmDiffRecycler);
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
                    BookMark myPlaces = myPlacesSnapShot.getValue(BookMark.class);
                    if(myPlaces!=null && myPlaces.getBookmark()!=null)
                    {
                        MyPlacesList.add(myPlaces);
                        myPlacesAdapter = new BookAdapter(BookMarks.this, MyPlacesList);
                            recyclerView.setAdapter(myPlacesAdapter);
                    }
                    else
                    {
                        Toast.makeText(BookMarks.this,"NULLLLLLLL",Toast.LENGTH_SHORT).show();
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
