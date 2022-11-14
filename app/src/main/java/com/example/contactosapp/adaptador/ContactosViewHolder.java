package com.example.contactosapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;

public class ContactosViewHolder extends RecyclerView.ViewHolder{
    final TextView contactoItemView;
    final ImageView imageView;

    public ContactosViewHolder(View itemView) {
        super(itemView);
        contactoItemView = itemView.findViewById(R.id.textViewNombreContacto);
        imageView = itemView.findViewById(R.id.imageViewContacto);

    }

    public void bind(String text) {
        contactoItemView.setText(text);
        imageView.setImageResource(R.drawable.contacto);
    }

    static ContactosViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ContactosViewHolder(view);
    }
}
