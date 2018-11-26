package com.tec.salsas.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signupLI(View view){
        Intent intent = new Intent(MainActivity.this, signup.class);
        startActivity(intent);
    }

    public void login(View v){

        Intent intent = new Intent(MainActivity.this, AdventureSettings.class);
        startActivity(intent);

    }

}
