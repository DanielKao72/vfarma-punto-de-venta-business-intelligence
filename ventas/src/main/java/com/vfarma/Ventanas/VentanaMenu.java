package com.vfarma.Ventanas;

import java.util.ArrayList;

import javax.swing.JButton;

import com.vfarma.GestoresVentanas.GestorVentanaMenu;

public abstract class VentanaMenu extends Ventana {
    protected GestorVentanaMenu gestorVentanaMenu;

    public VentanaMenu(String tituloVentana) {
        super(tituloVentana);
        this.gestorVentanaMenu = new GestorVentanaMenu();
    }

    @Override
    public void iniciarVentana() {
        this.gestorVentanaMenu.configurarVentana(this.ventana, this.tituloVentana);
        this.gestorVentanaMenu.agregarOpcionesVentana(this.ventana);
        this.gestorVentanaMenu.agregarEncabezado(this.ventana, this.tituloVentana);
        this.gestorVentanaMenu.agregarContenido(this.ventana, this.crearOpcionesMenu());

        this.configurarEventos();
    }

    public abstract ArrayList<JButton> crearOpcionesMenu();
    
    public abstract void configurarEventos();
}
