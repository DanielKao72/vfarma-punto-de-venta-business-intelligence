package com.vfarma.VentanasPDV.ControlAcceso;

import java.util.ArrayList;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;

public class VentanaControlAcceso extends VentanaFormulario {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;

    public VentanaControlAcceso(String titulo) {
        super(titulo);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();

        this.campoUsuario = GestorFormulario.crearCampoTexto(50);
        this.campoContrasena = GestorFormulario.crearCampoContrasena(50);

        formulario.agregarCampo(new InformacionCampoFormulario("Usuario:", this.campoUsuario));
        formulario.agregarCampo(new InformacionCampoFormulario("Contraseña:", this.campoContrasena));

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<JButton>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBoton = new InformacionBoton("Iniciar Sesión", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonIniciarSesion = GestorComponentes.crearBoton(informacionBoton, estilosBoton);

        botones.add(this.botonIniciarSesion);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").setVisible(false);
    }
}
