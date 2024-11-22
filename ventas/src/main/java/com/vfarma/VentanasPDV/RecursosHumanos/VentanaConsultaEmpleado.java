package com.vfarma.VentanasPDV.RecursosHumanos;

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
import com.vfarma.Farmacia.Modelo.InformacionEmpleado;
import com.vfarma.Farmacia.RecursosHumanos.FuncionarioRecursosHumanos;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaConsultaEmpleado extends VentanaFormulario {
    private JTextField campoUsuario;
    private JTable informacionEmpleado;
    private JScrollPane tablaInformacionEmpleado;
    private JButton botonConsultar;
    private JButton botonLimpiar;
    
    public VentanaConsultaEmpleado(String tituloVentana) {
        super(tituloVentana);
        this.formulario = new Formulario();
    }

    @Override
    public Formulario crearCamposFormulario() {
        this.campoUsuario = GestorFormulario.crearCampoTexto(20);
        this.informacionEmpleado = GestorFormulario.crearTabla(new String[] { "Clave de Empleado", "Nombre", "Apellidos", "Correo", "Teléfono", "Turno", "Rol" });
        this.tablaInformacionEmpleado = new JScrollPane(this.informacionEmpleado);

        this.formulario.agregarCampo(new InformacionCampoFormulario("Usuario del Empleado:", this.campoUsuario));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Información del Empleado:", this.tablaInformacionEmpleado));

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonEliminar = new InformacionBoton("Buscar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonConsultar = GestorComponentes.crearBoton(informacionBotonEliminar, estilosBoton);

        InformacionBoton informacionBotonCancelar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonConsultar);
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
            this.limpiarCamposFormulario();
        });

        this.botonConsultar.addActionListener(e -> {

            String usuario = campoUsuario.getText().trim();
            System.out.println(usuario);

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.obtenerFuncionarioRH();
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

    private void limpiarCamposFormulario() {
        campoUsuario.setText("");
        javax.swing.table.DefaultTableModel modeloTabla = (javax.swing.table.DefaultTableModel) informacionEmpleado.getModel();
        modeloTabla.setRowCount(0);
    }
}
