package com.example.contactosapp.conexionBDD;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.contactosapp.miCasaTelefono.Converters;
import com.example.contactosapp.miCasaTelefono.Telefono;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EntidadContacto.class, EntidadTelefono.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
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
                                    BaseDatosContactos.class, DB_NOMBRE).addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCIA;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            baseDatosEscritor.execute(() -> {

                ContactosTelefonoDAO dao = INSTANCIA.contactoDAO();

                EntidadContacto contacto = new EntidadContacto("Yo","dtomassob@gmail.com","Av. de las Ciudades");
                long id = dao.insertarContacto(contacto);
                EntidadTelefono telefono = new EntidadTelefono(new Telefono("608456638"), (int) id);
                dao.insertarTelefono(telefono);

                contacto = new EntidadContacto("Aa Mamá","email@gmail.com","C/ dirección");
                id = dao.insertarContacto(contacto);
                telefono = new EntidadTelefono(new Telefono("686204988"), (int) id);
                dao.insertarTelefono(telefono);

                contacto = new EntidadContacto("Dwayne Johnson","dwayneJohnson@gmail.com","C/ la piedra");
                id = dao.insertarContacto(contacto);
                telefono = new EntidadTelefono(new Telefono("3106202187"),(int) id);
                dao.insertarTelefono(telefono);

            });
        }
    };

}
