package com.vfarma.Modelo;

public class Pago {

    private MetodoPago metodoPago;

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public MetodoPago obtenerMetodoPago() {
        return this.metodoPago;
    }

    public void colocarMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    // NO USADO???
    public void procesarPago() {}

}
