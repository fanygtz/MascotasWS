package mx.fanygtz.mascotasws.poyo;

/**
 * Created by FGutierrez on 2016/05/09.
 */
public class MascotaPerfil {

    private String id;
    private String full_name;
    private String urlFoto;
    private int likes;

    public MascotaPerfil(String id, String full_name, String urlFoto, int likes){
        this.id = id;
        this.full_name = full_name;
        this.urlFoto = urlFoto;
        this.likes = likes;
    }

    public MascotaPerfil() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
