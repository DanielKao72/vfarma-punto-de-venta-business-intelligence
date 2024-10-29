package com.vfarma.Modelo;

public class InformacionEmpleado {

    private String claveEmpleado;
    private String nombreEmpleado;
    private String sucursalFarmacia;
    private String turno;
    private InformacionCaja informacionCaja;

    public String obtenerClaveEmpleado() {
        return claveEmpleado;
    }

    public void colocarClaveEmpleado(String claveEmpleado) {
        this.claveEmpleado = claveEmpleado;
    }

    public String obtenerNombreEmpleado() {
        return nombreEmpleado;
    }

    public void colocarNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String obtenerSucursalFarmacia() {
        return sucursalFarmacia;
    }

    public void colocarSucursalFarmacia(String sucursalFarmacia) {
        this.sucursalFarmacia = sucursalFarmacia;
    }

    public String obtenerTurno() {
        return turno;
    }

    public void colocarTurno(String turno) {
        this.turno = turno;
    }

    public InformacionCaja obtenerInformacionCaja() {
        return informacionCaja;
    }

    public void colocarInformacionCaja(InformacionCaja informacionCaja) {
        this.informacionCaja = informacionCaja;
    }

}
