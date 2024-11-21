package com.vfarma.ComponentesVentana;

import javax.swing.border.Border;

public class InformacionBoton {
    private String textoBoton;
    private Border bordes;

    public InformacionBoton(String textoBoton, Border bordes) {
        this.textoBoton = textoBoton;
        this.bordes = bordes;
    }

    public String obtenerTextoBoton() {
        return this.textoBoton;
    }

    public Border obtenerBordes() {
        return this.bordes;
    }
}
