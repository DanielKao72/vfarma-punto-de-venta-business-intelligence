package com.vfarma.Modelo;

public class PersonaFisica {

    public String nombreCliente;
    public String apellidosCliente;

    public PersonaFisica(String nombreCliente, String apellidosCliente) {
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
