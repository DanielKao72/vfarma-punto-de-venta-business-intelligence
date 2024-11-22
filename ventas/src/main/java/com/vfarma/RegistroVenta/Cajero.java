package com.vfarma.RegistroVenta;

import java.io.FileNotFoundException;

import com.itextpdf.layout.Document;
import com.vfarma.BaseDatos.GestorCaja;
import com.vfarma.BaseDatos.InventarioProductos;
import com.vfarma.Modelo.Efectivo;
import com.vfarma.Modelo.InformacionCliente;
import com.vfarma.Modelo.InformacionVenta;
import com.vfarma.Modelo.Producto;

public class Cajero {

    private static Cajero cajero;
    public InformacionVenta informacionVenta;
    public InventarioProductos consultasProducto;
    public GestorCaja consultasCaja;
    private String nombreCaja;

    public Cajero() {
        this.informacionVenta = new InformacionVenta();
        this.consultasProducto = new InventarioProductos();
        this.consultasCaja = new GestorCaja();
    }

    public static Cajero obtenerCajero() {
        if (cajero == null) {
            cajero = new Cajero();
        }
        return cajero;
    }

    public String obtenerNombreCaja() {
        return this.nombreCaja;
    }

    public void colocarNombreCaja(String nombreCaja) {
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

    public float efectuarPago() {
        return this.informacionVenta.obtenerInformacionCliente().obtenerPago().obtenerMetodoPago().realizarPago();
    }

    public void imprimirComprobante() {
        try {
            Document comprobanteLlenado = this.informacionVenta.obtenerComprobante().llenarInformacionComprobante();
            this.informacionVenta.obtenerComprobante().enviarAImpresion(comprobanteLlenado);
        } catch (FileNotFoundException e) {
        }
    }

    public float calcularCambioVenta() {

        float cambioDelCliente = this.efectuarPago();
        this.informacionVenta.obtenerCarritoCompras().obtenerTodosProductos().forEach(producto -> {
            this.consultasProducto.restarExistenciaProducto(producto.obtenerClaveProducto(), 1);
        });

        return cambioDelCliente;
    }

    public void terminarVenta() {
        Cajero.cajero = null;
    }

}
