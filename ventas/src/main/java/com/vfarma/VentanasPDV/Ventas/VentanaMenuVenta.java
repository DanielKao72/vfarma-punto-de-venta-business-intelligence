package com.vfarma.VentanasPDV.Ventas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.Ventanas.VentanaMenu;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaMenuVenta extends VentanaMenu {
    private JButton botonIniciarVenta;
    
    public VentanaMenuVenta(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public ArrayList<JButton> crearOpcionesMenu() {
        ArrayList<JButton> opcionesMenu = new ArrayList<JButton>();
        Color colorFondoBoton = new Color(0, 44, 240);
        ImageIcon icono = new ImageIcon("src/main/resources/iconos/ventas.png");

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, colorFondoBoton, icono, JButton.CENTER, JButton.BOTTOM);
        InformacionBoton informacionBoton = new InformacionBoton("Iniciar Venta", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonIniciarVenta = GestorComponentes.crearBoton(informacionBoton, estilosBoton);

        opcionesMenu.add(this.botonIniciarVenta);

        return opcionesMenu;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaMenu.obtenerBoton("CerrarSesion").addActionListener(e -> {
            VentanaControlAcceso ventana = new VentanaControlAcceso("Control de Acceso");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonIniciarVenta.addActionListener(e -> {
            VentanaSeleccionCaja ventana = new VentanaSeleccionCaja("Selección Caja");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });
    }
}
