package com.vfarma.Modelo;

import java.util.ArrayList;

public class CarritoCompras {

    private final ArrayList<Producto> listaProductos;

    public CarritoCompras() {
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void removerProducto(Producto producto) {
        listaProductos.remove(producto);
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto producto : this.listaProductos) {
            if (producto.obtenerClaveProducto() == id) {
                return producto;
            }
        }
        return null;
    }

    public void vaciarCarrito() {
        listaProductos.clear();
    }

    public ArrayList<Producto> obtenerTodosProductos() {
        return listaProductos;
    }
}
