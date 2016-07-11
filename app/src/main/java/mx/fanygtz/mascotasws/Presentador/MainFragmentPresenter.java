package mx.fanygtz.mascotasws.Presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.db.ConstructorMascotas;
import mx.fanygtz.mascotasws.fragments.IMainFragment;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;
import mx.fanygtz.mascotasws.restApi.IEndPointsApi;
import mx.fanygtz.mascotasws.restApi.Modelo.MascotaResponse;
import mx.fanygtz.mascotasws.restApi.adapter.RestApiAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FGutierrez on 2016/05/28.
 */
public class MainFragmentPresenter implements IMainFragmentPresenter {
    private IMainFragment imainfragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    //private ArrayList<Mascotas> listMascotas;
    private final ArrayList<MascotaPerfil> listMascotas = new ArrayList<>() ;
    private ArrayList<MascotaPerfil> followsMascotas;
    private ArrayList<MascotaPerfil> MascotasFollow;
    int contador;
    int registro;
    public MainFragmentPresenter(IMainFragment imainFragment, Context context) {
        this.context = context;
        this.imainfragment = imainFragment;
        obtenerSelfFollows();

        //obtenerMascotasBD();
    }

    @Override
    public void obtenerMascotasBD() {
   /*     constructorMascotas = new ConstructorMascotas(context);
        listMascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
*/
    }

    @Override
    public void mostrarMascotasRV() {
       imainfragment.inicializarAdaptadorMF(imainfragment.crearAdaptador(listMascotas));
       imainfragment.generarLinearLayoutVertical();

    }

    @Override
    public void obtenerSelfFollows() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSelfFollows = restApiAdapter.construyeGsonDeserealizadorSelfFollows();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonSelfFollows);
        Call<MascotaResponse> mascotaResponseCall = iEndPointsApi.getSelfFollows();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                followsMascotas = mascotaResponse.getMascotasPerfil();
                registro=followsMascotas.size();
                for (int i = 0; i <followsMascotas.size() ; i++) {
                    contador = i;
                    obtenerMediosFollows(followsMascotas.get(i));
                }


            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso, reportalo con el administrador del sistema", Toast.LENGTH_LONG).show();
                Log.e("Fallo conexion : ", t.toString());
            }
        });
    }

    @Override
    public void obtenerMediosFollows(MascotaPerfil fichaUsuario) {
        //final ArrayList<MascotaPerfil> listMascotas = new ArrayList<>();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaFollows = restApiAdapter.construyeGsonDeserealizadorMediaRecent();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaFollows);
        Call<MascotaResponse> mascotaResponseCall = iEndPointsApi.getMediaRecent(fichaUsuario.getId());
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                MascotasFollow = mascotaResponse.getMascotasPerfil();
                listMascotas.addAll(MascotasFollow);
                if (contador == registro - 1){
                    mostrarMascotasRV();
                }
                Log.e("Contador", String.valueOf(contador));
                Log.e("Registros", String.valueOf(registro));
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso, reportalo con el administrador del sistema", Toast.LENGTH_LONG).show();
                Log.e("Fallo conexion : ", t.toString());
            }
        });

    }
}
