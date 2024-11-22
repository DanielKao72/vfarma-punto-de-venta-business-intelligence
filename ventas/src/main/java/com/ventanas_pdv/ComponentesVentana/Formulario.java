package com.ventanas_pdv.ComponentesVentana;

import java.util.ArrayList;

public class Formulario {
    private ArrayList<InformacionCampoFormulario> campos;

    public Formulario() {
        this.campos = new ArrayList<>();
    }

    public void agregarCampo(InformacionCampoFormulario campo) {
        this.campos.add(campo);
    }

    public ArrayList<InformacionCampoFormulario> obtenerCampos() {
        return this.campos;
    }
}
