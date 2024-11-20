package com.vfarma.ComponentesVentana;

import java.awt.Color;
import java.awt.LayoutManager;

public class InformacionPanel {
    private LayoutManager tipoDisposicion;
    private Color colorFondo;
    private float alturaRelativa;

    public InformacionPanel(LayoutManager tipoDisposicion, Color colorFondo, float alturaRelativa) {
        this.tipoDisposicion = tipoDisposicion;
        this.colorFondo = colorFondo;
        this.alturaRelativa = alturaRelativa;
    }

    public LayoutManager obtenerTipoDisposicion() {
        return this.tipoDisposicion;
    }

    public Color obtenerColorFondo() {
        return this.colorFondo;
    }

    public float obtenerAlturaRelativa() {
        return this.alturaRelativa;
    }
}
