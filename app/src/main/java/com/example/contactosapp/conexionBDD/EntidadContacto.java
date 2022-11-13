package com.example.contactosapp.conexionBDD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_contacto")
public class EntidadContacto {

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idContacto")
    private int idContacto;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "direccion")
    private String direccion;

    public EntidadContacto(@NonNull String nombre, String email, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "EntidadContacto{" +
                "nombre='" + nombre + '\'' +
                ", idContacto=" + idContacto +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
