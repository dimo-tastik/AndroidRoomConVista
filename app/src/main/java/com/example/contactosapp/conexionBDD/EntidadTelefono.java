package com.example.contactosapp.conexionBDD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.contactosapp.miCasaTelefono.Telefono;


@Entity(tableName = "tabla_telefono"/*,
        foreignKeys = {@ForeignKey(entity = EntidadContacto.class,
                parentColumns = "idContacto",
                childColumns = "idContacto",
                onDelete = ForeignKey.CASCADE)
        }*/)
public class EntidadTelefono {

    @NonNull
    @ColumnInfo(name = "idContacto")
    private int idContacto;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "telefono")
    private Telefono telefono;

    public EntidadTelefono(@NonNull Telefono telefono, int idContacto) {
        this.telefono = telefono;
        setIdContacto(idContacto);
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    @NonNull
    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(@NonNull Telefono telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "EntidadTelefono{" +
                "idContacto=" + idContacto +
                ", telefono=" + telefono +
                '}';
    }
}
