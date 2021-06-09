package com.example.inmobile.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobile.R;
import com.example.inmobile.modelo.Inmueble;

import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;



    public InmuebleAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater inflater) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inmueble_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvDireccion.setText(inmuebles.get(position).getDireccion());
        holder.tvPrecio.setText("$" + inmuebles.get(position).getPrecio());
        String propietarioNombre= inmuebles.get(position).getpropietarioInmueble().getNombre();
        String url= "http://192.168.0.4:45455";
        Log.d("salida", url + inmuebles.get(position).getImagen());
        Log.d("salida", inmuebles.get(position).getpropietarioInmueble().getNombre());

        Glide.with(context)
                .load(url + inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.propiedades_1)
                .error(R.drawable.propiedades_1)
                .into(holder.ivImagenInmueble);

    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPrecio;
        TextView tvDireccion;
        ImageView ivImagenInmueble;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Inmueble inmueble = inmuebles.get(getAdapterPosition());
                    bundle.putInt("id", inmueble.getId());
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment).navigate(R.id.inmuebleFragment, bundle);
                   }
            });
        }
    }
}
