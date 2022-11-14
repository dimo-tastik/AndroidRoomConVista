package com.example.contactosapp.adaptador;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.contactosapp.conexionBDD.EntidadContacto;

public class ContactosListAdapter extends ListAdapter<EntidadContacto, ContactosViewHolder> {


    public ContactosListAdapter(@NonNull DiffUtil.ItemCallback<EntidadContacto> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ContactosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ContactosViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ContactosViewHolder holder, int position) {
        EntidadContacto current = getItem(position);
        holder.bind(current.getNombre());
    }

    public static class ContactoDiff extends DiffUtil.ItemCallback<EntidadContacto> {

        @Override
        public boolean areItemsTheSame(@NonNull EntidadContacto oldItem, @NonNull EntidadContacto newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull EntidadContacto oldItem, @NonNull EntidadContacto newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }
}
