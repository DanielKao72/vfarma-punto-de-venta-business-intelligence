package com.ventanas_pdv.Ventanas;

import com.ventanas_pdv.GestoresVentanas.GestorVentanaMenu;

public abstract class VentanaMenu extends Ventana implements IMenu {
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
}
