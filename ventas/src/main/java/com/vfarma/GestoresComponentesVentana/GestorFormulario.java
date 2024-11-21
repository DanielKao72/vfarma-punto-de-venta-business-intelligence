package com.vfarma.GestoresComponentesVentana;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GestorFormulario {
    
    public static JTextField crearCampoTexto(int longitud) {
        JTextField campoTexto = new JTextField(longitud);
        campoTexto.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));

        return campoTexto;
    }

    public static JComboBox<String> crearListaOpciones() {
        JComboBox<String> listaOpciones = new JComboBox<String>();
        listaOpciones.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));

        return listaOpciones;
    }

    public static JComboBox<String> creaListaOpciones(String[] opciones) {
        JComboBox<String> campoComboBox = new JComboBox<String>(opciones);
        campoComboBox.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));

        return campoComboBox;
    }

    public static JTable crearTabla(String[] encabezado) {
        DefaultTableModel modelo = new DefaultTableModel(encabezado, 0);
        JTable tabla = new JTable(modelo);
        tabla.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));

        return tabla;
    }

    public static JRadioButton crearOpcionMultiple(String opcion) {
        JRadioButton boton = new JRadioButton(opcion);
        boton.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));
        
        return boton;
    }

    public static ButtonGroup crearGrupoBotones(ArrayList<JRadioButton> opciones) {
        ButtonGroup grupoBotones = new ButtonGroup();

        opciones.get(0).setSelected(true);

        for (JRadioButton opcion : opciones) {
            grupoBotones.add(opcion);
        }

        return grupoBotones;
    }
}
