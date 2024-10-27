package com.vfarma.Modelo;

import java.time.LocalDate;
import java.util.Random;

public class InformacionVenta {

    private final String claveVenta;
    private final String fechaVenta;
    private Float montoTotalVenta;
    public InformacionCliente informacionCliente;
    public Comprobante comprobante;
    public CarritoCompras carritoCompras;

    public InformacionVenta() {
        this.claveVenta = this.generarClaveVenta();
        this.fechaVenta = this.obtenerFechaActual();
        this.montoTotalVenta = 0.0f;
        this.carritoCompras = new CarritoCompras();
    }

    public String getClaveVenta() {
        return claveVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public Float getMontoTotalVenta() {
        return montoTotalVenta;
    }

    public void setMontoTotalVenta(Float montoTotalVenta) {
        this.montoTotalVenta = montoTotalVenta;
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

}
