package com.example.contactosapp;

import static org.junit.Assert.assertEquals;

import com.example.contactosapp.miCasaTelefono.Telefono;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void telefonoToStringIsCorrect(){
        Telefono tlf = new Telefono("34","608456638");
        assertEquals("Telefono{" +
                "prefijoTelefonico='" + "+34" + '\'' +
                ", numeroTelefono='" + "608-45-66-38" + '\'' +
                '}',tlf.toString());
    }
    @Test
    public void telefonoComprobarNumero(){
        Telefono tlf = new Telefono("34","686204988");
        assertEquals("686-20-49-88",tlf.getNumeroTelefono());
    }
}