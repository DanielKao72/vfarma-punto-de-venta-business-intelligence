package com.ventanas_pdv.RH;

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
import com.vfarma.RegistroEmpleados.Controlador.FuncionarioRecursosHumanos;

public class VentanaEliminarEmpleado extends VentanaFormulario {
    private JTextField campoUsuarioEmpleado;
    private JButton botonEliminar;
    private JButton botonCancelar;

    public VentanaEliminarEmpleado(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();

        this.campoUsuarioEmpleado = GestorFormulario.crearCampoTexto(20);

        formulario.agregarCampo(
            new InformacionCampoFormulario("Usuario del Empleado:", this.campoUsuarioEmpleado)
        );

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonEliminar = new InformacionBoton("Eliminar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Cancelar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonEliminar = GestorComponentes.crearBoton(informacionBotonEliminar, estilosBoton);
        this.botonCancelar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonEliminar);
        botones.add(this.botonCancelar);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            this.cerrarVentana();
        });

        this.botonCancelar.addActionListener(e -> {
            limpiarCamposFormulario();
        });

        this.botonEliminar.addActionListener(e -> {

            String usuarioEmpleado = this.campoUsuarioEmpleado.getText().trim();

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.llamarFuncionarioRH(); 
            boolean empleadoEliminadoCorrectamente = funcionarioRH.eliminarEmpleado(usuarioEmpleado);

            if (empleadoEliminadoCorrectamente) {
                JOptionPane.showMessageDialog(null, 
                "Empleado eliminado exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, 
                "No se pudo eliminar al empleado.", 
                "Fracaso", 
                JOptionPane.ERROR_MESSAGE);
            }

            limpiarCamposFormulario();

        });
    }

    private void limpiarCamposFormulario() {
        this.campoUsuarioEmpleado.setText("");
    }
}
