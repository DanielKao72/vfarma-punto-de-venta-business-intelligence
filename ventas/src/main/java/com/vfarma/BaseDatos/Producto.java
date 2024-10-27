package com.vfarma.BaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto implements MapeadorBaseDatos {
    private int productoID;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria; // Objeto relacionado
    private Familia familia; // Objeto relacionado
    private Proveedor proveedor; // Objeto relacionado

    @Override
    public void mapearDelConjuntoResultado(ResultSet conjuntoResultado) throws SQLException {
        this.productoID = conjuntoResultado.getInt("ProductoID");
        this.nombre = conjuntoResultado.getString("Nombre");
        this.descripcion = conjuntoResultado.getString("Descripcion");
        this.precio = conjuntoResultado.getDouble("Precio");

        this.categoria = new Categoria();
        this.categoria.mapearDelConjuntoResultado(conjuntoResultado); // Mapea la categoria

        this.familia = new Familia();
        this.familia.mapearDelConjuntoResultado(conjuntoResultado); // Mapea la familia

        this.proveedor = new Proveedor();
        this.proveedor.mapearDelConjuntoResultado(conjuntoResultado); // Mapea el proveedor
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productoID=" + productoID +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", familia=" + familia +
                ", proveedor=" + proveedor +
                '}';
    }

    // Getters y Setters
}
