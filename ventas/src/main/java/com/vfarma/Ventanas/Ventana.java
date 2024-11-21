package com.vfarma.Ventanas;

import javax.swing.JFrame;

public abstract class Ventana {
    protected JFrame ventana;
    protected String tituloVentana;

    public Ventana(String tituloVentana) {
        this.ventana = new JFrame();
        this.tituloVentana = tituloVentana;
    }

    public void mostrarVentana() {
        this.ventana.setVisible(true);
    }

    public void ocultarVentana() {
        this.ventana.setVisible(false);
    }

    public void cerrarVentana() {
        this.ventana.dispose();
    }

    public abstract void iniciarVentana();
}