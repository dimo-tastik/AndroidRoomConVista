package com.example.contactosapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;
import com.example.contactosapp.adaptador.RecyclerItemClickListener;
import com.example.contactosapp.adaptador.TelefonoListAdapter;
import com.example.contactosapp.conexionBDD.EntidadTelefono;
import com.example.contactosapp.miCasaTelefono.Telefono;
import com.example.contactosapp.vistaModelo.ContactosTelefonoViewModel;

public class Detalles extends AppCompatActivity implements View.OnClickListener {

    RecyclerView listaTelefonos;
    ContactosTelefonoViewModel telefonoViewModel;
    TelefonoListAdapter adapter;


    private ImageView imageView;
    private EditText editTextNombre;

    private EditText editTextNuevoTelefono;

    private EditText editTextDireccion;
    private EditText editTextEmail;
    private ImageButton botonGuardar;
    private ImageButton botonCerrar;

    private ImageButton botonDireccion;
    private ImageButton botonEmail;

    private ImageButton botonNuevoTelefono;

    int idContacto;
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

        listaTelefonos.addOnItemTouchListener(new RecyclerItemClickListener(this, listaTelefonos, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int posicion) {
                String numeroTelefono = adapter.getCurrentList().get(posicion).getTelefono().getNumeroTelefono();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numeroTelefono));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View v, int posicion) {
                //eliminar
            }
        }));

        imageView = findViewById(R.id.imageViewGrande);
        editTextNombre = (EditText) findViewById(R.id.editTextNombreContacto);
        editTextNuevoTelefono = (EditText) findViewById(R.id.editTextNuevoTelefono);
        editTextDireccion = (EditText) findViewById(R.id.editTextDireccionContacto);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailContacto);

        botonGuardar = (ImageButton) findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(view -> {
            editarContacto();
        });

        botonCerrar = (ImageButton) findViewById(R.id.botonCerrar);
        botonCerrar.setOnClickListener(view -> {
            finish();
        });

        botonDireccion = (ImageButton) findViewById(R.id.botonDireccion);
        botonDireccion.setOnClickListener(view -> {
            intentMapa();
        });

        botonEmail = (ImageButton) findViewById(R.id.botonEmail);
        botonEmail.setOnClickListener(view -> {
            intentGmail();

        });

        botonNuevoTelefono = (ImageButton) findViewById(R.id.botonNuevoTelefono);
        botonNuevoTelefono.setOnClickListener(this);
        botonNuevoTelefono.setEnabled(false);

        editTextNuevoTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")){
                    botonNuevoTelefono.setEnabled(false);
                }else{
                    botonNuevoTelefono.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")){
                    botonNuevoTelefono.setEnabled(false);
                }else{
                    botonNuevoTelefono.setEnabled(true);
                }
            }
        });

        getDatos();
        setDatos();

        telefonoViewModel = new ViewModelProvider(this).get(ContactosTelefonoViewModel.class);
        telefonoViewModel.getTelefonosContacto(idContacto).observe(this, tlf -> {
            adapter.submitList(tlf);
        });

    }

    private void intentMapa() {
        Uri location = Uri.parse("geo:0,0?q="+editTextDireccion.getText().toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }

    private void intentGmail() {
        String email = editTextEmail.getText().toString();

        Intent irAGmail = new Intent();
        irAGmail.setAction(Intent.ACTION_SENDTO);
        irAGmail.setData(Uri.parse("mailto:" + email));

        if (irAGmail.resolveActivity(getPackageManager()) != null)
            startActivity(irAGmail);
    }

    private void getDatos() {
        nombre = getIntent().getStringExtra("nombre");
        direccion = getIntent().getStringExtra("direccion");
        email = getIntent().getStringExtra("email");
        idContacto = getIntent().getIntExtra("idContacto", 0);

    }

    private void setDatos() {
        imageView.setImageResource(R.drawable.contacto200x200);
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
        intentRespuesta.putExtra("direccion", editTextDireccion.getText().toString());
        intentRespuesta.putExtra("email", editTextEmail.getText().toString());
        intentRespuesta.putExtra("idContacto", idContacto);

        setResult(RESULT_OK, intentRespuesta);
    }

    private boolean nombreOrTlfVacios() {
        return TextUtils.isEmpty(editTextNombre.getText());
    }

    @Override
    public void onClick(View view) {
        telefonoViewModel.insert(new EntidadTelefono(new Telefono(String.valueOf(editTextNuevoTelefono.getText())), idContacto));
        editTextNuevoTelefono.setText(null);
    }
}
