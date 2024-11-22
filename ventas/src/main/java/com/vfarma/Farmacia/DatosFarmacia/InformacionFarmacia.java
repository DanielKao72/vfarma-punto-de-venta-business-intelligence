package com.vfarma.Farmacia.DatosFarmacia;

public class InformacionFarmacia {

    private String claveRFCFarmacia;
    private String nombreFarmacia;
    private String domicilioSucursalFarmacia;
    private Comprobante comprobante;

    public InformacionFarmacia() {
        this.claveRFCFarmacia = " XLR8";
        this.nombreFarmacia = "VFarma";
        this.domicilioSucursalFarmacia = "Calle 1, Colonia 2, Ciudad 3, Estado 4";
    }

    public String obtenerClaveRFCFarmacia() {
        return claveRFCFarmacia;
    }

    public void colocarClaveRFCFarmacia(String claveRFCFarmacia) {
        this.claveRFCFarmacia = claveRFCFarmacia;
    }

    public String obtenerNombreFarmacia() {
        return nombreFarmacia;
    }

    public void colocarNombreFarmacia(String nombreFarmacia) {
        this.nombreFarmacia = nombreFarmacia;
    }

    public String obtenerDomicilioSucursalFarmacia() {
        return domicilioSucursalFarmacia;
    }

    public void colocarDomicilioSucursalFarmacia(String domicilioSucursalFarmacia) {
        this.domicilioSucursalFarmacia = domicilioSucursalFarmacia;
    }

    public Comprobante obtenerComprobante() {
        return this.comprobante;
    }

    public void colocarComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

}
