package com.example.contactosapp.conexionBDD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EntidadContacto.class, EntidadTelefono.class}, version = 1, exportSchema = false)
public abstract class BaseDatosContactos extends RoomDatabase {

    private static final String DB_NOMBRE = "contactos_db";

    public abstract ContactosTelefonoDAO contactoDAO();

    //Hilos necesarios para la escritura de la BBDD permitiendo el uso de la aplicación durante esta tarea.
    private static final int NUMERO_HILOS =  4;
    public static final ExecutorService baseDatosEscritor = Executors.newFixedThreadPool(NUMERO_HILOS);

    //"Patron sigleton" con la intención de que el objeto generado por esta clase sea único en toda la aplicación
    private static volatile BaseDatosContactos INSTANCIA;

    public static BaseDatosContactos getDatabase(final Context context) {
        if (INSTANCIA == null) {
            //region critica
            synchronized (BaseDatosContactos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                                    BaseDatosContactos.class, DB_NOMBRE)
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

}
