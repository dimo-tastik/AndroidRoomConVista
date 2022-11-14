package com.example.contactosapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;
import com.example.contactosapp.adaptador.ContactosListAdapter;
import com.example.contactosapp.vistaModelo.ContactosTelefonoViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContactos;
    ContactosTelefonoViewModel contactosViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = findViewById(R.id.listaContactos);
        final ContactosListAdapter adapter = new ContactosListAdapter(new ContactosListAdapter.ContactoDiff());
        listaContactos.setAdapter(adapter);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        contactosViewModel = new ViewModelProvider(this).get(ContactosTelefonoViewModel.class);
        contactosViewModel.getAllContactos().observe(this, animals -> {
            adapter.submitList(animals);
        });

    }
}