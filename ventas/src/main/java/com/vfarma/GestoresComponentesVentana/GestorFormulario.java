package com.vfarma.GestoresComponentesVentana;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;

public class GestorFormulario {
    
    public static JTextField crearCampoTexto(int longitud) {
        JTextField campoTexto = new JTextField(longitud);
        campoTexto.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));

        return campoTexto;
    }

    public static JSpinner crearCampoNumero() {
        SpinnerNumberModel modelo = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner campoNumero = new JSpinner(modelo);
        campoNumero.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));

        return campoNumero;
    }

    public static JPasswordField crearCampoContrasena(int longitud) {
        JPasswordField campoContrasena = new JPasswordField(longitud);
        campoContrasena.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 26));

        return campoContrasena;
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
        tabla.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
        tabla.setFillsViewportHeight(true);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tabla.setPreferredScrollableViewportSize(new java.awt.Dimension(400, 150));
        tabla.setFillsViewportHeight(true);

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

    public static boolean esFormularioCorrecto(Formulario formulario) {
        for (InformacionCampoFormulario campo : formulario.obtenerCampos()) {
            Object componente = campo.obtenerComponenteCampo();
    
            if (componente instanceof JTextField) {
                if (((JTextField) componente).getText().isEmpty()) {
                    return false; // Campo de texto vacío
                }
            }
    
            if (componente instanceof JSpinner) {
                if (((JSpinner) componente).getValue().equals(0)) {
                    return false; // Campo numérico no válido
                }
            }
    
            if (componente instanceof JComboBox) {
                if (((JComboBox<?>) componente).getSelectedItem() == null) {
                    return false; // Lista de opciones sin eleccion
                }
            }
    
            if (componente instanceof JPasswordField) {
                if (((JPasswordField) componente).getPassword().length == 0) {
                    return false; // Contraseña vacía
                }
            }
    
            if (componente instanceof JTable) {
                if (((JTable) componente).getRowCount() == 0) {
                    return false; // Tabla sin filas
                }
            }
        }
    
        return true; // Todos los campos son válidos
    }

    public static boolean esNumeroEntero(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false; // Cadena vacía o nula
        }
        try {
            Integer.parseInt(cadena);
            return true; // Es un entero
        } catch (NumberFormatException e) {
            return false; // No es un número entero
        }
    }
    
}
