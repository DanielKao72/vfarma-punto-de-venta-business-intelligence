package com.vfarma.Modelo;

public class Producto {

    private int claveProducto;
    private String nombreProducto;
    private float precioProducto;
   
    private int existenciaProducto;

    public Producto() {
    }

    public Producto(int claveProducto, String nombreProducto, float precioProducto, int existenciaProducto) {
        this.claveProducto = claveProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;

        this.existenciaProducto = existenciaProducto;
    }

    public int obtenerClaveProducto() {
        return claveProducto;
    }

    public String obtenerNombreProducto() {
        return nombreProducto;
    }

    public float obtenerPrecioProducto() {
        return precioProducto;
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

   

    public void colocarExistenciaProducto(int existenciaProducto) {
        this.existenciaProducto = existenciaProducto;
    }

}
