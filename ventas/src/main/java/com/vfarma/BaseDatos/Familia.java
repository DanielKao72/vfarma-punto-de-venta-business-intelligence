package com.vfarma.BaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Familia implements MapeadorBaseDatos {
    private int familiaID;
    private String nombre;

    @Override
    public void mapearDelConjuntoResultado(ResultSet conjuntoResultado) throws SQLException {
        this.familiaID = conjuntoResultado.getInt("FamiliaID");
        this.nombre = conjuntoResultado.getString("Nombre");
    }

    @Override
    public String toString() {
        return "Familia{" +
                "familiaID=" + familiaID +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // Getters y Setters
}
