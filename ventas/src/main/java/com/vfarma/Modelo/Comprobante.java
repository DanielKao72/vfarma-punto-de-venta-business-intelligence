package com.vfarma.Modelo;

public class Comprobante {
    public String claveRFCFarmacia;
    public String nombreFarmacia;
    public String domicilioSucursalFarmacia;


    public void llenarInformacionComprobante(){
        
    }

    public void generarComprobante(){
        
    }

    public void imprimir(){
        
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

    

}
