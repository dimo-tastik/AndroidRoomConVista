package com.example.contactosapp.miCasaTelefono;

public class PrefijoTelefonicoException extends RuntimeException {
    public PrefijoTelefonicoException() {
        super("Prefijo incorrecto");
    }
    public PrefijoTelefonicoException(String mensa) {
        super("Prefijo incorrecto " + mensa);
    }
}
