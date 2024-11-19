package com.vfarma.Modelo;

import java.util.Date;


public class Producto   {

    private int claveProducto;
    private String nombreProducto;
    private float precioProducto;
    private Date fechaCaducidad;
    private int existenciaProducto;

    public int obtenerClaveProducto() {
        return claveProducto;
    }

    public String obtenerNombreProducto() {
        return nombreProducto;
    }

    public float obtenerPrecioProducto() {
        return precioProducto;
    }

    public Date obtenerFechaCaducidad() {
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

    public void colocarFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void colocarExistenciaProducto(int existenciaProducto) {
        this.existenciaProducto = existenciaProducto;
    }

}
