package com.vfarma.Ventanas;

import com.vfarma.GestoresVentanas.GestorVentanaFormulario;

public abstract class VentanaFormulario extends Ventana implements IFormulario {

    protected GestorVentanaFormulario gestorVentanaFormulario;

    public VentanaFormulario(String tituloVentana) {
        super(tituloVentana);
        this.gestorVentanaFormulario = new GestorVentanaFormulario();
    }

    @Override
    public void iniciarVentana() {
        this.gestorVentanaFormulario.configurarVentana(this.ventana, this.tituloVentana);
        this.gestorVentanaFormulario.agregarOpcionesVentana(this.ventana);
        this.gestorVentanaFormulario.agregarEncabezado(this.ventana, this.tituloVentana);
        this.gestorVentanaFormulario.agregarContenido(this.ventana, this.crearCamposFormulario(), this.crearBotones());

        this.configurarEventos();
    }

}
