package com.vfarma.Farmacia.DatosFarmacia;

public class InformacionLoteProducto {
    private int claveProducto;
    private int lote;
    private String fechaCaducidad;
    private int cantidad;

    public InformacionLoteProducto() {
    }

    public InformacionLoteProducto(int claveProducto, int lote, String fechaCaducidad, int cantidad) {
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

    public String obtenerFechaCaducidad() {
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

    public void colocarFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void colocarCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
