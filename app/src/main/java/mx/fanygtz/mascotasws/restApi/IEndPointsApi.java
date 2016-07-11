package mx.fanygtz.mascotasws.restApi;

import mx.fanygtz.mascotasws.restApi.Modelo.FichaResponse;
import mx.fanygtz.mascotasws.restApi.Modelo.MascotaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fany_ on 07/07/2016.
 */
public interface IEndPointsApi {

    @GET(ConstantesRestApi.URL_GET_MEDIA_RECENT_01)
    Call<MascotaResponse> getMediaRecent(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_SEARCH_USER_01)
    Call<FichaResponse> getIdUser(@Query("q") String jack, @Query("access_token") String access_token);

    @GET(ConstantesRestApi.URL_GET_SELF_FOLLOWS)
    Call<MascotaResponse> getSelfFollows();
}
