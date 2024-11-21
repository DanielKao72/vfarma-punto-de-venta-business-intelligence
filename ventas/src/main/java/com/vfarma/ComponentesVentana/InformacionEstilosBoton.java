package com.vfarma.ComponentesVentana;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;

public class InformacionEstilosBoton {
    private Font fuenteTexto;
    private Color colorTexto;
    private Color colorFondo;
    private Icon icono;
    private int posicionVerticalTexto;
    private int posicionHorizontalTexto;


    public InformacionEstilosBoton(Color colorTexto, Color colorFondo, Icon icono, int posicionX, int posicionY) {
        this.fuenteTexto = new Font("SansSerif", Font.BOLD, 26);
        this.colorTexto = colorTexto;
        this.colorFondo = colorFondo;
        this.icono = icono;
        this.posicionHorizontalTexto = posicionX;
        this.posicionVerticalTexto = posicionY;
    }

    public InformacionEstilosBoton(Font fuenteTexto) {
        this();
        this.fuenteTexto = fuenteTexto;
    }

    public InformacionEstilosBoton(Color colorTexto, Color colorFondo) {
        this();
        this.colorTexto = colorTexto;
        this.colorFondo = colorFondo;
    }

    public InformacionEstilosBoton(Icon icono) {
        this();
        this.icono = icono;
    }

    public InformacionEstilosBoton() {
        this.fuenteTexto = new Font("SansSerif", Font.BOLD, 20);
        this.colorTexto = Color.BLACK;
        this.colorFondo = new Color(242, 242, 242);
        this.icono = null;
    }

    public Font obtenerFuenteTexto() {
        return this.fuenteTexto;
    }

    public Color obtenerColorTexto() {
        return this.colorTexto;
    }

    public Color obtenerColorFondo() {
        return this.colorFondo;
    }

    public Icon obtenerIcono() {
        return this.icono;
    }

    public int obtenerPosicionVerticalTexto() {
        return this.posicionVerticalTexto;
    }

    public int obtenerPosicionHorizontalTexto() {
        return this.posicionHorizontalTexto;
    }
}
