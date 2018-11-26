package com.tec.salsas.driverapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import org.json.JSONObject;

import java.security.MessageDigest;

public class signup extends AppCompatActivity {

    public static final String host = "api.linkedin.com";
    public static final String url = "https://"+host+"/v1/people/~:"+"(email-address,formatted-name,phone-numbers,picture-urls::(original))";

    private TextView username, useremail, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        computePakageHash();

        username=(TextView)findViewById(R.id.name);
        useremail=(TextView)findViewById(R.id.email);
        token=(TextView)findViewById(R.id.printToken);

        Bundle bundle = getIntent().getExtras();
        String getoken = bundle.getString("value");

        token.setText(getoken);
        linkedinHelperApi();
    }

    private void computePakageHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.tec.salsas.driverapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            System.out.println(e);
            Log.e("TAG",e.getMessage());
        }
    }

    public void linkedinHelperApi(){
        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        apiHelper.getRequest(signup.this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                try{
                    finalResult(apiResponse.getResponseDataAsJson());
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }

            @Override
            public void onApiError(LIApiError LIApiError) {
                System.out.println("hahdsa");
            }
        });
    }

    public void finalResult(JSONObject jsonObject){
        try{
            username.setText("Full Name:  "+jsonObject.get("formattedName").toString());
            useremail.setText("Email Address:  "+jsonObject.get("emailAddress").toString());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
