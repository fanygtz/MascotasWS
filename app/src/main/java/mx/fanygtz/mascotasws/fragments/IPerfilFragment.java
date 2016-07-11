package mx.fanygtz.mascotasws.fragments;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.adapter.PerfilAdapter;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;

/**
 * Created by FGutierrez on 2016/05/28.
 */
public interface IPerfilFragment {
    public void generarLinearLayoutGrid();
    public PerfilAdapter crearAdaptador ( ArrayList<MascotaPerfil> mascotaList);
    public void inicializarAdaptadorPF (PerfilAdapter rAdapter);
    public void imagenPerfil(MascotaPerfil mascotaPerfil);

}
