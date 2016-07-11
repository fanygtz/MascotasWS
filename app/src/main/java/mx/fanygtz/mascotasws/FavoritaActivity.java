package mx.fanygtz.mascotasws;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.adapter.RecyclerViewAdapter;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;

public class FavoritaActivity extends AppCompatActivity {
    ArrayList<MascotaPerfil> mascotaList2;// Información.
    RecyclerView rv2;
    RecyclerViewAdapter rAdapter2;
    RecyclerView.LayoutManager lmanager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorita);
        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inicializarMascotas();
        rv2 = (RecyclerView) findViewById(R.id.rv);

        lmanager2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv2.setLayoutManager(lmanager2);
        inicializaAdapter();
    }
    private void inicializaAdapter(){
        rAdapter2 = new RecyclerViewAdapter(mascotaList2,this);
        rv2.setAdapter(rAdapter2);
    }

    private void inicializarMascotas(){
        mascotaList2 = new ArrayList<>();
       /* mascotaList2.add(new Mascotas("Copito", R.drawable.conejito2, 5));
        mascotaList2.add(new Mascotas("Lulu", R.drawable.cachorro2, 3));
        mascotaList2.add(new Mascotas("Anubi",R.drawable.minino2,2));
        mascotaList2.add(new Mascotas("Dumas", R.drawable.baby_elephant_clipart_29, 1));
        mascotaList2.add(new Mascotas("Barry", R.drawable.tigre2, 1));*/
    }
}
