package com.example.contactosapp.conexionBDD;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactosTelefonoDAO {
    @Insert
    long insertarContacto(EntidadContacto entidadContactos);

    @Insert
    void insertarTelefono(EntidadTelefono entidadTelefono);

    @Query("DELETE from tabla_contacto WHERE idContacto = :idContacto")
    void eliminarContacto(int idContacto);

    @Query("DELETE from tabla_telefono WHERE idContacto = :idContacto")
    void eliminarTelefonosContacto(int idContacto);

    @Update
    void actualizarContacto(EntidadContacto entidadContactos);

    @Update
    void actualizarTelefonos(EntidadTelefono entidadTelefono);

    @Query("SELECT * FROM tabla_contacto ORDER BY nombre asc")
    LiveData<List<EntidadContacto>> getContactos();

    @Query("SELECT * FROM tabla_telefono")
    LiveData<List<EntidadTelefono>> getTelefonosContacto();

    @Query("SELECT * FROM tabla_telefono WHERE idContacto = :idContacto")
    LiveData<List<EntidadTelefono>> getTelefonosContacto(int idContacto);

}
