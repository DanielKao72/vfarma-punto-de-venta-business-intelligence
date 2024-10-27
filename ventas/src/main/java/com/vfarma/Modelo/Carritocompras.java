package com.vfarma.Modelo;

import java.util.ArrayList;

public class CarritoCompras {
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void removerProducto(Producto producto) {
        listaProductos.remove(producto);
    }

    public void vaciarCarrito() {
        listaProductos.clear();
    }

    public ArrayList<Producto> obtenerTodosProductos() {
        return listaProductos;
    }
}