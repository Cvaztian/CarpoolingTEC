package com.tec.salsas.carpoolingtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class signUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    /**
     * Metodo que se ejecuta al oprimir el boton CameraButton y que inicializa el lector de codigo de barras
     * @param v pantalla donde se mostrara
     */
    public void CameraButton(View v){
            IntentIntegrator intent = new IntentIntegrator(this);
            intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
            intent.setPrompt("Coloque su carnet dentro del rectángulo");
            intent.setCameraId(0);
            intent.setBeepEnabled(true);
            intent.setBarcodeImageEnabled(false);
            intent.initiateScan();
        }

    /**
     * Muestra el resultado de la lectura del codigo de barras
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Cancelaste el escaneo", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
