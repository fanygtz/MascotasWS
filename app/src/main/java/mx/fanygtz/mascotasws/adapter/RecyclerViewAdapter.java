package mx.fanygtz.mascotasws.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.db.ConstructorMascotas;
import mx.fanygtz.mascotasws.poyo.MascotaPerfil;
import mx.fanygtz.mascotasws.poyo.Mascotas;
import mx.fanygtz.mascotasws.R;

/**
 * Created by FGutierrez on 2016/05/09.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MascotaViewHolder> {
    private ArrayList<MascotaPerfil> Mascota;
    private Activity activity;
    public RecyclerViewAdapter(ArrayList<MascotaPerfil> Mascota, Activity activity){
        this.Mascota = Mascota;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int position) {
        final MascotaPerfil registro = Mascota.get(position);

        holder.nombre.setText(registro.getFull_name());
        holder.rating.setText(String.valueOf(registro.getLikes()));
        //holder.idFoto.setImageResource(registro.getIdFoto());
        Picasso.with(activity)
                .load(registro.getUrlFoto())
                .placeholder(R.drawable.dog_bone)
                .into(holder.idFoto);
        holder.btnContador.setVisibility(View.INVISIBLE);
        /*holder.btnContador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(registro);
                Toast.makeText(activity, "Diste Like a : " + registro.getName(), Toast.LENGTH_SHORT).show();
                holder.rating.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(registro)));

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return Mascota.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView rating;
        ImageView idFoto;
        ImageButton btnContador;
        MascotaViewHolder(View itemView){
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.tvNombre);
            idFoto = (ImageView)itemView.findViewById(R.id.ivFoto);
            rating = (TextView)itemView.findViewById(R.id.tvRating);
            btnContador=(ImageButton)itemView.findViewById(R.id.btnContador);


        }
    }


}
