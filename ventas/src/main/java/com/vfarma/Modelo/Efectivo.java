package com.vfarma.Modelo;

public class Efectivo implements MetodoPago {

    private float cantidadAPagar;
    private float dineroRecibido;

    public Efectivo() {
    }

    public void colocarCantidadAPagar(float cantidadAPagar) {
        this.cantidadAPagar = cantidadAPagar;
    }   

    public void colocarDineroRecibido(float dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public float contarCambio() {
        float cambioAentregarAlCliente =  this.dineroRecibido  - this.cantidadAPagar;
        //System.out.println("Dinero recibido: " + this.dineroRecibido);
        //System.out.println("Cantidad a pagar (Cuanto costo la compra): " + this.cantidadAPagar);
        //System.out.println("Cambio: " + cambio);
        return cambioAentregarAlCliente;
    }

    @Override
    public float obtenerDetallesPago( ) {
        return this.contarCambio();
    }

    @Override
    public boolean estaPagado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
