package com.vfarma.ControlAcceso;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionBoton;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;
import com.vfarma.ComponentesVentana.InformacionEstilosBoton;
import com.vfarma.GestoresComponentesVentana.GestorComponentes;
import com.vfarma.GestoresComponentesVentana.GestorFormulario;
import com.vfarma.Ventanas.VentanaFormulario;

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
