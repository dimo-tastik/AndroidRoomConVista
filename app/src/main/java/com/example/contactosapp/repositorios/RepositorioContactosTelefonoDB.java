package com.example.contactosapp.repositorios;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.contactosapp.conexionBDD.BaseDatosContactos;
import com.example.contactosapp.conexionBDD.ContactosTelefonoDAO;
import com.example.contactosapp.conexionBDD.EntidadContacto;
import com.example.contactosapp.conexionBDD.EntidadTelefono;

import java.util.List;

public class RepositorioContactosTelefonoDB {

    private ContactosTelefonoDAO contactosTelefonoDAO;
    private LiveData<List<EntidadContacto>> AllContactos;
    private LiveData<List<EntidadTelefono>> AllTelefonos;

    public RepositorioContactosTelefonoDB(Application application) {
        BaseDatosContactos db = BaseDatosContactos.getDatabase(application);
        contactosTelefonoDAO = db.contactoDAO();
        AllContactos = contactosTelefonoDAO.getContactos();
        AllTelefonos = contactosTelefonoDAO.getTelefonos();
    }

    public LiveData<List<EntidadContacto>> getAllContactos() {
        return AllContactos;
    }
    public LiveData<List<EntidadTelefono>> getAllTelefonos() {
        return AllTelefonos;
    }

    public void insertarContacto(EntidadContacto contacto) {
        BaseDatosContactos.baseDatosEscritor.execute(() -> {
            contactosTelefonoDAO.insertarContacto(contacto);
        });
    }
    public void insertarTelefono(EntidadTelefono telefono) {
        BaseDatosContactos.baseDatosEscritor.execute(() -> {
            contactosTelefonoDAO.insertarTelefono(telefono);
        });
    }

    public void eliminarContacto(int idContacto){
        BaseDatosContactos.baseDatosEscritor.execute(() -> {
            contactosTelefonoDAO.eliminarContacto(idContacto);
        });
    }
}
