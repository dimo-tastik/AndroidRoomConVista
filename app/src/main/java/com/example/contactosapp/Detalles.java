package com.example.contactosapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;
import com.example.contactosapp.adaptador.TelefonoListAdapter;
import com.example.contactosapp.vistaModelo.ContactosTelefonoViewModel;

public class Detalles extends AppCompatActivity {

    RecyclerView listaTelefonos;
    ContactosTelefonoViewModel telefonoViewModel;
    TelefonoListAdapter adapter;

    private EditText editTextNombre;
    private EditText editTextDireccion;
    private EditText editTextEmail;
    private ImageButton botonGuardar;
    private ImageButton botonCerrar;

    int posicion;
    String nombre;
    String direccion;
    String email;

    Intent intentRespuesta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        listaTelefonos = findViewById(R.id.recyclerViewTelefonos);
        adapter = new TelefonoListAdapter(new TelefonoListAdapter.TelefonoDiff());
        listaTelefonos.setAdapter(adapter);
        listaTelefonos.setLayoutManager(new LinearLayoutManager(this));

        telefonoViewModel = new ViewModelProvider(this).get(ContactosTelefonoViewModel.class);
        telefonoViewModel.getAllTelefonos().observe(this, tlf -> {
            adapter.submitList(tlf);
        });

        editTextNombre = (EditText) findViewById(R.id.editTextNombreContacto);
        //tlf
        editTextDireccion = (EditText) findViewById(R.id.editTextDireccionContacto);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailContacto);

        botonGuardar = (ImageButton) findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(view -> {editarContacto();});

        botonCerrar = (ImageButton) findViewById(R.id.botonCerrar);
        botonCerrar.setOnClickListener(view ->{finish();});

        getDatos();
        setDatos();

    }

    private void getDatos() {
        nombre = getIntent().getStringExtra("nombre");
        direccion = getIntent().getStringExtra("direccion");
        email = getIntent().getStringExtra("email");
        posicion = getIntent().getIntExtra("posicion", 0);

    }

    private void setDatos() {
        editTextNombre.setText(nombre);
        editTextDireccion.setText(direccion);
        editTextEmail.setText(email);
    }


    private void editarContacto() {
        intentRespuesta = new Intent();
        if (nombreOrTlfVacios())
            setResult(DEFAULT_KEYS_DIALER, intentRespuesta);
        else
            prepararContacto();
        finish();
    }

    private void prepararContacto() {
        intentRespuesta.putExtra("nombre", editTextNombre.getText().toString());
        //tlf
        intentRespuesta.putExtra("direccion", editTextDireccion.getText().toString());
        intentRespuesta.putExtra("email", editTextEmail.getText().toString());
        intentRespuesta.putExtra("posicion", posicion);

        setResult(RESULT_OK, intentRespuesta);
    }

    private boolean nombreOrTlfVacios() {
        return TextUtils.isEmpty(editTextNombre.getText());
    }
}
