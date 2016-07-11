package mx.fanygtz.mascotasws.poyo;

/**
 * Created by FGutierrez on 2016/05/09.
 */
public class Mascotas {

    private int id;
    private String name;
    private int idFoto;
    private int contador;

    public Mascotas(String name, int idFoto, int contador){
        this.name = name;
        this.idFoto = idFoto;
        this.contador = contador;
    }

    public Mascotas() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

}
