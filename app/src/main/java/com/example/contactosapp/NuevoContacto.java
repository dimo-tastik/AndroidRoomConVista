package com.example.contactosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.aplicaciondecontactos.R;

public class NuevoContacto extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextTlf;
    private EditText editTextDireccion;
    private EditText editTextEmail;
    private ImageButton botonGuardar;
    private ImageButton botonCerrar;
    Intent intentRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        editTextNombre = (EditText) findViewById(R.id.editTextTextNombreContacto);
        editTextTlf = (EditText) findViewById(R.id.editTextTextNumeroTlf);
        editTextDireccion = (EditText) findViewById(R.id.editTextTextDireccionContacto);
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailContacto);

        botonGuardar = (ImageButton) findViewById(R.id.botonGuardar);

        botonGuardar.setOnClickListener(view -> {guardarContacto();});

        botonCerrar = (ImageButton) findViewById(R.id.botonCerrar);
        botonCerrar.setOnClickListener(view ->{finish();});
    }

    private void guardarContacto() {
        intentRespuesta = new Intent();
        if (nombreOrTlfVacios())
            setResult(DEFAULT_KEYS_DIALER, intentRespuesta);
        else
            prepararContacto();
        finish();
    }

    private void prepararContacto() {
        intentRespuesta.putExtra("nombre", editTextNombre.getText().toString());
        intentRespuesta.putExtra("telefono", editTextTlf.getText().toString());
        intentRespuesta.putExtra("direccion", editTextDireccion.getText().toString());
        intentRespuesta.putExtra("email", editTextEmail.getText().toString());


        setResult(RESULT_OK, intentRespuesta);
    }

    private boolean nombreOrTlfVacios() {
        return TextUtils.isEmpty(editTextNombre.getText()) || TextUtils.isEmpty(editTextTlf.getText());
    }
}