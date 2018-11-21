package com.tec.salsas.carpoolingtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton)findViewById(R.id.logFB);
        loginButton.setReadPermissions(Arrays.asList("email"));
        // Callback registration
        callbackManager = CallbackManager.Factory.create();


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        System.out.println("yay");
                        System.out.println(loginResult.getAccessToken().getUserId());



                    }

                    @Override
                    public void onCancel() {
                        // App code
                        System.out.println("nay");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        System.out.println(exception);
                    }
                });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
