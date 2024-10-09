package com.vfarma.Modelo;

import java.util.ArrayList;

public class CarritoCompras {
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        listaProductos.remove(producto);
    }

    public void vaciarLista() {
        listaProductos.clear();
    }

    public ArrayList<Producto> obtenerListaProductos() {
        return listaProductos;
    }
}