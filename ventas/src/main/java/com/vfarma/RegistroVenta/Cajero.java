package com.vfarma.RegistroVenta;

import java.io.FileNotFoundException;

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
        this.informacionVenta.obtenerCarritoCompras().agregarProducto(productoEncontrado);
    }

    public void retirarProductoDeCarrito(int idProducto) {
        Producto productoEncontrado = this.informacionVenta.obtenerCarritoCompras().buscarProductoPorId(idProducto);
        this.informacionVenta.obtenerCarritoCompras().removerProducto(productoEncontrado);
    }

    public enum TipoCliente {
        PERSONA_MORAL,
        PERSONA_FISICA
    }

    public void seleccionarTipoCliente(TipoCliente tipoCliente) {
        switch (tipoCliente) {
            case PERSONA_MORAL -> {
                this.informacionVenta.colocarInformacionCliente(new InformacionPersonaMoral());
                this.informacionVenta.colocarComprobante(new Factura(this.informacionVenta) );
            }
            case PERSONA_FISICA -> {
                this.informacionVenta.colocarInformacionCliente(new InformacionPersonaFisica()) ;
                this.informacionVenta.colocarComprobante(new Recibo(this.informacionVenta));
            }
            default ->
                System.out.println("Tipo de cliente no válido");
        }
    }

    public enum TipoPago {
        EFECTIVO,
        TARJETA
    }

    public void seleccionarTipoPagoCliente(TipoPago tipoPago) {
        switch (tipoPago) {
            case EFECTIVO -> {
                this.informacionVenta.obtenerInformacionCliente().obtenerPago().colocarMetodoPago(new Efectivo()); 
            }
            case TARJETA -> {
                this.informacionVenta.obtenerInformacionCliente().obtenerPago().colocarMetodoPago(new TarjetaCredito());
            }
            default ->
                System.out.println("Tipo de pago no válido");
        }

    }

    public void efectuarPago() {
        this.informacionVenta.obtenerInformacionCliente().obtenerPago().obtenerMetodoPago().obtenerDetallesPago();
    }

    public void imprimirComprobante() {
        try {
            this.informacionVenta.obtenerComprobante().generarComprobante(this.informacionVenta);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void finalizarVenta() {
        this.efectuarPago();
        this.informacionVenta.obtenerCarritoCompras().obtenerTodosProductos().forEach(producto -> {
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
