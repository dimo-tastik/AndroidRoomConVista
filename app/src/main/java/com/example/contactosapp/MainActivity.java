package com.example.contactosapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicaciondecontactos.R;
import com.example.contactosapp.adaptador.ContactosListAdapter;
import com.example.contactosapp.adaptador.RecyclerItemClickListener;
import com.example.contactosapp.conexionBDD.EntidadContacto;
import com.example.contactosapp.vistaModelo.ContactosTelefonoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContactos;
    ContactosTelefonoViewModel contactosViewModel;
    ContactosListAdapter adapter;
    public static final int REQUEST_CODE_NUEVO_CONTACTO = 1;
    public static final int REQUEST_CODE_EDITAR_CONTACTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = findViewById(R.id.listaContactos);
        adapter = new ContactosListAdapter(new ContactosListAdapter.ContactoDiff());
        listaContactos.setAdapter(adapter);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
        listaContactos.addOnItemTouchListener(new RecyclerItemClickListener(this, listaContactos, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int posicion) {
                itemSelecionado(v ,posicion);
            }

            @Override
            public void onLongItemClick(View v, int posicion) {
                itemSelecionadoLongClick(v,posicion);
            }
        }));

        contactosViewModel = new ViewModelProvider(this).get(ContactosTelefonoViewModel.class);
        contactosViewModel.getAllContactos().observe(this, contactos -> {
            adapter.submitList(contactos);
        });

        FloatingActionButton botonNuevoContacto = findViewById(R.id.botonNuevoContacto);
        botonNuevoContacto.setOnClickListener(view -> {
            lanzarNuevoContacto();
        });

    }
    private void lanzarNuevoContacto() {
        Intent intent = new Intent(MainActivity.this, NuevoContacto.class);
        startActivityForResult(intent, REQUEST_CODE_NUEVO_CONTACTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_NUEVO_CONTACTO && resultCode == RESULT_OK) {
            EntidadContacto nuevoContacto = new EntidadContacto(
                    data.getStringExtra("nombre"),
                    data.getStringExtra("email"),
                    data.getStringExtra("direccion")
                    //pillar telefono
            );
            contactosViewModel.insert(nuevoContacto);
            //insertar telefono
        }else if(requestCode == REQUEST_CODE_NUEVO_CONTACTO && resultCode == DEFAULT_KEYS_DIALER){
            Toast.makeText(getApplicationContext(), "Error, faltan datos.", Toast.LENGTH_LONG).show();
        }

        if (requestCode == REQUEST_CODE_EDITAR_CONTACTO && resultCode == RESULT_OK) {
            EntidadContacto  contactoEditado = adapter.getCurrentList().get(data.getIntExtra("posicion",0));
            contactoEditado.setNombre(data.getStringExtra("nombre"));
            contactoEditado.setDireccion(data.getStringExtra("direccion"));
            contactoEditado.setEmail(data.getStringExtra("email"));
                    //pillar telefono
            contactosViewModel.update(contactoEditado);
        }else if(requestCode == REQUEST_CODE_EDITAR_CONTACTO && resultCode == DEFAULT_KEYS_DIALER){
            Toast.makeText(getApplicationContext(), "Error, faltan datos.", Toast.LENGTH_LONG).show();
        }
    }


    private void itemSelecionadoLongClick(View v, int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("¿Eliminar?")
                .setPositiveButton("SI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EntidadContacto contactoParaEliminar = adapter.getCurrentList().get(posicion);
                                contactosViewModel.eliminar(contactoParaEliminar.getIdContacto());
                            }
                        })
                .setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        builder.create().show();
    }

    public void itemSelecionado(View v, int posicion){
        EntidadContacto contactoSeleccionado = adapter.getCurrentList().get(posicion);
        lanzarEditarContacto(posicion, contactoSeleccionado);
    }

    private void lanzarEditarContacto(int posicion, EntidadContacto entidadContacto) {
        Intent intent = new Intent(MainActivity.this, Detalles.class);
        intent.putExtra("nombre", entidadContacto.getNombre());
        intent.putExtra("direccion",entidadContacto.getDireccion());
        intent.putExtra("email",entidadContacto.getEmail());
        intent.putExtra("posicion", posicion);
        startActivityForResult(intent, REQUEST_CODE_EDITAR_CONTACTO);
    }
}