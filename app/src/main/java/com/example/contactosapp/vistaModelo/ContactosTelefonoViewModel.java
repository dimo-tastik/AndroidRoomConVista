package com.example.contactosapp.vistaModelo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.contactosapp.conexionBDD.EntidadContacto;
import com.example.contactosapp.conexionBDD.EntidadTelefono;
import com.example.contactosapp.repositorios.RepositorioContactosTelefonoDB;

import java.util.List;

public class ContactosTelefonoViewModel  extends AndroidViewModel {

    private RepositorioContactosTelefonoDB Repositorio;

    private final LiveData<List<EntidadContacto>> AllContactos;
    private final LiveData<List<EntidadTelefono>> AllTelefonos;

    public ContactosTelefonoViewModel (@NonNull Application application) {
        super(application);
        Repositorio = new RepositorioContactosTelefonoDB(application);
        AllContactos = Repositorio.getAllContactos();
        AllTelefonos = Repositorio.getAllTelefonos();
    }

    LiveData<List<EntidadContacto>> getAllContactos() { return AllContactos; }
    LiveData<List<EntidadTelefono>> getAllTelefonos() { return AllTelefonos; }


    public void insert(EntidadContacto contacto) { Repositorio.insertarContacto(contacto); }
    public void insert(EntidadTelefono telefono) { Repositorio.insertarTelefono(telefono); }

}