package com.vfarma.Farmacia.DatosFarmacia;

import java.util.ArrayList;

public class CarritoCompras {

    private final ArrayList<InformacionProducto> listaProductos;

    public CarritoCompras() {
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(InformacionProducto informacionProducto) {
        listaProductos.add(informacionProducto);
    }

    public void removerProducto(InformacionProducto informacionProducto) {
        listaProductos.remove(informacionProducto);
    }

    public void vaciarCarrito() {
        listaProductos.clear();
    }

    public ArrayList<InformacionProducto> obtenerTodosProductos() {
        return this.listaProductos;
    }

    public InformacionProducto buscarProductoPorIdDelProducto(int id) {
        for (InformacionProducto producto : this.listaProductos) {
            if (producto.obtenerClaveProducto() == id) {
                return producto;
            }
        }
        return null;
    }
}
