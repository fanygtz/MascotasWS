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
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotasPerfil(deserializarMascotaJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<MascotaPerfil> deserializarMascotaJson (JsonArray mascotaResponseData){
        ArrayList<MascotaPerfil> mascotaPerfils = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size() ; i++){
            JsonObject mascotaResponseRegistroData = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject userJson = mascotaResponseRegistroData.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String full_name = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imagenJson = mascotaResponseRegistroData.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imagenJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlImagen = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotaResponseRegistroData.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likesCounts = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            MascotaPerfil mascotaActual = new MascotaPerfil();
            mascotaActual.setId(id);
            mascotaActual.setFull_name(full_name);
            mascotaActual.setUrlFoto(urlImagen);
            mascotaActual.setLikes(likesCounts);

            mascotaPerfils.add(mascotaActual);
        }
        return mascotaPerfils;
    }
}
