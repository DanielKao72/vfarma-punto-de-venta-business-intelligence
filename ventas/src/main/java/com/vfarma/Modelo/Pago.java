package com.vfarma.Modelo;

public class Pago {

    private MetodoPago metodoPago;

    public MetodoPago obtenerMetodoPago() {
        return this.metodoPago;
    }

    public void colocarMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

}
