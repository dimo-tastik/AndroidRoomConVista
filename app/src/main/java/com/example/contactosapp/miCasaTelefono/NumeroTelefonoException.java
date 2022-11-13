package com.example.contactosapp.miCasaTelefono;

public class NumeroTelefonoException extends RuntimeException {
    public NumeroTelefonoException() {
        super("Teléfono incorrecto");
    }
    public NumeroTelefonoException(String mensa) {
        super("Teléfono incorrecto " + mensa);
    }
}
