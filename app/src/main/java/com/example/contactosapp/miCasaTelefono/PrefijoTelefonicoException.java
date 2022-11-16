package com.example.contactosapp.miCasaTelefono;

public class PrefijoTelefonicoException extends RuntimeException {
    public PrefijoTelefonicoException() {
        super("Prefijo no válido");
    }
    public PrefijoTelefonicoException(String mensa) {
        super("Prefijo no válido " + mensa);
    }
}
