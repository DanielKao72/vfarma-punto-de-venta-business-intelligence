package com.vfarma.Modelo;

public class InformacionVenta {

    private String claveVenta;
    private String fechaVenta;
    private Float montoTotalVenta; 
    public InformacionCliente informacionCliente;
    public Comprobante comprobante;
    public CarritoCompras carritoCompras;

    public String getClaveVenta() {
        return claveVenta;
    }

    public void setClaveVenta(String claveVenta) {
        this.claveVenta = claveVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Float getMontoTotalVenta() {
        return montoTotalVenta;
    }

    public void setMontoTotalVenta(Float montoTotalVenta) {
        this.montoTotalVenta = montoTotalVenta;
    }
    
}
