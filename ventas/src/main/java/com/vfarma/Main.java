package com.vfarma;

import java.util.List;

import com.vfarma.BaseDatos.BaseDeDatos;
import com.vfarma.BaseDatos.Producto;

public class Main {

    public static void main(String[] args) {
        BaseDeDatos baseDeDatos = BaseDeDatos.obtenerInstancia();
        List<Producto> productos = baseDeDatos.obtenerTodaLaInfoDeTodosLosProductos();

        for (Producto producto : productos) {
            System.out.println(producto);
        }

       
    }
}
