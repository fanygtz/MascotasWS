package mx.fanygtz.mascotasws.restApi.Modelo;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.poyo.MascotaPerfil;

/**
 * Created by fany_ on 07/07/2016.
 */
public class MascotaResponse {

    ArrayList<MascotaPerfil> MascotasPerfil;

    public ArrayList<MascotaPerfil> getMascotasPerfil() {
        return MascotasPerfil;
    }

    public void setMascotasPerfil(ArrayList<MascotaPerfil> mascotasPerfil) {
        MascotasPerfil = mascotasPerfil;
    }

}
