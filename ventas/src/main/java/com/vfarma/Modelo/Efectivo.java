package com.vfarma.Modelo;

public class Efectivo implements MetodoPago {
    @Override
    public void obtenerDetallesPago() {
        System.out.println("Pago en efectivo");
    }

    @Override
    public boolean estaPagado() {
        return true;
    }

    public void contarCambio() {
        System.out.println("Contando el cambio...");
    }
}