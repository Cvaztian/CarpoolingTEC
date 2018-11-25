package com.tec.salsas.driverapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tec.salsas.driverapp.ui.rateuser.RateuserFragment;

public class Rateuser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rateuser_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RateuserFragment.newInstance())
                    .commitNow();
        }
    }
}
