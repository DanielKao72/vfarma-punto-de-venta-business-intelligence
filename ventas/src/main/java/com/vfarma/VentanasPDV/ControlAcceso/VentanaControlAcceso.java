package com.vfarma.VentanasPDV.ControlAcceso;

import java.util.ArrayList;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.Farmacia.ControlAcceso.Empleado;
import com.vfarma.VentanasPDV.Inventario.VentanaMenuInventario;
import com.vfarma.VentanasPDV.RecursosHumanos.VentanaMenuRH;
import com.vfarma.VentanasPDV.Ventas.VentanaMenuVenta;

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

        this.botonIniciarSesion.addActionListener(e -> {
            Empleado empleado = Empleado.obtenerEmpleado();

            String rolEmpleado = empleado.iniciarSesion(this.campoUsuario.getText(), this.campoContrasena.getText());

            switch (rolEmpleado) {
                case "Cajero":
                    VentanaMenuVenta ventanaVenta = new VentanaMenuVenta("Menú Ventas");
                    ventanaVenta.iniciarVentana();
                    ventanaVenta.mostrarVentana();
                    break;
                case "Almacén":
                    VentanaMenuInventario ventanaInventario = new VentanaMenuInventario("Menú Inventario");
                    ventanaInventario.iniciarVentana();
                    ventanaInventario.mostrarVentana();
                    break;
                case "Recursos Humanos":
                    VentanaMenuRH ventanaRH = new VentanaMenuRH("Menú Recursos Humanos");
                    ventanaRH.iniciarVentana();
                    ventanaRH.mostrarVentana();
                    this.cerrarVentana();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });
    }
}
