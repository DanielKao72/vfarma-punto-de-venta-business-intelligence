package com.vfarma.BaseDatos.Ejemplos;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vfarma.BaseDatos.MapeadorBaseDatos;

public class Proveedor implements MapeadorBaseDatos {
    private int proveedorID;
    private String nombre;

    @Override
    public void mapearDelConjuntoResultado(ResultSet conjuntoResultado) throws SQLException {
        this.proveedorID = conjuntoResultado.getInt("ProveedorID");
        this.nombre = conjuntoResultado.getString("Nombre");
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "proveedorID=" + proveedorID +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // Getters y Setters
}
