package mx.fanygtz.mascotasws;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class BioDesarrollador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_desarrollador);
        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tvAutor = (TextView)findViewById(R.id.tvAutor);
        TextView tvUbicacion = (TextView)findViewById(R.id.tvUbicacion);
        TextView tvFecha = (TextView)findViewById(R.id.tvFechabio);
        TextView tvVersion = (TextView)findViewById(R.id.tvVersion);
        TextView tvCurso = (TextView)findViewById(R.id.tvCurso);
    }
}
