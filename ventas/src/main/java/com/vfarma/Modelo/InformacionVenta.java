package com.vfarma.Modelo;

import java.time.LocalDate;
import java.util.Random;

public class InformacionVenta {

    private final String claveVenta;
    private final String fechaVenta;
    //private float montoTotalVenta;
    private InformacionCliente informacionCliente;
    private Comprobante comprobante;
    private CarritoCompras carritoCompras;
    private InformacionEmpleado informacionEmpleado;

    public InformacionVenta() {
        this.claveVenta = this.generarClaveVenta();
        this.fechaVenta = this.obtenerFechaActual();
        this.carritoCompras = new CarritoCompras();
        this.informacionCliente = new InformacionCliente();
    }

    public String obtenerClaveVenta() {
        return claveVenta;
    }

    public String obtenerFechaVenta() {
        return fechaVenta;
    }

    public float obtenerMontoTotalVenta() {
        float montoTotalVenta = 0.0f;

        if (this.carritoCompras != null) {
            for (Producto producto : this.carritoCompras.obtenerTodosProductos()) {
                if (producto != null) { 
                    montoTotalVenta += producto.obtenerPrecioProducto();
                }
            }
        }

        return montoTotalVenta;
    }

    private String generarClaveVenta() {
        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random RANDOM = new Random();
        int longitudClaveVentaAleatoria = 8;

        StringBuilder cadena = new StringBuilder(longitudClaveVentaAleatoria);
        for (int i = 0; i < longitudClaveVentaAleatoria; i++) {
            int index = RANDOM.nextInt(CARACTERES.length());
            cadena.append(CARACTERES.charAt(index));
        }
        return cadena.toString();
    }

    private String obtenerFechaActual() {
        LocalDate fechaHoy = LocalDate.now();

        int dia = fechaHoy.getDayOfMonth();
        int mes = fechaHoy.getMonthValue();
        int anio = fechaHoy.getYear();
        return dia + "/" + mes + "/" + anio;
    }

    public Comprobante obtenerComprobante() {
        return comprobante;
    }

    public void colocarComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public CarritoCompras obtenerCarritoCompras() {
        return carritoCompras;
    }

    public void colocarCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public InformacionCliente obtenerInformacionCliente() {
        return informacionCliente;
    }

    public void colocarInformacionCliente(InformacionCliente informacionCliente) {
        this.informacionCliente = informacionCliente;
    }

    public InformacionEmpleado obtenerInformacionEmpleado() {
        return this.informacionEmpleado;
    }

    public void colocarInformacionEmpleado(InformacionEmpleado informacionEmpleado) {
        this.informacionEmpleado = informacionEmpleado;
    }
}
