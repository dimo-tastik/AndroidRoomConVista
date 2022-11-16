package com.example.contactosapp.miCasaTelefono;

import java.io.Serializable;

public class Telefono implements Serializable {

    private String numeroTelefono;

    public Telefono(String numeroTelefono) {
        setNumeroTelefono(numeroTelefono);
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono.length() > 15) {
            throw new NumeroTelefonoException(numeroTelefono);
        }else{
            this.numeroTelefono = numeroTelefono;
        }
    }

    @Override
    public String toString() {
        return numeroTelefono;
    }
}
