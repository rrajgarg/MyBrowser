package com.example.android.myweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class Connect extends AppCompatActivity {
    MaterialSearchView materialSearchView;
    RecyclerView recyclerView;
    FriendAdapter myPlacesAdapter;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    List<Friend> MyPlacesList;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Expense");
    String[] list=new String[]{"Raj",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        materialSearchView=findViewById(R.id.mySearch);
        materialSearchView.setSuggestions(list);
        materialSearchView.closeSearch();
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String id = databaseExpenses.push().getKey();
                Friend friend = new Friend(query);
                databaseExpenses.child(id).setValue(friend);
                //    Intent intent = new Intent(Begin.this,MainActivity.class);
                //    intent.putExtra("key_1",query);
                //    startActivity(intent);
                //    ProgressBar progressBar = findViewById(R.id.pro);
                //    progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        MyPlacesList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle);
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
                    Friend myPlaces = myPlacesSnapShot.getValue(Friend.class);
                    if(myPlaces!=null && myPlaces.getUsername()!=null)
                    {
                        MyPlacesList.add(myPlaces);
                        myPlacesAdapter = new FriendAdapter(Connect.this, MyPlacesList);
                        recyclerView.setAdapter(myPlacesAdapter);
                    }
                    else
                    {
                        Toast.makeText(Connect.this,"NULLLLLLLL",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //   MyPlacesList.clear();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        materialSearchView.setMenuItem(item);
        return true;
    }
}
