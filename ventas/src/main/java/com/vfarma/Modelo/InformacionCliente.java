package com.vfarma.Modelo;

public class InformacionCliente {
    private String domicilioCliente;
    private String claveRFCCliente;
    public Pago pago;

    public InformacionCliente(){

    }

    public void obtenerDatosCliente() {
        System.out.println("Domicilio del cliente: " + domicilioCliente);
        System.out.println("Clave RFC del cliente: " + claveRFCCliente);
    }

    public String getDomicilioCliente() {
        return domicilioCliente;
    }

    public void setDomicilioCliente(String domicilioCliente) {
        this.domicilioCliente = domicilioCliente;
    }

    public String getClaveRFCCliente() {
        return claveRFCCliente;
    }

    public void setClaveRFCCliente(String claveRFCCliente) {
        this.claveRFCCliente = claveRFCCliente;
    }

}
