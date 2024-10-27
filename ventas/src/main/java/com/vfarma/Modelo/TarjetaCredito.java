package com.vfarma.Modelo;

public class TarjetaCredito implements MetodoPago {

 

    public void autorizarTransaccion() {
        System.out.println("Autorizando transacción con tarjeta de crédito...");
    }

    @Override
    public void obtenerDetallesPago() {
        this.autorizarTransaccion();
    }

    @Override
    public boolean estaPagado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
