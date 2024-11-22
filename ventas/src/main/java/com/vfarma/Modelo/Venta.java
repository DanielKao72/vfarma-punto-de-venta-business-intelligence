package com.vfarma.Modelo;

public class Venta {

    private String claveVenta;
    private String fechaVenta;
    private float montoTotalVenta;
    private Comprobante comprobante;
    private CarritoCompras carritoCompras;
    private InformacionCliente informacionCliente;

    public String obtenerClaveVenta() {
        return claveVenta;
    }

    public String obtenerFechaVenta() {
        return fechaVenta;
    }

    public float obtenerMontoTotalVenta() {
        return montoTotalVenta;
    }

    public Comprobante obtenerComprobante() {
        return comprobante;
    }

    public void colocarComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public CarritoCompras colocarCarritoCompras() {
        return carritoCompras;
    }

    public InformacionCliente obtenerInformacionCliente() {
        return informacionCliente;
    }

    public void colocarInformacionCliente(InformacionCliente informacionCliente) {
        this.informacionCliente = informacionCliente;
    }
}
