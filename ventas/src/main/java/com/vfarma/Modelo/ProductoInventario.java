package com.vfarma.Modelo;

import java.util.Date;

public class ProductoInventario {
    private int claveProducto;
    private int lote;
    private Date fechaCaducidad;
    private int cantidad;

    public ProductoInventario() {
    }

    public ProductoInventario(int claveProducto, int lote, Date fechaCaducidad, int cantidad) {
        this.claveProducto = claveProducto;
        this.lote = lote;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
    }

    public int obtenerClaveProducto() {
        return claveProducto;
    }

    public int obtenerLote() {
        return lote;
    }

    public Date obtenerFechaCaducidad() {
        return fechaCaducidad;
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    public void colocarClaveProducto(int claveProducto) {
        this.claveProducto = claveProducto;
    }

    public void colocarLote(int lote) {
        this.lote = lote;
    }

    public void colocarFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void colocarCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
