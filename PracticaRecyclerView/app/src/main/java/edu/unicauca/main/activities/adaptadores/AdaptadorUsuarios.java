package edu.unicauca.main.activities.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.unicauca.main.activities.R;
import edu.unicauca.main.activities.modelo.Usuario;

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.ViewHolderUsuarios> {
    ArrayList<Usuario> listaUsuarios;

    public AdaptadorUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public ViewHolderUsuarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_usuarios,null,false);
        return new ViewHolderUsuarios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUsuarios holder, int position) {
        holder.tvNombre.setText(listaUsuarios.get(position).getNombre());
        holder.tvDescripcion.setText(listaUsuarios.get(position).getDescripcion());
        holder.ivFoto.setImageResource(listaUsuarios.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolderUsuarios extends RecyclerView.ViewHolder{
        TextView tvNombre;
        TextView tvDescripcion;
        ImageView ivFoto;

        public ViewHolderUsuarios(@NonNull View itemView) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.idNombre);
            tvDescripcion = (TextView) itemView.findViewById(R.id.idDescripcion);
            ivFoto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
