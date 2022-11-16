package com.example.contactosapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;

public class TelefonoViewHolder extends RecyclerView.ViewHolder {
    final TextView nombreItemView;

    public TelefonoViewHolder(View itemView) {
        super(itemView);
        nombreItemView = itemView.findViewById(R.id.textViewNumeroTelefono);
    }

    public void bind(String text) {
        nombreItemView.setText(text);
    }

    static TelefonoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_telefonos, parent, false);
        return new TelefonoViewHolder(view);
    }
}
