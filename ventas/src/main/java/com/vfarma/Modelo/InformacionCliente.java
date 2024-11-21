package com.vfarma.Modelo;

public class InformacionCliente {

    private String domicilioCliente;
    private String claveRFCCliente;
    private Pago pago;


    public InformacionCliente() {
        this.domicilioCliente = "Domicilio de la farmacia (InformacionCliente)";
        this.claveRFCCliente = "RFC de la farmacia (InformacionCliente)";
    }

    public InformacionCliente obtenerDatosCliente() {
        return this;
    }



    public String obtenerDomicilioCliente() {
        return domicilioCliente;
    }

    public void colocarDomicilioCliente(String domicilioCliente) {
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
