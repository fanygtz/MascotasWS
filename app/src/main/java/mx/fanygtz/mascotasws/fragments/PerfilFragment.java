package mx.fanygtz.mascotasws.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.Presentador.IPerfilFragmentPresenter;
import mx.fanygtz.mascotasws.Presentador.PerfilFragmentePresenter;
import mx.fanygtz.mascotasws.R;
import mx.fanygtz.mascotasws.adapter.PerfilAdapter;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragment{
    ArrayList<MascotaPerfil> mascotaList;// Información.
    RecyclerView rv;
    RecyclerView.LayoutManager lmanager;
    private IPerfilFragmentPresenter presenter;
    private CircularImageView circularImageView;
    private TextView tvNombre;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        circularImageView = (CircularImageView)v.findViewById(R.id.ivFotoCircular);
        tvNombre = (TextView)v.findViewById(R.id.tvNombre);
        rv = (RecyclerView) v.findViewById(R.id.rvPerfil);
        presenter = new PerfilFragmentePresenter(this,getContext());
        //presenter = new PerfilFragmentePresenter(this,getContext(), MiAplicacion.mascotaActual.getMascota());
        return v;
    }

    @Override
    public void generarLinearLayoutGrid() {
        lmanager = new GridLayoutManager(getActivity(),2);
        rv.setLayoutManager(lmanager);
    }

    @Override
    public PerfilAdapter crearAdaptador(ArrayList<MascotaPerfil> mascotaList) {
        PerfilAdapter rAdapter = new PerfilAdapter(mascotaList,getActivity());
        return rAdapter;
    }

    @Override
    public void inicializarAdaptadorPF(PerfilAdapter rAdapter) {
        rv.setAdapter(rAdapter);
    }

    @Override
    public void imagenPerfil(MascotaPerfil mascotaPerfil) {
        Picasso.with(getActivity())
                .load(mascotaPerfil.getUrlFoto())
                .placeholder(R.drawable.tigre2)
                .into(circularImageView);

        tvNombre.setText(mascotaPerfil.getFull_name());
    }


}
