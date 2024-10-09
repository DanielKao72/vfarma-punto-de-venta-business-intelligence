package com.vfarma.Modelo;

public class Venta {
    public String claveVenta;
    public String fechaVenta;
    public float montoTotalVenta;
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
