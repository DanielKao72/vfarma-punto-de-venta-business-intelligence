package com.vfarma.Modelo;

public class InformacionPersonaFisica extends InformacionCliente {

    private String nombreCliente;
    private String apellidosCliente;

    public InformacionPersonaFisica() {
        super();
        this.nombreCliente = "Vfarma";
        this.apellidosCliente = "S.A. de C.V.";
    }

    public InformacionPersonaFisica(String nombreCliente, String apellidosCliente, String domicilioCliente, String claveRFCCliente) {
        super(domicilioCliente, claveRFCCliente);
        this.nombreCliente = nombreCliente;
        this.apellidosCliente = apellidosCliente;
        
    }

    public String obtenerNombreCliente() {
        return nombreCliente;
    }

    public void colocarNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String obtenerApellidosCliente() {
        return apellidosCliente;
    }

    public void colocarApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

}
