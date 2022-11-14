package com.example.contactosapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;

public class ContactosViewHolder extends RecyclerView.ViewHolder{
    private final TextView contactoItemView;
    //imagen
    private ContactosViewHolder(View itemView) {
        super(itemView);
        contactoItemView = itemView.findViewById(R.id.textViewNombreContacto);
        //imagen
    }

    public void bind(String text) {
        contactoItemView.setText(text);
    }

    static ContactosViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ContactosViewHolder(view);
    }
}
