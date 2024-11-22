package com.ventanas_pdv.Ventanas;

import java.util.ArrayList;

import javax.swing.JButton;

import com.ventanas_pdv.ComponentesVentana.Formulario;

public interface IFormulario {
    public Formulario crearCamposFormulario();
    public ArrayList<JButton> crearBotones();
    public void configurarEventos();
}
