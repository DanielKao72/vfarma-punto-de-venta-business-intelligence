package com.vfarma.Modelo;

public class InformacionCaja {

    private String claveCaja;
    private String encargadoCaja;
    private String balanceCaja;


    public String obtenerClaveCaja() {
        return claveCaja;
    }

    public String obtenerEncargadoCaja() {
        return encargadoCaja;
    }

    public String obtenerBalanceCaja() {
        return balanceCaja;
    }

    public void colocarEncargadoCaja(String encargadoCaja) {
        this.encargadoCaja = encargadoCaja;
    }

    public void colocarBalanceCaja(String balanceCaja) {
        this.balanceCaja = balanceCaja;
    }
}
