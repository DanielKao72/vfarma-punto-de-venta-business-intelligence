package com.vfarma.Ventanas;

import java.util.ArrayList;

import javax.swing.JButton;

import com.vfarma.ComponentesVentana.Formulario;

public interface IFormulario {

    public Formulario crearCamposFormulario();

    public ArrayList<JButton> crearBotones();

    public void configurarEventos();
}
