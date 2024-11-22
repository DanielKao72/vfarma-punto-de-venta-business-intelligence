package com.vfarma.VentanasPDV.RecursosHumanos;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
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

public class VentanaRegistroEmpleado extends VentanaFormulario {
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JComboBox<String> tipoRol;
    private ButtonGroup sexo;
    private JRadioButton opcionMasculino;
    private JRadioButton opcionFemenino;
    private ButtonGroup turno;
    private JRadioButton opcionMatutino;
    private JRadioButton opcionVespertino;
    private JButton botonGuardar;
    private JButton botonLimpiar;

    public VentanaRegistroEmpleado(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        this.formulario = new Formulario();
        ArrayList<JRadioButton> opcionesSexo = new ArrayList<>();
        ArrayList<JRadioButton> opcionesTurno = new ArrayList<>();

        this.opcionMasculino = GestorFormulario.crearOpcionMultiple("Masculino");
        this.opcionFemenino = GestorFormulario.crearOpcionMultiple("Femenino");
        opcionesSexo.add(this.opcionMasculino);
        opcionesSexo.add(this.opcionFemenino);

        this.sexo = GestorFormulario.crearCampoOpcionMultiple(opcionesSexo);

        this.opcionMatutino = GestorFormulario.crearOpcionMultiple("Matutino");
        this.opcionVespertino = GestorFormulario.crearOpcionMultiple("Vespertino");
        opcionesTurno.add(this.opcionMatutino);
        opcionesTurno.add(this.opcionVespertino);

        this.turno = GestorFormulario.crearCampoOpcionMultiple(opcionesTurno);

        this.campoNombre = GestorFormulario.crearCampoTexto(50);
        this.campoApellido = GestorFormulario.crearCampoTexto(50);
        this.campoCorreo = GestorFormulario.crearCampoTexto(50);
        this.campoTelefono = GestorFormulario.crearCampoTexto(10);
        this.tipoRol = GestorFormulario.creaListaOpciones(new String[] { "Gerente", "Cajero", "Almacén", "Recursos Humanos" });

        this.formulario.agregarCampo(new InformacionCampoFormulario("Nombre:", this.campoNombre));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Apellido:", this.campoApellido));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Correo:", this.campoCorreo));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Teléfono:", this.campoTelefono));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Rol:", this.tipoRol));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Sexo:", this.sexo));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Turno:", this.turno));

        return this.formulario;
    }
    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonGuardar = new InformacionBoton("Finalizar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonGuardar = GestorComponentes.crearBoton(informacionBotonGuardar, estilosBoton);
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonLimpiar);
        botones.add(this.botonGuardar);

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
        

        this.botonGuardar.addActionListener(e -> {
            String nombre = this.campoNombre.getText();
            String apellido = this.campoApellido.getText();
            String correo = this.campoCorreo.getText();
            String telefono = this.campoTelefono.getText();
            String sexo = this.opcionFemenino.isSelected() ? "M" : "H";
            String turno = this.opcionMatutino.isSelected() ? "Matutino" : "Vespertino";
            String rol = this.tipoRol.getSelectedItem().toString();

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.obtenerFuncionarioRH();
            boolean empleadoAgregadoCorrectamente = funcionarioRH.agregarNuevoEmpleado(nombre, apellido, correo, telefono, sexo, turno, rol);   
            this.limpiarCamposFormulario();

            if(empleadoAgregadoCorrectamente){
                javax.swing.JOptionPane.showMessageDialog(null, "Empleado registrado correctamente", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(null, "No se pudo registrar el empleado", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            

            VentanaMenuRH ventana = new VentanaMenuRH("Menú Recursos Humanos");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonLimpiar.addActionListener(e -> {
            this.limpiarCamposFormulario();
        });
    }

    private void limpiarCamposFormulario() {
        this.campoNombre.setText("");
        this.campoApellido.setText("");
        this.campoCorreo.setText("");
        this.campoTelefono.setText("");
        this.tipoRol.setSelectedIndex(0);
        this.sexo.clearSelection();
        this.turno.clearSelection();
    }
}

