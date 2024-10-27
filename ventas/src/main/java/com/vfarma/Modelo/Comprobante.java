package com.vfarma.Modelo;

public abstract class Comprobante {
    private InformacionFarmacia informacionFarmacia;

    public Comprobante() {
        this.informacionFarmacia = new InformacionFarmacia();
    }

    public abstract void llenarInformacionComprobante(InformacionVenta informacionVenta);

    public abstract void generarComprobante();

    public abstract void imprimir();

    
    

}
