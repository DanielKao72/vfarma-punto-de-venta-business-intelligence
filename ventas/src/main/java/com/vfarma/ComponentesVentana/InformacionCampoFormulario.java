package com.vfarma.ComponentesVentana;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformacionCampoFormulario {
    private JLabel etiqueta;
    private JComponent componente;

    public InformacionCampoFormulario(String textoEtiqueta, JComponent componente) {
        this.etiqueta = new JLabel(textoEtiqueta);
        this.componente = componente;
        configurarEstilosEtiqueta();
        configurarEstilosComponente();
    }

    public InformacionCampoFormulario(String etiquetaCampo, ButtonGroup grupoBotones) {
        this.etiqueta= new JLabel(etiquetaCampo);
        this.componente = new JPanel(new FlowLayout());

        var elementos = grupoBotones.getElements();
        while (elementos.hasMoreElements()) {
            AbstractButton boton = elementos.nextElement();
            ((JPanel) this.componente).add(boton);
        }

        configurarEstilosEtiqueta();
        configurarEstilosComponente();
    }

    public JLabel obtenerEtiquetaCampo() {
        return this.etiqueta;
    }

    public JComponent obtenerComponenteCampo() {
        return this.componente;
    }

    private void configurarEstilosEtiqueta() {
        this.etiqueta.setFont(new Font("SansSerif", Font.BOLD, 26));
    }

    public void configurarEstilosComponente() {
        this.componente.setFont(new Font("SansSerif", Font.PLAIN, 20));
    }
}
