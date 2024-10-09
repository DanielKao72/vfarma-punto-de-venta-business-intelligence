package com.vfarma.Modelo;

public class TarjetaCredito implements MetodoPago {
    @Override
    public void obtenerDetallesPago() {
        System.out.println("Pago con tarjeta de crédito");
    }

    @Override
    public boolean estaPagado() {
        return true;    
    }

    public void autorizarTransaccion() {
        System.out.println("Autorizando transacción con tarjeta de crédito...");
    }
}
