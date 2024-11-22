package com.ventanas_pdv.GestoresVentanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.util.HashMap;

import com.ventanas_pdv.ComponentesVentana.InformacionPanel;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;

public abstract class GestorVentana {
    protected HashMap<String, JButton> botones;

    public GestorVentana() {
        this.botones = new HashMap<String, JButton>();
    }

    public void configurarVentana(JFrame ventana, String tituloVentana) {
        ventana.setTitle(tituloVentana);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setUndecorated(true);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout(0, 0));
    }

    public void agregarEncabezado(JFrame ventana, String tituloVentana) {
        // Tamaño de la pantalla del dispositivo
        int anchoPantalla = ventana.getToolkit().getScreenSize().width;
        int altoPantalla = ventana.getToolkit().getScreenSize().height;
        Color colorFondo = new Color(66, 7, 124);
        JLabel titulo = new JLabel(tituloVentana, JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);

        // Panel de Encabezado
        InformacionPanel informacionPanel = new InformacionPanel(new BorderLayout(), colorFondo, 0.1f);
        JPanel panel = GestorComponentes.crearPanel(informacionPanel, anchoPantalla, altoPantalla);

        // Agregar Titulo a Panel
        panel.add(titulo);

        // Agregar Panel a la Ventana
        ventana.add(panel, BorderLayout.CENTER);
    }

    public JButton obtenerBoton(String nombreBoton) {
        return this.botones.get(nombreBoton);
    }

    public abstract void agregarOpcionesVentana(JFrame ventana);

}
