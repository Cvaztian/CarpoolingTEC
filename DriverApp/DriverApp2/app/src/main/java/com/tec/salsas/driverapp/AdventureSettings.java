package com.tec.salsas.driverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdventureSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_settings);
    }

    public void launch(View v){

        Intent intent = new Intent(AdventureSettings.this, AdventureActivity.class);
        startActivity(intent);

    }
}
