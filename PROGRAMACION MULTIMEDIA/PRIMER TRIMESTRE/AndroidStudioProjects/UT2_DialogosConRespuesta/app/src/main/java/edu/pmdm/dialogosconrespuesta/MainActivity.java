package edu.pmdm.dialogosconrespuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogoSexo.OnRespuestaSexo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Click(View v){
        DialogoSexo ds=new DialogoSexo();
        ds.show(getSupportFragmentManager(),"Mi diálogo");
    }

    @Override
    public void onRespuesta(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG ).show();
    }
}