package com.vfarma.Modelo;

public class Efectivo implements MetodoPago {

    private float cantidadAPagar;
    private float dineroRecibido;

    public Efectivo() {}

    public void colocarCantidadAPagar(float cantidadAPagar) {
        this.cantidadAPagar = cantidadAPagar;
    }

    public void colocarDineroRecibido(float dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public float contarCambio() {
        float cambioAentregarAlCliente = this.dineroRecibido - this.cantidadAPagar;
        return cambioAentregarAlCliente;
    }

    @Override
    public float realizarPago() {
        return this.contarCambio();
    }
}
