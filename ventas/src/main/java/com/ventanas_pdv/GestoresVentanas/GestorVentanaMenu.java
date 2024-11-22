package com.ventanas_pdv.GestoresVentanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionPanel;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;

public class GestorVentanaMenu extends GestorVentana {

    public GestorVentanaMenu() {
        super();
    }

    @Override
    public void agregarOpcionesVentana(JFrame ventana) {
        // Tamaño de la pantalla del dispositivo
        int anchoPantalla = ventana.getToolkit().getScreenSize().width;
        int altoPantalla = ventana.getToolkit().getScreenSize().height;
        Color colorFondo = new Color(242, 242, 242);

        // Panel de Opciones de Ventana
        InformacionPanel informacionPanel = new InformacionPanel(new BorderLayout(), colorFondo, 0.1f);
        JPanel panel = GestorComponentes.crearPanel(informacionPanel, anchoPantalla, altoPantalla);

        // Boton Cerrar Sesión
        InformacionEstilosBoton estilosBotonCerrarSesion = new InformacionEstilosBoton(Color.RED, colorFondo, new ImageIcon("src/main/resources/iconos/cerrar_sesion.png"), JButton.LEFT, JButton.CENTER);
        InformacionBoton informacionBotonCerrarSesion = new InformacionBoton("Cerrar Sesión", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JButton botonCerrarSesion = GestorComponentes.crearBoton(informacionBotonCerrarSesion, estilosBotonCerrarSesion);

        // Agregar Botone al Mapa de Botones
        this.botones.put("CerrarSesion", botonCerrarSesion);

        // Agregar Botones al Panel
        panel.add(botonCerrarSesion, BorderLayout.EAST);

        // Agregar Panel a la Ventana
        ventana.add(panel, BorderLayout.NORTH);
    }

    public void agregarContenido(JFrame ventana, ArrayList<JButton> contenido) {
        int anchoPantalla = ventana.getToolkit().getScreenSize().width;
        int altoPantalla = ventana.getToolkit().getScreenSize().height;
        Color colorFondo = new Color(242, 242, 242);

        // Panel de Contenido
        InformacionPanel informacionPanel = new InformacionPanel(new FlowLayout(), colorFondo, 0.8f);
        JPanel panel = GestorComponentes.crearPanel(informacionPanel, anchoPantalla, altoPantalla);

        // Agregar Botones al Panel
        this.agregarBotonesPanel(panel, contenido);

        ventana.add(panel, BorderLayout.SOUTH);
    }

    private void agregarBotonesPanel(JPanel panel, ArrayList<JButton> botones) {
        for (JButton boton : botones) {
            panel.add(boton);
        }
    }
}
