package com.vfarma.Modelo;

public class InformacionCliente {
    private String domicilioCliente;
    private String claveRFCCliente;
    private Pago pago;

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

    public String obtenerClaveRFCCliente() {
        return claveRFCCliente;
    }

    public void colocarClaveRFCCliente(String claveRFCCliente) {
        this.claveRFCCliente = claveRFCCliente;
    }

    public Pago obtenerPago() {
        return pago;
    }

    public void colocarPago(Pago pago) {
        this.pago = pago;
    }

}
