package mx.fanygtz.mascotasws.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mx.fanygtz.mascotasws.R;
import mx.fanygtz.mascotasws.poyo.Mascotas;

/**
 * Created by FGutierrez on 2016/05/28.
 */
public class ConstructorMascotas {
    private static final int LIKE = 1 ;
    private Context context;
    public ConstructorMascotas(Context context) {
        this.context = context;
    }
    public ArrayList<Mascotas> obtenerDatos(){
        ArrayList<Mascotas> listMascotas;
        BaseDatos db = new BaseDatos(context);
        listMascotas = db.obtenerTodasLasMascotas();
        if (listMascotas.isEmpty()) {
            insertarMascota(db);
            listMascotas = db.obtenerTodasLasMascotas();
        }
        return listMascotas;
    }

    public void insertarMascota(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TMASCOTAS_NAME,"Lulu");
        contentValues.put(ConstantesBD.TMASCOTAS_IDFOTO, R.drawable.cachorro2);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TMASCOTAS_NAME,"Copito");
        contentValues.put(ConstantesBD.TMASCOTAS_IDFOTO, R.drawable.conejito2);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TMASCOTAS_NAME,"Anubi");
        contentValues.put(ConstantesBD.TMASCOTAS_IDFOTO, R.drawable.minino2);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TMASCOTAS_NAME,"Dumas");
        contentValues.put(ConstantesBD.TMASCOTAS_IDFOTO, R.drawable.baby_elephant_clipart_29);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TMASCOTAS_NAME,"Barry");
        contentValues.put(ConstantesBD.TMASCOTAS_IDFOTO, R.drawable.tigre2);
        db.insertarMascota(contentValues);

    }
    public void darLikeMascota(Mascotas mascota){
        BaseDatos db = new BaseDatos(context);

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        Date myDate = new Date();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_IDMASCOTA, mascota.getId());
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_CONTADOR, LIKE);
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_FECHA, timeStampFormat.format(myDate));
        db.insertarLikesMascota(contentValues);
        Log.d("Fecha : ", timeStampFormat.format(myDate));
    }

    public int obtenerLikesMascota(Mascotas mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerContadorMascota(mascota);

    }

}
