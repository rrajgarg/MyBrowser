package com.example.android.myweb.NewTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.android.myweb.AllTabs;
import com.example.android.myweb.Begin;
import com.example.android.myweb.BookMark;
import com.example.android.myweb.BookMarks;
import com.example.android.myweb.History;
import com.example.android.myweb.PrivateTab.PrivateTab1;
import com.example.android.myweb.R;
import com.example.android.myweb.UUrl;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class NewTab1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MaterialSearchView materialSearchView;
    DrawerLayout drawer ;
    String book;
    String load;
    String searchEngine;
    WebView webView;
    String string1;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Expense");
    String[] list;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        list=new String[]{"https://google.com"};
        searchEngine="https://www.google.com/search?q=";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent4 = getIntent();
        string1= intent4.getStringExtra("tab_1");
        searchEngine = intent4.getStringExtra("searchE");
        materialSearchView=findViewById(R.id.mySearch);
        materialSearchView.closeSearch();
        webView = findViewById(R.id.webs);
        webView.setWebViewClient(new NewTab1.myWebViewClient());
        Intent intent = getIntent();
        load = new String("nothing");
        load = intent.getStringExtra("load");
        if(load!=null && !load.equals("nothing"))
        {
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(searchEngine+load);
        }
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String id = databaseExpenses.push().getKey();
                UUrl url =new UUrl(query);
                databaseExpenses.child(id).setValue(url);
                book=query;
                //    Intent intent = new Intent(Begin.this,MainActivity.class);
                //    intent.putExtra("key_1",query);
                //    startActivity(intent);
                //    ProgressBar progressBar = findViewById(R.id.pro);
                //    progressBar.setVisibility(View.VISIBLE);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(searchEngine+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseExpenses.push().getKey();
                BookMark bookMark =new BookMark(book);
                databaseExpenses.child(id).setValue(bookMark);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //   @Override
    //   public boolean onCreateOptionsMenu(Menu menu) {
    //       // Inflate the menu; this adds items to the action bar if it is present.
    //       getMenuInflater().inflate(R.menu.begin, menu);
    //       return true;
    //   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.bookmarks)
        {
            Toast.makeText(getApplicationContext(),"Hiiiiiiiiiiiiiiiiii",Toast.LENGTH_LONG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id)
        {
            case R.id.history:
                Toast.makeText(getApplicationContext(),"History",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(NewTab1.this,History.class);
                startActivity(intent);
                break;
            case R.id.bookmarks:
                Intent intent3 = new Intent(NewTab1.this,BookMarks.class);
                startActivity(intent3);
                Toast.makeText(getApplicationContext(),"Bookmarks",Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_tab:
                Intent intent1 = new Intent(NewTab1.this,NewTab2.class);
                Intent intent4 = getIntent();
                String string1 = intent4.getStringExtra("tab_1");
                intent1.putExtra("tab_1",string1);
                intent1.putExtra("searchE",searchEngine);
                intent1.putExtra("tab_2",book);
                startActivity(intent1);
                break;
            case R.id.new_private_tab:
                Intent intent2 = new Intent(NewTab1.this, PrivateTab1.class);
                startActivity(intent2);
                break;
        }
        /**   if (id == R.id.share) {
         // Handle the camera action
         }
         if(id==R.id.bookmarks)
         {
         Toast.makeText(Begin.this,"Hey Booky",Toast.LENGTH_SHORT).show();
         Fragment fn = new Bookmarks();
         FragmentManager fm = getFragmentManager();
         FragmentTransaction fragmentTransaction = fm.beginTransaction();
         fragmentTransaction.replace(R.id.frag, fn);
         fragmentTransaction.commit();
         }
         if(id==R.id.history)
         {
         Fragment fr = new History();
         FragmentManager fm = getFragmentManager();
         FragmentTransaction fragmentTransaction = fm.beginTransaction();
         fragmentTransaction.replace(R.id.frag, fr);
         fragmentTransaction.commit();
         }**/
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        MenuItem item1 = menu.findItem(R.id.all_tabs);
        materialSearchView.setMenuItem(item);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(NewTab1.this,AllTabs.class);
                intent.putExtra("tab_1",string1);
                intent.putExtra("tab_2",book);
                intent.putExtra("tab_3","nothing");
                startActivity(intent);
                return true;
            }
        });
        return true;
    }
}
