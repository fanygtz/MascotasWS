package mx.fanygtz.mascotasws.fragments;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.adapter.RecyclerViewAdapter;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;

/**
 * Created by FGutierrez on 2016/05/26.
 */
public interface IMainFragment {
    public void generarLinearLayoutVertical();
    public RecyclerViewAdapter crearAdaptador ( ArrayList<MascotaPerfil> mascotaList);
    public void inicializarAdaptadorMF (RecyclerViewAdapter rAdapter);
}
