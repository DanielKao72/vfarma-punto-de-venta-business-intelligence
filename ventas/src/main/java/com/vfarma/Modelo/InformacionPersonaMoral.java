package com.vfarma.Modelo;

public class InformacionPersonaMoral extends InformacionCliente {

    private String razonSocial;
    private String regimenFiscal;

    public InformacionPersonaMoral(String razonSocial, String regimenFiscal, String domicilioCliente, String claveRFCCliente) {
        super(domicilioCliente, claveRFCCliente);
        this.razonSocial = razonSocial;
        this.regimenFiscal = regimenFiscal;
    }

    public String obtenerRazonSocial() {
        return this.razonSocial;
    }

    public String obtenerRegimenFiscal() {
        return this.regimenFiscal;
    }

    public void colocarRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void colocarRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }
}
