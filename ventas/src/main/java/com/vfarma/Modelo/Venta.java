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

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public InformacionCliente getInformacionCliente() {
        return informacionCliente;
    }

    public void setInformacionCliente(InformacionCliente informacionCliente) {
        this.informacionCliente = informacionCliente;
    }
}
