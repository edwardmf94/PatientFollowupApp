package com.pe.patient.followup.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.pe.patient.followup.R;

public class MainActivity extends AppCompatActivity implements MainActivityView, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //toolbar = findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        //toolbar.setTitle("Dashboard");
        navigationView.bringToFront();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        Log.i("onBackPressed", "prueba inicio*************");
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_item_edit_patient:
                Intent intent = new Intent(getApplicationContext(), PatientActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_new_file:
                Intent intent2 = new Intent(getApplicationContext(), PatientFileActivity.class);
                startActivity(intent2);
                break;
            case R.id.menu_item_order_status:
                Intent intent3 = new Intent(getApplicationContext(), DrugstoreOrdersActivity.class);
                startActivity(intent3);
                break;
            case R.id.menu_item_recipients:
                Intent intent4 = new Intent(getApplicationContext(), RecipientsActivity.class);
                startActivity(intent4);
                break;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
