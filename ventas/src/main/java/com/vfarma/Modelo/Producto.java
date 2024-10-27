package com.vfarma.Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vfarma.BaseDatos.ConsultasProducto;
import com.vfarma.BaseDatos.MapeadorBaseDatos;
public class Producto implements MapeadorBaseDatos {

    private int claveProducto;
    private String nombreProducto;
    private int precioProducto;
    private String fechaCaducidad;
    private int existenciaProducto;


    @Override
    public void mapearDelConjuntoResultado(ResultSet conjuntoResultado) throws SQLException {
        this.claveProducto = conjuntoResultado.getInt("ProductoID");
        this.nombreProducto = conjuntoResultado.getString("Nombre");
        this.precioProducto = conjuntoResultado.getInt("Precio");
        
        ConsultasProducto consultasProducto = new ConsultasProducto();
        this.fechaCaducidad = consultasProducto.obtenerFechaCaducidad(this.claveProducto);
        this.existenciaProducto = consultasProducto.existenciaProducto(this.claveProducto);
        // se podria agregar el proveedor
    }


    public int obtenerClaveProducto() {
        return claveProducto;
    }

    public String obtenerNombreProducto() {
        return nombreProducto;
    }

    public int obtenerPrecioProducto() {
        return precioProducto;
    }

    public String obtenerFechaCaducidad() {
        return fechaCaducidad;
    }

    public int obtenerExistenciaProducto() {
        return existenciaProducto;
    }

    public void colocarClaveProducto(int claveProducto) {
        this.claveProducto = claveProducto;
    }

    public void colocarNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void colocarPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public void colocarFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void colocarExistenciaProducto(int existenciaProducto) {
        this.existenciaProducto = existenciaProducto;
    }

   
}
