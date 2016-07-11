package mx.fanygtz.mascotasws.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.restApi.JsonKeys;
import mx.fanygtz.mascotasws.restApi.Modelo.MascotaResponse;

/**
 * Created by fany_ on 10/07/2016.
 */
public class SelfFollowsDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotasPerfil(deserializarFollowsJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<MascotaPerfil> deserializarFollowsJson (JsonArray mascotaResponseData){
        ArrayList<MascotaPerfil> mascotaPerfils = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size() ; i++){
            JsonObject mascotaResponseRegistroData = mascotaResponseData.get(i).getAsJsonObject();
            String id = mascotaResponseRegistroData.get(JsonKeys.USER_ID).getAsString();
            String full_name = mascotaResponseRegistroData.get(JsonKeys.USER_FULLNAME).getAsString();
            String foto_perfil = mascotaResponseRegistroData.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

            MascotaPerfil mascotaActual = new MascotaPerfil();
            mascotaActual.setId(id);
            mascotaActual.setFull_name(full_name);
            mascotaActual.setUrlFoto(foto_perfil);
            mascotaActual.setLikes(0);

            mascotaPerfils.add(mascotaActual);
        }
        return mascotaPerfils;
    }
}
