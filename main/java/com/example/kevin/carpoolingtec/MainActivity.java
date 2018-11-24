package com.example.kevin.carpoolingtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }


    /**
     * Abre la pagina de sign up
     * */
    public void openSignUp(View v){

        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);

    }

    /**
     * Abre la aplicacion principal
     * */
    public void openMain(View v){

        Intent intent = new Intent(this, main.class);
        startActivity(intent);

    }
}
