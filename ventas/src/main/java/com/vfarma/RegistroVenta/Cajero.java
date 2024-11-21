package com.vfarma.RegistroVenta;

import java.io.FileNotFoundException;

import com.itextpdf.layout.Document;
import com.vfarma.BaseDatos.ConsultasProducto;
import com.vfarma.Modelo.Efectivo;
import com.vfarma.Modelo.InformacionCliente;
import com.vfarma.Modelo.InformacionVenta;
import com.vfarma.Modelo.Producto;

public class Cajero {

    private static Cajero instanciaUnica; 
    public InformacionVenta informacionVenta;
    public ConsultasProducto consultasProducto;
    private String nombreCaja;

    public Cajero() {
        this.informacionVenta = new InformacionVenta();
        this.consultasProducto = new ConsultasProducto();
    }

    public static Cajero obtenerInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Cajero();
        }
        return instanciaUnica;
    }


    public void colocarNombreCaja(String nombreCaja){
        this.nombreCaja = nombreCaja;
    }

    public void agregarProductoACarrito(int idProducto) {
        if (this.consultasProducto.contarExistenciaProducto(idProducto) == 0) {
            System.out.println("Producto no disponible");
            return;
        }
        Producto productoEncontrado = this.consultasProducto.buscarProductoPorID(idProducto);
        this.informacionVenta.obtenerCarritoCompras().agregarProducto(productoEncontrado);
    }

    public void retirarProductoDeCarrito(int idProducto) {
        Producto productoEncontrado = this.informacionVenta.obtenerCarritoCompras().buscarProductoPorIdDelProducto(idProducto);
        this.informacionVenta.obtenerCarritoCompras().removerProducto(productoEncontrado);
    }

    

    public void seleccionarTipoCliente(InformacionCliente tipoCliente) {
        this.informacionVenta.colocarInformacionCliente(tipoCliente);
    }

    public enum TipoPago {
        EFECTIVO
    }

    public void seleccionarTipoPagoCliente(TipoPago tipoPago) {
        switch (tipoPago) {
            case EFECTIVO -> {
                this.informacionVenta.obtenerInformacionCliente().obtenerPago().colocarMetodoPago(new Efectivo());
            }
            default ->
                System.out.println("Tipo de pago no válido");
        }

    }

    public float efectuarPago( ) {
        return this.informacionVenta.obtenerInformacionCliente().obtenerPago().obtenerMetodoPago().obtenerDetallesPago();
    }

    public void imprimirComprobante() {
        try {
            Document comprobanteLlenado = this.informacionVenta.obtenerComprobante().llenarInformacionComprobante();
            this.informacionVenta.obtenerComprobante().enviarAImpresion(comprobanteLlenado);
        } catch (FileNotFoundException e) {
        }
    }

    public void finalizarVenta() {
        System.out.println(this.informacionVenta.obtenerCarritoCompras().obtenerTodosProductos());
        Float cambioDelCliente = this.efectuarPago();
        this.informacionVenta.obtenerCarritoCompras().obtenerTodosProductos().forEach(producto -> {
            this.consultasProducto.restarExistenciaProducto(producto.obtenerClaveProducto(), 1);
        });
        this.imprimirComprobante();
        System.out.println("Cambio a entregar al cliente: " + cambioDelCliente);
        System.out.println("Venta finalizada");
    }

    

}
