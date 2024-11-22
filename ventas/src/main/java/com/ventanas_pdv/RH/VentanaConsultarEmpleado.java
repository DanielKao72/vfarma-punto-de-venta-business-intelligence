package com.ventanas_pdv.RH;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.Modelo.InformacionEmpleado;
import com.vfarma.RegistroEmpleados.Controlador.FuncionarioRecursosHumanos;

public class VentanaConsultarEmpleado extends VentanaFormulario {
    private JTextField campoUsuario;
    private JTable informacionEmpleado;
    private JScrollPane tablaInformacionEmpleado;
    private JButton botonConsultar;
    private JButton botonCancelar;
    
    public VentanaConsultarEmpleado(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();

        this.campoUsuario = GestorFormulario.crearCampoTexto(50);
        this.informacionEmpleado = GestorFormulario.crearTabla(new String[] { "Clave de Empleado", "Nombre", "Apellidos", "Correo", "Teléfono", "Turno", "Rol" });
        this.tablaInformacionEmpleado = new JScrollPane(this.informacionEmpleado);

        formulario.agregarCampo(new InformacionCampoFormulario("Usuario:", this.campoUsuario));
        formulario.agregarCampo(new InformacionCampoFormulario("Información del Empleado:", this.tablaInformacionEmpleado));

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonEliminar = new InformacionBoton("Buscar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonConsultar = GestorComponentes.crearBoton(informacionBotonEliminar, estilosBoton);

        InformacionBoton informacionBotonCancelar = new InformacionBoton("Cancelar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonCancelar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonConsultar);
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

        this.botonConsultar.addActionListener(e -> {

            String usuario = campoUsuario.getText().trim();
            System.out.println(usuario);

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.llamarFuncionarioRH();
            InformacionEmpleado empleado = funcionarioRH.consultarInformacionEmpleado(usuario);

            javax.swing.table.DefaultTableModel modeloTabla = (javax.swing.table.DefaultTableModel) informacionEmpleado.getModel();
            modeloTabla.setRowCount(0); 

            if (empleado == null) {
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Empleado no encontrado.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            } else {
                modeloTabla.addRow(new Object[] {
                    empleado.obtenerClaveEmpleado(),
                    empleado.obtenerNombreEmpleado(),
                    empleado.obtenerApellidoEmpleado(),
                    empleado.obtenerCorreoEmpleado(),
                    empleado.obtenerTelefonoEmpleado(),
                    empleado.obtenerTurnoEmpleado(),
                    empleado.obtenerRolEmpleado()
                });
            }
        });
    }

    private void limpiarCamposFormulario(){
        campoUsuario.setText("");
    }
}
