package com.vfarma.Modelo;

public class PersonaMoral extends InformacionCliente{
    public String razonSocial;
    public String regimenFiscal;

    public PersonaMoral(String razonSocial, String regimenFiscal) {
        this.razonSocial = razonSocial;
        this.regimenFiscal = regimenFiscal;
    }

    public String obtenerRazonSocial() {
        return this.razonSocial;
    }

    public String obtenerRegimenFiscal() {
        return this.regimenFiscal;
    }

    public void establecerRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void establecerRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }
}
