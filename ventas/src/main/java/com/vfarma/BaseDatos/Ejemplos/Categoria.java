package com.vfarma.BaseDatos.Ejemplos;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vfarma.BaseDatos.MapeadorBaseDatos;

public class Categoria implements MapeadorBaseDatos {
    private int categoriaID;
    private String nombre;

    @Override
    public void mapearDelConjuntoResultado(ResultSet conjuntoResultado) throws SQLException {
        this.categoriaID = conjuntoResultado.getInt("CategoriaID");
        this.nombre = conjuntoResultado.getString("Nombre");
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "categoriaID=" + categoriaID +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    // Getters y Setters
}
