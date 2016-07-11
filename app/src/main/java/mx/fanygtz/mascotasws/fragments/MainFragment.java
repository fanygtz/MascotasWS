package mx.fanygtz.mascotasws.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.Presentador.IMainFragmentPresenter;
import mx.fanygtz.mascotasws.Presentador.MainFragmentPresenter;
import mx.fanygtz.mascotasws.R;
import mx.fanygtz.mascotasws.adapter.RecyclerViewAdapter;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;


public class MainFragment extends Fragment implements IMainFragment {

    RecyclerView rv;
    RecyclerView.LayoutManager lmanager;
    private IMainFragmentPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        //inicializarMascotas();
        rv = (RecyclerView) v.findViewById(R.id.rv);
        presenter = new MainFragmentPresenter(this,getContext());
        return v;
    }




    @Override
    public void generarLinearLayoutVertical() {
        lmanager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(lmanager);
    }

    @Override
    public RecyclerViewAdapter crearAdaptador(ArrayList<MascotaPerfil> mascotaList) {
        RecyclerViewAdapter rAdapter = new RecyclerViewAdapter(mascotaList,getActivity());
        return rAdapter;
    }

    @Override
    public void inicializarAdaptadorMF(RecyclerViewAdapter rAdapter) {
        rv.setAdapter(rAdapter);
    }
}
