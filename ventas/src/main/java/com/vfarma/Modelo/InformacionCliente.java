package com.vfarma.Modelo;

import java.util.Map;

public class InformacionCliente {
    public Map<String, String> domicilioCliente;
    public String claveRFCCliente;

    public InformacionCliente(Map<String, String> domicilioCliente, String claveRFCCliente) {
        this.domicilioCliente = domicilioCliente;
        this.claveRFCCliente = claveRFCCliente;
    }

    public void obtenerDatosCliente() {
        System.out.println("Domicilio del cliente: " + domicilioCliente);
        System.out.println("Clave RFC del cliente: " + claveRFCCliente);
    }

    public Map<String, String> getDomicilioCliente() {
        return domicilioCliente;
    }

    public void setDomicilioCliente(Map<String, String> domicilioCliente) {
        this.domicilioCliente = domicilioCliente;
    }

    public String getClaveRFCCliente() {
        return claveRFCCliente;
    }

    public void setClaveRFCCliente(String claveRFCCliente) {
        this.claveRFCCliente = claveRFCCliente;
    }

}
