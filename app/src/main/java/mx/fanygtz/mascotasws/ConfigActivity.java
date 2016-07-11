package mx.fanygtz.mascotasws;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class ConfigActivity extends AppCompatActivity {
    Button btnGuardar;
    TextInputLayout lyUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        lyUsuario = (TextInputLayout) findViewById(R.id.layoutUsuario);
    }
    public void generarArchivo(View view){
        try{
            String usuario = lyUsuario.getEditText().getText().toString();
            FileOutputStream outputStream = null;
            outputStream = openFileOutput("ConfigCta.txt", Context.MODE_PRIVATE);
            outputStream.write(usuario.getBytes());
            outputStream.close();
            Toast.makeText(ConfigActivity.this, "El contacto se ha guardado...",Toast.LENGTH_SHORT).show();
            lyUsuario.getEditText().setText("");
        }catch (Exception e){
            e.printStackTrace();
            Log.d("MENSAJE", "generarArchivo: " + e);
            Toast.makeText(ConfigActivity.this, "Error en el archivo...", Toast.LENGTH_SHORT).show();
        }
    }

}
