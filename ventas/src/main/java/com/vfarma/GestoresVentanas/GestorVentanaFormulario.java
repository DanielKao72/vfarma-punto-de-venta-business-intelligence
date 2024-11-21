package com.vfarma.GestoresVentanas;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.util.ArrayList;

import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionBoton;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;
import com.vfarma.ComponentesVentana.InformacionEstilosBoton;
import com.vfarma.ComponentesVentana.InformacionPanel;
import com.vfarma.GestoresComponentesVentana.GestorComponentes;

public class GestorVentanaFormulario extends GestorVentana {
    
    public GestorVentanaFormulario() {
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

        // Boton Volver Atrás
        InformacionEstilosBoton estilosBotonVolver = new InformacionEstilosBoton();
        InformacionBoton informacionBotonVolver = new InformacionBoton("Volver", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JButton botonVolver = GestorComponentes.crearBoton(informacionBotonVolver, estilosBotonVolver);

        // Boton Cerrar Sesión
        InformacionEstilosBoton estilosBotonCerrarSesion = new InformacionEstilosBoton(Color.RED, colorFondo, new ImageIcon("src/main/resources/iconos/cerrar_sesion.png"), JButton.LEFT, JButton.CENTER);
        InformacionBoton informacionBotonCerrarSesion = new InformacionBoton("Cerrar Sesión", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JButton botonCerrarSesion = GestorComponentes.crearBoton(informacionBotonCerrarSesion, estilosBotonCerrarSesion);

        // Agregar Botones al Mapa de Botones
        this.botones.put("Volver", botonVolver);
        this.botones.put("CerrarSesion", botonCerrarSesion);

        // Agregar Botones al Panel
        panel.add(botonVolver, BorderLayout.WEST);
        panel.add(botonCerrarSesion, BorderLayout.EAST);

        // Agregar Panel a la Ventana
        ventana.add(panel, BorderLayout.NORTH);
    }

    public void agregarContenido(JFrame ventana, Formulario formulario, ArrayList<JButton> botones) {
        int anchoPantalla = ventana.getToolkit().getScreenSize().width;
        int altoPantalla = ventana.getToolkit().getScreenSize().height;
        Color colorFondo = new Color(242, 242, 242);
    
        // Panel de Contenido con GridBagLayout
        InformacionPanel informacionPanel = new InformacionPanel(new GridBagLayout(), colorFondo, 0.8f);
        JPanel panel = GestorComponentes.crearPanel(informacionPanel, anchoPantalla, altoPantalla);
        GridBagConstraints restriccionesDiseno = crearRestriccionesDiseno();
    
        // Agregar Campos del Formulario
        int fila = 0;
        for (InformacionCampoFormulario campo : formulario.obtenerCampos()) {
            // Etiqueta
            restriccionesDiseno.gridx = 0; // Primera columna
            restriccionesDiseno.gridy = fila;
            restriccionesDiseno.weightx = 0.2; // Ocupa menos espacio que el componente
            restriccionesDiseno.fill = GridBagConstraints.HORIZONTAL;
            panel.add(campo.obtenerEtiquetaCampo(), restriccionesDiseno);
    
            // Componente
            restriccionesDiseno.gridx = 1; // Segunda columna
            restriccionesDiseno.weightx = 0.8; // Ocupa más espacio
            panel.add(campo.obtenerComponenteCampo(), restriccionesDiseno);
    
            fila++; // Siguiente fila
        }
    
        // Agregar Botones
        restriccionesDiseno.gridy = fila; // Continuar en la fila siguiente
        restriccionesDiseno.gridx = 0; // Reiniciar columna
        restriccionesDiseno.gridwidth = 2; // Botones ocupan ambas columnas
        restriccionesDiseno.weightx = 1.0;
        restriccionesDiseno.fill = GridBagConstraints.NONE; // No llenar espacio
        restriccionesDiseno.anchor = GridBagConstraints.CENTER; // Centrar botones
    
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        for (JButton boton : botones) {
            panelBotones.add(boton);
        }
        panel.add(panelBotones, restriccionesDiseno);
        // Agregar Panel Principal a la Ventana
        ventana.add(panel, BorderLayout.CENTER);
    }
    

    private GridBagConstraints crearRestriccionesDiseno() {
        GridBagConstraints restriccionesDiseno = new GridBagConstraints();
        restriccionesDiseno.insets = new Insets(10, 10, 10, 10);
        restriccionesDiseno.fill = GridBagConstraints.HORIZONTAL;

        return restriccionesDiseno;
    }
}
