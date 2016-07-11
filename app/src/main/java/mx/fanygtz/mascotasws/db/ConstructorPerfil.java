package mx.fanygtz.mascotasws.db;

import android.content.Context;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.poyo.Mascotas;

/**
 * Created by FGutierrez on 2016/05/29.
 */
public class ConstructorPerfil {
    private static final int LIKE = 1 ;
    private Context context;
    public ConstructorPerfil(Context context) {
        this.context = context;
    }

    public ArrayList<Mascotas> obtenerDatos(Mascotas mascota){

        BaseDatos db = new BaseDatos(context);
        //insertarHistorial(db);
        return db.obtenerHistorialMascota(mascota);
    }
/*
    public void insertarHistorial(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_IDMASCOTA,1);
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_FECHA,"20150101000000");
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_CONTADOR,LIKE);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_IDMASCOTA,1);
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_FECHA,"20150101000000");
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_CONTADOR,LIKE);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_IDMASCOTA,1);
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_FECHA,"20150201000000");
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_CONTADOR,LIKE);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_IDMASCOTA,1);
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_FECHA,"20150201000000");
        contentValues.put(ConstantesBD.TLIKESMASCOTAS_CONTADOR,LIKE);
        db.insertarMascota(contentValues);

    }
    */

    public ArrayList<Mascotas> obtenerHistorialMascota(Mascotas mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerHistorialMascota(mascota);

    }
}
