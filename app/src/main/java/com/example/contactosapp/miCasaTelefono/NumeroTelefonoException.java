package com.example.contactosapp.miCasaTelefono;

public class NumeroTelefonoException extends RuntimeException {
    public NumeroTelefonoException() {
        super("Teléfono no válido");
    }
    public NumeroTelefonoException(String mensa) {
        super("Teléfono no válido " + mensa);
    }
}
