package com.example.lexicomaniac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {
    private Button dashboardBtn;
    private Button switchUserBtn;
    private Button newUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        dashboardBtn = (Button) findViewById(R.id.dashboard);
        switchUserBtn = (Button) findViewById(R.id.switchUser);
        newUserBtn = (Button) findViewById(R.id.register);
        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, DashboardActivity.class));
            }
        });
        switchUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
            }
        });
        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, RegisterActivity.class));
            }
        });
    }
}
