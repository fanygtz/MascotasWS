package mx.fanygtz.mascotasws.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.fanygtz.mascotasws.restApi.ConstantesRestApi;
import mx.fanygtz.mascotasws.restApi.IEndPointsApi;
import mx.fanygtz.mascotasws.restApi.Modelo.FichaResponse;
import mx.fanygtz.mascotasws.restApi.Modelo.MascotaResponse;
import mx.fanygtz.mascotasws.restApi.deserializador.FichaDeserializador;
import mx.fanygtz.mascotasws.restApi.deserializador.MascotaDeserializador;
import mx.fanygtz.mascotasws.restApi.deserializador.SelfFollowsDeserializador;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fany_ on 09/07/2016.
 */
public class RestApiAdapter {
    public IEndPointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IEndPointsApi.class);
    }

    public Gson construyeGsonDeserealizadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class,new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyendoGsonDeserealizadorFichaUsuario(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FichaResponse.class, new FichaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserealizadorSelfFollows(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class,new SelfFollowsDeserializador());
        return gsonBuilder.create();
    }

}
