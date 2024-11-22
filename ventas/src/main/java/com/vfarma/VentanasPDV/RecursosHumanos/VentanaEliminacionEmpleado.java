package com.vfarma.VentanasPDV.RecursosHumanos;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.Farmacia.RecursosHumanos.FuncionarioRecursosHumanos;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaEliminacionEmpleado extends VentanaFormulario {
    private JTextField campoUsuarioEmpleado;
    private JButton botonEliminar;
    private JButton botonLimpiar;

    public VentanaEliminacionEmpleado(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        this.formulario = new Formulario();
        this.campoUsuarioEmpleado = GestorFormulario.crearCampoTexto(20);

        this.formulario.agregarCampo(new InformacionCampoFormulario("Clave del Empleado:", this.campoUsuarioEmpleado));

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonEliminar = new InformacionBoton("Eliminar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Cancelar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonEliminar = GestorComponentes.crearBoton(informacionBotonEliminar, estilosBoton);
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonEliminar);
        botones.add(this.botonLimpiar);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            VentanaControlAcceso ventanaControlAcceso = new VentanaControlAcceso("Control de Acceso");
            ventanaControlAcceso.iniciarVentana();
            ventanaControlAcceso.mostrarVentana();
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            VentanaMenuRH ventana = new VentanaMenuRH("Menú Recursos Humanos");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonLimpiar.addActionListener(e -> {
            this.campoUsuarioEmpleado.setText("");
        });


        this.botonEliminar.addActionListener(e -> {

            String usuarioEmpleado = this.campoUsuarioEmpleado.getText().trim();

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.obtenerFuncionarioRH(); 
            boolean empleadoEliminadoCorrectamente = funcionarioRH.eliminarEmpleado(usuarioEmpleado);

            if (empleadoEliminadoCorrectamente) {
                JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar al empleado.", "Fracaso", JOptionPane.ERROR_MESSAGE);
            }

            this.limpiarCamposFormulario();
        });
    }

    private void limpiarCamposFormulario() {
        this.campoUsuarioEmpleado.setText("");
    }
}
