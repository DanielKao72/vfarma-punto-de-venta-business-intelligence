package com.vfarma.Modelo;

public class Venta {
    private String claveVenta;
    private String fechaVenta;
    private float montoTotalVenta;
    public Comprobante comprobante;
    public CarritoCompras carritoCompras;
    public InformacionCliente informacionCliente;

    public String obtenerClaveVenta() {
        return claveVenta;
    }

    public String obtenerFechaVenta() {
        return fechaVenta;
    }

    public float obtenerMontoTotalVenta() {
        return montoTotalVenta;
    }
}
