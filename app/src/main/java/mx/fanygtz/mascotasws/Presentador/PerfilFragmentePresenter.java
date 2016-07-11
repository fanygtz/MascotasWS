package mx.fanygtz.mascotasws.Presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import mx.fanygtz.mascotasws.ConfigActivity;
import mx.fanygtz.mascotasws.R;
import mx.fanygtz.mascotasws.db.ConstructorPerfil;
import mx.fanygtz.mascotasws.fragments.IPerfilFragment;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;
import mx.fanygtz.mascotasws.restApi.ConstantesRestApi;
import mx.fanygtz.mascotasws.restApi.IEndPointsApi;
import mx.fanygtz.mascotasws.restApi.Modelo.FichaResponse;
import mx.fanygtz.mascotasws.restApi.Modelo.MascotaResponse;
import mx.fanygtz.mascotasws.restApi.adapter.RestApiAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FGutierrez on 2016/05/28.
 */
public class PerfilFragmentePresenter implements  IPerfilFragmentPresenter {
    private IPerfilFragment iPerfilFragment;
    private Context context;
    private ConstructorPerfil constructorPerfil;
    //private ArrayList<Mascotas> listMascotas;
    private ArrayList<MascotaPerfil> listMascotasPerfil;
    //private Mascotas mascotaActual;
    private String usuario;
    private MascotaPerfil fichaUsuario;

    public PerfilFragmentePresenter(IPerfilFragment iPerfilFragment, Context context) {
        this.iPerfilFragment = iPerfilFragment;
        this.context = context;
        usuario = consultaConfig();
        obtenerID();


    }

    @Override
    public void obtenerMascotasBD() {
    /*    constructorPerfil = new ConstructorPerfil(context);
        listMascotas = constructorPerfil.obtenerDatos(mascotaActual);
        mostrarMascotasRV();
    */
    }

    @Override
    public void mostrarMascotasRV() {
        iPerfilFragment.imagenPerfil(fichaUsuario);
        iPerfilFragment.inicializarAdaptadorPF(iPerfilFragment.crearAdaptador(listMascotasPerfil));
        iPerfilFragment.generarLinearLayoutGrid();
    }

    public String consultaConfig(){
        try{

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(context.openFileInput("ConfigCta.txt")));

            String usuario = fin.readLine();
            fin.close();
            //Log.e("usuario : ",usuario);
            return usuario;
        }catch (Exception e){
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            return usuario = "leo.app";
        }

    }

    @Override
    public void obtenerID() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonIdUser = restApiAdapter.construyendoGsonDeserealizadorFichaUsuario();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonIdUser);
        //Log.e("usuario1 : ",usuario);
        Call<FichaResponse> fichaResponseCall = iEndPointsApi.getIdUser(usuario, ConstantesRestApi.ACCESS_TOKEN);
        fichaResponseCall.enqueue(new Callback<FichaResponse>() {
            @Override
            public void onResponse(Call<FichaResponse> call, Response<FichaResponse> response) {
                FichaResponse fichaResponse = response.body();
                fichaUsuario = fichaResponse.getFichaIdentificacion();
//                Log.e("usuario id : ",fichaUsuario.getId());
                if (fichaUsuario != null){
                    obtenerMediosRecientes(fichaUsuario);
                }
            }

            @Override
            public void onFailure(Call<FichaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso, reportalo con el administrador del sistema", Toast.LENGTH_LONG).show();
                Log.e("Fallo conexion : ", t.toString());

            }
        });

    }

    @Override
    public void obtenerMediosRecientes(MascotaPerfil fichaUsuario) {
        //"3452990908"
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserealizadorMediaRecent();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = iEndPointsApi.getMediaRecent(fichaUsuario.getId());
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                listMascotasPerfil = mascotaResponse.getMascotasPerfil();
                mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso, reportalo con el administrador del sistema", Toast.LENGTH_LONG).show();
                Log.e("Fallo conexion : ", t.toString());
            }
        });
    }
}
