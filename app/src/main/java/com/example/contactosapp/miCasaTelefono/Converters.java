package com.example.contactosapp.miCasaTelefono;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Telefono fromString(String value) {
        return value == null ? null : new Telefono(value);
    }

    @TypeConverter
    public static String tlfToString(Telefono telefono) {
        return telefono == null ? null : telefono.toString();
    }
}
