package com.vfarma.RegistroVenta;

import com.vfarma.BaseDatos.ConsultasProducto;
import com.vfarma.Modelo.Efectivo;
import com.vfarma.Modelo.Factura;
import com.vfarma.Modelo.InformacionPersonaFisica;
import com.vfarma.Modelo.InformacionPersonaMoral;
import com.vfarma.Modelo.InformacionVenta;
import com.vfarma.Modelo.Producto;
import com.vfarma.Modelo.Recibo;
import com.vfarma.Modelo.TarjetaCredito;

public class Cajero {
    public InformacionVenta informacionVenta;
    public ConsultasProducto consultasProducto;
    //public InformacionEmpleado datosCajero;

    public Cajero() {
        this.informacionVenta = new InformacionVenta();
        this.consultasProducto = new ConsultasProducto();
    }

    public void agregarProductoACarrito(int idProducto) {
        if (this.consultasProducto.existenciaProducto(idProducto) == 0) {
            System.out.println("Producto no disponible");
            return;
        }
        Producto productoEncontrado = this.consultasProducto.buscarProductoPorID(idProducto);
        this.informacionVenta.carritoCompras.agregarProducto(productoEncontrado);
    }

    public void retirarProductoDeCarrito(int idProducto) {
        Producto productoEncontrado = this.informacionVenta.carritoCompras.buscarProductoPorId(idProducto);
        this.informacionVenta.carritoCompras.removerProducto(productoEncontrado);
    }

    public enum TipoCliente {
        PERSONA_MORAL,
        PERSONA_FISICA
    }

    public void seleccionarTipoCliente(TipoCliente tipoCliente) {
        switch (tipoCliente) {
            case PERSONA_MORAL -> {
                this.informacionVenta.informacionCliente = new InformacionPersonaMoral();
                this.informacionVenta.comprobante = new Factura();
            }
            case PERSONA_FISICA -> {
                this.informacionVenta.informacionCliente = new InformacionPersonaFisica();
                this.informacionVenta.comprobante = new Recibo();
            }
            default -> System.out.println("Tipo de cliente no válido");
        }
    }

    public enum TipoPago {
        EFECTIVO,
        TARJETA
    }
    

    public void seleccionarTipoPagoCliente(TipoPago tipoPago) {
        switch (tipoPago) {
            case EFECTIVO -> {
                this.informacionVenta.informacionCliente.pago.metodoPago = new Efectivo();
            }
            case TARJETA -> {
                this.informacionVenta.informacionCliente.pago.metodoPago = new TarjetaCredito();
            }
            default -> System.out.println("Tipo de pago no válido");
        }
        
    }

    public void efectuarPago() {
        this.informacionVenta.informacionCliente.pago.metodoPago.obtenerDetallesPago();
    }

    public void imprimirComprobante(){
        this.informacionVenta.comprobante.llenarInformacionComprobante(this.informacionVenta);
    }

    public void finalizarVenta() {
        this.efectuarPago();
        this.informacionVenta.carritoCompras.obtenerTodosProductos().forEach(producto -> {
            this.consultasProducto.restarExistenciaProducto(producto.obtenerClaveProducto(), 1);
        });
        this.imprimirComprobante();
        
    }

    /* 
    public void establecerBalanceInicial(){

    }

    public void abrirCaja(){

    }

    public void cerrarCaja(){
    }
    */
}
