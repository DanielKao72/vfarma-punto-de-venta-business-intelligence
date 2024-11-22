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
import com.vfarma.Farmacia.Modelo.InformacionEmpleado;
import com.vfarma.Farmacia.RecursosHumanos.FuncionarioRecursosHumanos;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaEdicionEmpleado extends VentanaFormulario {
    private JTextField campoUsuario;
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
    private JButton botonBuscar;
    private JButton botonLimpiar;
    private InformacionEmpleado informacionEmpleado = new InformacionEmpleado("", "", "", "", "", "", "", "");

    public VentanaEdicionEmpleado(String tituloVentana) {
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

        this.campoUsuario = GestorFormulario.crearCampoTexto(10);
        this.campoNombre = GestorFormulario.crearCampoTexto(50);
        this.campoApellido = GestorFormulario.crearCampoTexto(50);
        this.campoCorreo = GestorFormulario.crearCampoTexto(50);
        this.campoTelefono = GestorFormulario.crearCampoTexto(10);
        this.tipoRol = GestorFormulario.creaListaOpciones(new String[] { "Gerente", "Cajero", "Almacenista", "Recursos Humanos" });

        this.formulario.agregarCampo(new InformacionCampoFormulario("Clave Empleado:", this.campoUsuario));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Nombre:", this.campoNombre));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Apellido:", this.campoApellido));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Correo:", this.campoCorreo));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Teléfono:", this.campoTelefono));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Rol:", this.tipoRol));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Sexo:", this.sexo));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Turno:", this.turno));

        this.restringirCamposEditables();

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonGuardar = new InformacionBoton("Guardar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonBuscar = new InformacionBoton("Buscar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonGuardar = GestorComponentes.crearBoton(informacionBotonGuardar, estilosBoton);
        this.botonBuscar = GestorComponentes.crearBoton(informacionBotonBuscar, estilosBoton);
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonLimpiar);
        botones.add(this.botonBuscar);
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

        this.botonLimpiar.addActionListener(e -> {
            this.limpiarCamposFormulario();
        });

        this.botonBuscar.addActionListener(e -> {
            String usuario = this.campoUsuario.getText().trim();

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.obtenerFuncionarioRH();
            informacionEmpleado = funcionarioRH.consultarInformacionEmpleado(usuario);

            if (informacionEmpleado != null) {
                habilitarCamposEdicion();
                this.campoNombre.setText(informacionEmpleado.obtenerNombreEmpleado());
                this.campoApellido.setText(informacionEmpleado.obtenerApellidoEmpleado());
                this.campoCorreo.setText(informacionEmpleado.obtenerCorreoEmpleado());
                this.campoTelefono.setText(informacionEmpleado.obtenerTelefonoEmpleado());
                this.tipoRol.setSelectedItem(informacionEmpleado.obtenerRolEmpleado());

                if ("Masculino".equals(informacionEmpleado.obtenerSexoEmpleado())) {
                    this.opcionMasculino.setSelected(true);
                } else {
                    this.opcionFemenino.setSelected(true);
                }

                if ("Matutino".equals(informacionEmpleado.obtenerTurnoEmpleado())) {
                    this.opcionMatutino.setSelected(true);
                } else {
                    this.opcionVespertino.setSelected(true);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "El usuario no existe en la base de datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });

        this.botonGuardar.addActionListener(e -> {
            informacionEmpleado.colocarNombreEmpleado(this.campoNombre.getText());
            informacionEmpleado.colocarApellidoEmpleado(this.campoApellido.getText());
            informacionEmpleado.colocarCorreoEmpleado(this.campoCorreo.getText());
            informacionEmpleado.colocarTelefonoEmpleado(this.campoTelefono.getText());
            informacionEmpleado.colocarSexoEmpleado(this.opcionMasculino.isSelected() ? "H" : "M");
            informacionEmpleado.colocarTurnoEmpleado
            (
                this.opcionMatutino.isSelected() ? "Matutino" : "Vespertino"
            );
            informacionEmpleado.colocarRolEmpleado(this.tipoRol.getSelectedItem().toString());

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.obtenerFuncionarioRH();
            boolean informacionActualizada = funcionarioRH.editarInformacionEmpleado(informacionEmpleado);

            if(informacionActualizada){
                javax.swing.JOptionPane.showMessageDialog(null,
                "Información del empleado actualizada con éxito.",
                "Éxito",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

                restringirCamposEditables();
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(null, 
                "Hubo un error al guardar los cambios", 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
            limpiarCamposFormulario();     
        });
    }

    private void habilitarCamposEdicion() {
        this.campoNombre.setEditable(true);
        this.campoApellido.setEditable(true);
        this.campoCorreo.setEditable(true);
        this.campoTelefono.setEditable(true);
        this.tipoRol.setEnabled(true);
        this.opcionMasculino.setEnabled(true);
        this.opcionFemenino.setEnabled(true);
        this.opcionMatutino.setEnabled(true);
        this.opcionVespertino.setEnabled(true);
    }

    private void limpiarCamposFormulario(){
        this.campoUsuario.setText("");
        this.campoNombre.setText("");
        this.campoApellido.setText("");
        this.campoCorreo.setText("");
        this.campoTelefono.setText("");
        this.sexo.clearSelection();
        this.turno.clearSelection();
    }

    private void restringirCamposEditables() {
        this.campoNombre.setEditable(false);
        this.campoApellido.setEditable(false);
        this.campoCorreo.setEditable(false);
        this.campoTelefono.setEditable(false);
        this.tipoRol.setEnabled(false);
        this.sexo.clearSelection();
        this.turno.clearSelection();
    }
}
