package com.vfarma.Farmacia.Ventas;

import java.io.FileNotFoundException;

import com.itextpdf.layout.Document;
import com.vfarma.Farmacia.DatosFarmacia.Efectivo;
import com.vfarma.Farmacia.DatosFarmacia.Factura;
import com.vfarma.Farmacia.DatosFarmacia.InformacionCliente;
import com.vfarma.Farmacia.DatosFarmacia.InformacionPersonaFisica;
import com.vfarma.Farmacia.DatosFarmacia.InformacionProducto;
import com.vfarma.Farmacia.DatosFarmacia.InformacionVenta;
import com.vfarma.Farmacia.DatosFarmacia.Pago;
import com.vfarma.Farmacia.DatosFarmacia.Recibo;
import com.vfarma.RegistroDatos.GestorCaja;
import com.vfarma.RegistroDatos.GestorProductos;

public class Cajero {

    private static Cajero cajero;
    private final InformacionVenta informacionVenta;
    private final GestorProductos gestorProductos;
    private final GestorCaja gestorCaja;
    private String nombreCaja;

    public Cajero() {
        this.informacionVenta = new InformacionVenta();
        this.gestorProductos = new GestorProductos();
        this.gestorCaja = new GestorCaja();
    }

    public static Cajero obtenerCajero() {
        if (cajero == null) {
            cajero = new Cajero();
        }
        return cajero;
    }

    public void realizarVentaConRecibo(float cantidadDineroRecibida) {
        cajero.seleccionarTipoCliente(new InformacionPersonaFisica());
        float cantidadAPagar = cajero.informacionVenta.obtenerMontoTotalVenta();

        Efectivo efectivo = new Efectivo();
        efectivo.colocarCantidadAPagar(cantidadAPagar);
        efectivo.colocarDineroRecibido(cantidadDineroRecibida);
        Pago pago = new Pago();
        pago.colocarMetodoPago(efectivo);

        cajero.informacionVenta.obtenerInformacionCliente().colocarPago(pago);

        cajero.informacionVenta.colocarComprobante(new Recibo(cajero.informacionVenta));

        String nombreActualCaja = cajero.obtenerNombreCaja();
        cajero.gestorCaja.desOcuparCaja(nombreActualCaja);

    }

    public void realizarVentaConFactura() {
        //Es factura, como tal no se necesita un pago
        Pago pago = new Pago();
        pago.colocarMetodoPago(new Efectivo());
        cajero.informacionVenta.obtenerInformacionCliente().colocarPago(pago);

        cajero.informacionVenta.colocarComprobante(new Factura(cajero.informacionVenta));
        cajero.calcularCambioVenta();

        String nombreCaja = cajero.obtenerNombreCaja();
        cajero.gestorCaja.desOcuparCaja(nombreCaja);
    }

    public void agregarProductoACarrito(int idProducto) {
        if (this.gestorProductos.contarExistenciaProducto(idProducto) == 0) {
            System.out.println("Producto no disponible");
            return;
        }
        InformacionProducto productoEncontrado = this.gestorProductos.buscarProductoPorID(idProducto);
        this.informacionVenta.obtenerCarritoCompras().agregarProducto(productoEncontrado);
    }

    public void retirarProductoDeCarrito(int idProducto) {
        InformacionProducto productoEncontrado = this.informacionVenta.obtenerCarritoCompras().buscarProductoPorIdDelProducto(idProducto);
        this.informacionVenta.obtenerCarritoCompras().removerProducto(productoEncontrado);
    }

    public void seleccionarTipoCliente(InformacionCliente tipoCliente) {
        this.informacionVenta.colocarInformacionCliente(tipoCliente);
    }

    private enum TipoPago {
        EFECTIVO
    }

    private void seleccionarTipoPagoCliente(TipoPago tipoPago) {
        switch (tipoPago) {
            case EFECTIVO -> {
                this.informacionVenta.obtenerInformacionCliente().obtenerPago().colocarMetodoPago(new Efectivo());
            }
            default ->
                System.out.println("Tipo de pago no válido");
        }

    }

    private float efectuarPago() {
        return this.informacionVenta.obtenerInformacionCliente().obtenerPago().obtenerMetodoPago().realizarPago();
    }

    public float calcularCambioVenta() {

        float cambioDelCliente = this.efectuarPago();
        this.informacionVenta.obtenerCarritoCompras().obtenerTodosProductos().forEach(producto -> {
            this.gestorProductos.restarExistenciaProducto(producto.obtenerClaveProducto(), 1);
        });

        return cambioDelCliente;
    }

    public void imprimirComprobante() {
        try {
            Document comprobanteLlenado = this.informacionVenta.obtenerComprobante().llenarInformacionComprobante();
            this.informacionVenta.obtenerComprobante().enviarAImpresion(comprobanteLlenado);
        } catch (FileNotFoundException e) {
        }
    }

    public void terminarVenta() {
        Cajero.cajero = null;
    }

    public GestorCaja obtenerGestorCaja() {
        return this.gestorCaja;
    }

    public InformacionVenta obtenerInformacionVenta() {
        return this.informacionVenta;
    }

    public GestorProductos obtenerGestorProductos() {
        return this.gestorProductos;
    }

    public String obtenerNombreCaja() {
        return this.nombreCaja;
    }

    public void colocarNombreCaja(String nombreCaja) {
        this.nombreCaja = nombreCaja;
    }
}
