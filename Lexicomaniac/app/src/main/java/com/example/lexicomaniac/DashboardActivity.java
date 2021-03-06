package com.example.lexicomaniac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard: {
                        return true;
                    }
                    case R.id.navigation_notifications: {
                        return true;
                    }
                    case R.id.navigation_search: {
                        startActivity(new Intent(DashboardActivity.this, SearchActivity.class));
                        return true;
                    }
                    case R.id.navigation_settings: {
                        startActivity(new Intent(DashboardActivity.this, SettingsActivity.class));
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
        });
    }
}
