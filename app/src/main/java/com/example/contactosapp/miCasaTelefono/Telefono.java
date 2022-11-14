package com.example.contactosapp.miCasaTelefono;

import java.io.Serializable;

public class Telefono implements Serializable {

    private String prefijoTelefonico;
    private String numeroTelefono;

    public Telefono(String prefijoTelefonico, String numeroTelefono) {
        setPrefijoTelefonico(prefijoTelefonico);
        setNumeroTelefono(numeroTelefono);
    }

    public Telefono(String numeroTelefono) {
        setPrefijoTelefonico(null);
        setNumeroTelefono(numeroTelefono);
    }

    public String getPrefijoTelefonico() {
        return prefijoTelefonico;
    }

    public void setPrefijoTelefonico(String prefijoTelefonico) {
        if (prefijoTelefonico == null){
            this.prefijoTelefonico = "";
        }else if (prefijoTelefonico.length() > 5) {
            throw new PrefijoTelefonicoException(prefijoTelefonico);
        }else {
            this.prefijoTelefonico = "+".concat(prefijoTelefonico);
        }
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono.length() > 9) {
            throw new NumeroTelefonoException(numeroTelefono);
        }
        this.numeroTelefono = numeroTelefono.substring(0,3) + "-" + numeroTelefono.substring(3,5) + "-" + numeroTelefono.substring(5,7) + "-" + numeroTelefono.substring(7,9);
    }

    @Override
    public String toString() {
        return prefijoTelefonico + " " + numeroTelefono;
    }
}
