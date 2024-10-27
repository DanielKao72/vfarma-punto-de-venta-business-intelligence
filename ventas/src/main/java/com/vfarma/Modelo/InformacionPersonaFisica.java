package com.vfarma.Modelo;

public class InformacionPersonaFisica extends InformacionCliente{

    private String nombreCliente;
    private String apellidosCliente;

    public InformacionPersonaFisica(String nombreCliente, String apellidosCliente) {
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
