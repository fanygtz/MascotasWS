package mx.fanygtz.mascotasws.Presentador;

import mx.fanygtz.mascotasws.poyo.MascotaPerfil;

/**
 * Created by FGutierrez on 2016/05/28.
 */
public interface IPerfilFragmentPresenter {
    public void obtenerMascotasBD();
    public void mostrarMascotasRV();
  //obtener informacion de webservices
    public void obtenerID();
    public void obtenerMediosRecientes(MascotaPerfil fichaUsuario);

}
