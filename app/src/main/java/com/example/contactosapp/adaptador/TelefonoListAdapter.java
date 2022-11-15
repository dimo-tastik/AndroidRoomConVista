package com.example.contactosapp.adaptador;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.contactosapp.conexionBDD.EntidadTelefono;

public class TelefonoListAdapter extends ListAdapter<EntidadTelefono, TelefonoViewHolder> {


    public TelefonoListAdapter(@NonNull DiffUtil.ItemCallback<EntidadTelefono> diffCallback) {
        super(diffCallback);
    }

    @Override
    public TelefonoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TelefonoViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TelefonoViewHolder holder, int position) {
        EntidadTelefono current = getItem(position);
        holder.bind(current.getTelefono().getNumeroTelefono());
    }

    public static class TelefonoDiff extends DiffUtil.ItemCallback<EntidadTelefono> {

        @Override
        public boolean areItemsTheSame(@NonNull EntidadTelefono oldItem, @NonNull EntidadTelefono newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull EntidadTelefono oldItem, @NonNull EntidadTelefono newItem) {
            return oldItem.getTelefono().getNumeroTelefono().equals(newItem.getTelefono().getNumeroTelefono());
        }
    }
}
