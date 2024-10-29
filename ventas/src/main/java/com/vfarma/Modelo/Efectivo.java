package com.vfarma.Modelo;

public class Efectivo  implements MetodoPago {

    public void contarCambio() {
        System.out.println("Contando el cambio...");
    }

    @Override
    public void obtenerDetallesPago() {
        this.contarCambio();
    }

    @Override
    public boolean estaPagado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}