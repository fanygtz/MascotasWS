package mx.fanygtz.mascotasws.restApi;

/**
 * Created by fany_ on 07/07/2016.
 */
public final class ConstantesRestApi {


//https://api.instagram.com/v1/users/search?q=leo.app&access_token=3440197046.f096016.6e701e3ec983405bbd6bfebf9d665a51
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3440197046.f096016.6e701e3ec983405bbd6bfebf9d665a51";
    public static final String AND = "&";
    public static final String INICIO_PARAM = "?";
    public static final String PARAM_ACCESS_TOKEN = "access_token=";
    public static final String PARAM_SEARCH = "search";
    public static final String GET_USER = "users/";
    public static final String URL_GET_SEARCH_USER_01 = GET_USER + PARAM_SEARCH ;

//https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    public static final String GET_MEDIA_RECENT = "{user-id}/media/recent/";
    public static final String URL_GET_MEDIA_RECENT_01 = GET_USER + GET_MEDIA_RECENT + INICIO_PARAM + PARAM_ACCESS_TOKEN + ACCESS_TOKEN ;

    //https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN
    public static final String GET_SELF_FOLLOWS = "self/follows";
    public static final String URL_GET_SELF_FOLLOWS = GET_USER + GET_SELF_FOLLOWS + INICIO_PARAM + PARAM_ACCESS_TOKEN + ACCESS_TOKEN;

}
