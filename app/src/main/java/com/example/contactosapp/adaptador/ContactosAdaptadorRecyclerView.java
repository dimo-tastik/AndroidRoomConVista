package com.example.contactosapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;
import com.example.contactosapp.conexionBDD.EntidadContacto;

import java.util.List;

public class ContactosAdaptadorRecyclerView extends RecyclerView.Adapter<ContactosViewHolder> {

    private List<EntidadContacto> listaContactos;

    public ContactosAdaptadorRecyclerView (List<EntidadContacto> lista){
        this.listaContactos = lista;
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new ContactosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {
        holder.contactoItemView.setText(listaContactos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }
}
