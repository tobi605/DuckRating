package com.diamentowygimbus.duckrating;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private int container;
    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        container = R.id.main_container;
        fragmentManager = getSupportFragmentManager();
        toolbar = findViewById(R.id.toolbar);

        initFragmentTransaction();
        setupToolbar();
        addNavigationListeners();


    }

    private void initFragmentTransaction() {
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(container, MainFragment.newInstance(getApplicationContext()));
        transaction.commit();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void addNavigationListeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                //TODO switch fragments
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
