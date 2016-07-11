package mx.fanygtz.mascotasws.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.poyo.Mascotas;

/**
 * Created by FGutierrez on 2016/05/29.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;
    public BaseDatos(Context context) {
        super(context, ConstantesBD.BD_NAME, null, ConstantesBD.BD_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          String queryTablaMascotas ="CREATE TABLE " + ConstantesBD.TMASCOTAS + " (" +
                                      ConstantesBD.TMASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                      ConstantesBD.TMASCOTAS_NAME + " TEXT, " +
                                      ConstantesBD.TMASCOTAS_IDFOTO + " INTEGER" +
                                      ")";
          String queryTablaLikesMascotas = "CREATE TABLE " + ConstantesBD.TLIKESMASCOTAS + " (" +
                                          ConstantesBD.TLIKESMASCOTAS_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                          ConstantesBD.TLIKESMASCOTAS_IDMASCOTA + " INTEGER, " +
                                          ConstantesBD.TLIKESMASCOTAS_FECHA  + " DATE, " +
                                          ConstantesBD.TLIKESMASCOTAS_CONTADOR  + " INTEGER, " +
                                          "FOREIGN KEY (" + ConstantesBD.TLIKESMASCOTAS_IDMASCOTA + ")" +
                                          "REFERENCES " + ConstantesBD.TMASCOTAS + "(" + ConstantesBD.TMASCOTAS_ID + ")" +
                                          ")";
         db.execSQL(queryTablaMascotas);
         db.execSQL(queryTablaLikesMascotas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TLIKESMASCOTAS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TMASCOTAS);
        onCreate(db);
    }

    public ArrayList<Mascotas> obtenerTodasLasMascotas(){
        ArrayList<Mascotas> listMascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBD.TMASCOTAS ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()){
            Mascotas mascota = new Mascotas();
            mascota.setId(registros.getInt(0));
            mascota.setName(registros.getString(1));
            mascota.setIdFoto(registros.getInt(2));

            String queryLike = "SELECT COUNT (" + ConstantesBD.TLIKESMASCOTAS_CONTADOR + ")" +
                    " FROM " + ConstantesBD.TLIKESMASCOTAS +
                    " WHERE " + ConstantesBD.TLIKESMASCOTAS_IDMASCOTA + " = " + mascota.getId() ;
            Cursor registroLikes = db.rawQuery(queryLike, null);
            if (registroLikes.moveToNext()){
                mascota.setContador(registroLikes.getInt(0));
            }else{
                mascota.setContador(0);
            }
            listMascotas.add(mascota);
        }
        db.close();
        return listMascotas;

    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TMASCOTAS,null, contentValues);
        db.close();

    }

    public void insertarLikesMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TLIKESMASCOTAS,null, contentValues);
        db.close();

    }

    public int obtenerContadorMascota(Mascotas mascota){
        int likes = 0;
        String query = "SELECT COUNT (" + ConstantesBD.TLIKESMASCOTAS_CONTADOR + ")" +
                       " FROM " + ConstantesBD.TLIKESMASCOTAS +
                       " WHERE " + ConstantesBD.TLIKESMASCOTAS_IDMASCOTA + " = " + mascota.getId() ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        if (registros.moveToNext()){
           likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }

    public void insertarHistorialMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TLIKESMASCOTAS, null, contentValues);
        db.close();
    }

    public ArrayList<Mascotas> obtenerHistorialMascota(Mascotas mascota){
        ArrayList<Mascotas> listMascotas = new ArrayList<>();
        Mascotas mascotaactual;
        String query = "SELECT COUNT (" + ConstantesBD.TLIKESMASCOTAS_CONTADOR + "), " + ConstantesBD.TLIKESMASCOTAS_IDMASCOTA +
                " FROM " + ConstantesBD.TLIKESMASCOTAS +
                " WHERE " + ConstantesBD.TLIKESMASCOTAS_IDMASCOTA + " = " + mascota.getId() +
                " GROUP BY " + ConstantesBD.TLIKESMASCOTAS_FECHA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()){
            mascotaactual = new Mascotas();
            mascotaactual.setId(mascota.getId());
            mascotaactual.setName(mascota.getName());
            mascotaactual.setIdFoto(mascota.getIdFoto());
            mascotaactual.setContador(registros.getInt(0));
            listMascotas.add(mascotaactual);
        }
        if (registros.moveToNext()) {
        }else{
            mascotaactual = new Mascotas();
            mascotaactual.setId(mascota.getId());
            mascotaactual.setName(mascota.getName());
            mascotaactual.setIdFoto(mascota.getIdFoto());
            mascotaactual.setContador(0);
            listMascotas.add(mascotaactual);
        }
        db.close();

        return listMascotas;

    }


}
