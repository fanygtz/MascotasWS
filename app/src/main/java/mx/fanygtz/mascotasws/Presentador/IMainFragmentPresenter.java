package mx.fanygtz.mascotasws.Presentador;

import mx.fanygtz.mascotasws.poyo.MascotaPerfil;

/**
 * Created by FGutierrez on 2016/05/28.
 */
public interface IMainFragmentPresenter {
    public void obtenerMascotasBD();
    public void mostrarMascotasRV();
    public void obtenerSelfFollows();
    public void obtenerMediosFollows(MascotaPerfil fichaUsuario);
}
