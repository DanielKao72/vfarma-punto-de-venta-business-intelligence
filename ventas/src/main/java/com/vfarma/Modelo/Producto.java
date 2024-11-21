package com.vfarma.Modelo;

public class Producto {

    private int claveProducto;
    private String nombreProducto;
    private int precioProducto;
    private String fechaCaducidad;
    private int existenciaProducto;


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
