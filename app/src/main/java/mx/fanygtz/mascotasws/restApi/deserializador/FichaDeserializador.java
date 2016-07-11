package mx.fanygtz.mascotasws.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.restApi.JsonKeys;
import mx.fanygtz.mascotasws.restApi.Modelo.FichaResponse;
import mx.fanygtz.mascotasws.restApi.Modelo.MascotaResponse;

/**
 * Created by fany_ on 10/07/2016.
 */
public class FichaDeserializador implements JsonDeserializer<FichaResponse> {

    @Override
    public FichaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FichaResponse fichaResponse = gson.fromJson(json,FichaResponse.class);
        JsonArray fichaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        fichaResponse.setFichaIdentificacion(deserializarFichaJson(fichaResponseData));
        return fichaResponse;

    }

    public MascotaPerfil deserializarFichaJson(JsonArray fichaResponseData){
         MascotaPerfil mascotaPerfil = new MascotaPerfil();
        for (int i = 0; i < fichaResponseData.size() ; i++) {
            JsonObject fichaResponseRegistroData = fichaResponseData.get(i).getAsJsonObject();
            String id = fichaResponseRegistroData.get(JsonKeys.USER_ID).getAsString();
            String full_name = fichaResponseRegistroData.get(JsonKeys.USER_FULLNAME).getAsString();
            String foto_perfil = fichaResponseRegistroData.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();
            mascotaPerfil.setId(id);
            mascotaPerfil.setFull_name(full_name);
            mascotaPerfil.setUrlFoto(foto_perfil);
        }
        return mascotaPerfil;
    }

}
