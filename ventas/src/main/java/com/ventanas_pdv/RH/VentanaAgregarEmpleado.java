package com.ventanas_pdv.RH;

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
import com.vfarma.RegistroEmpleados.Controlador.FuncionarioRecursosHumanos;

public class VentanaAgregarEmpleado extends VentanaFormulario {

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
    private JButton botonCancelar;

    public VentanaAgregarEmpleado(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();
        ArrayList<JRadioButton> opcionesSexo = new ArrayList<>();
        ArrayList<JRadioButton> opcionesTurno = new ArrayList<>();

        this.opcionMasculino = GestorFormulario.crearOpcionMultiple("Masculino");
        this.opcionFemenino = GestorFormulario.crearOpcionMultiple("Femenino");
        opcionesSexo.add(this.opcionMasculino);
        opcionesSexo.add(this.opcionFemenino);

        this.sexo = GestorFormulario.crearGrupoBotones(opcionesSexo);

        this.opcionMatutino = GestorFormulario.crearOpcionMultiple("Matutino");
        this.opcionVespertino = GestorFormulario.crearOpcionMultiple("Vespertino");
        opcionesTurno.add(this.opcionMatutino);
        opcionesTurno.add(this.opcionVespertino);

        this.turno = GestorFormulario.crearGrupoBotones(opcionesTurno);
        this.campoNombre = GestorFormulario.crearCampoTexto(50); 
        this.campoApellido = GestorFormulario.crearCampoTexto(50);
        this.campoCorreo = GestorFormulario.crearCampoTexto(50);
        this.campoTelefono = GestorFormulario.crearCampoTexto(10);

        this.tipoRol = GestorFormulario.creaListaOpciones(
            new String[] { "Gerente", "Cajero", "Almacén", "Recursos Humanos" }
        );

        formulario.agregarCampo(new InformacionCampoFormulario("Nombre:", this.campoNombre));
        formulario.agregarCampo(new InformacionCampoFormulario("Apellido:", this.campoApellido));
        formulario.agregarCampo(new InformacionCampoFormulario("Correo:", this.campoCorreo));
        formulario.agregarCampo(new InformacionCampoFormulario("Teléfono:", this.campoTelefono));
        formulario.agregarCampo(new InformacionCampoFormulario("Rol:", this.tipoRol));
        formulario.agregarCampo(new InformacionCampoFormulario("Sexo:", this.sexo));
        formulario.agregarCampo(new InformacionCampoFormulario("Turno:", this.turno));

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonGuardar = new InformacionBoton("Finalizar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Cancelar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonGuardar = GestorComponentes.crearBoton(informacionBotonGuardar, estilosBoton);
        this.botonCancelar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonCancelar);
        botones.add(this.botonGuardar);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            this.cerrarVentana();
            VentanaMenuRH ventana = new VentanaMenuRH("Menú Recursos Humanos");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            this.cerrarVentana();
        });

        this.botonGuardar.addActionListener(e -> {

            String nombre = campoNombre.getText();
            String apellido = campoApellido.getText();
            String correo = campoCorreo.getText();
            String telefono = campoTelefono.getText();
            String sexo = opcionFemenino.isSelected() ? "M" : "H";
            String turno = opcionMatutino.isSelected() ? "Matutino" : "Vespertino";
            String rol = tipoRol.getSelectedItem().toString();

            FuncionarioRecursosHumanos funcionarioRH = FuncionarioRecursosHumanos.llamarFuncionarioRH();
            ArrayList<String> credenciales = funcionarioRH.agregarNuevoEmpleado(nombre, apellido, correo, telefono, sexo, turno, rol);
            limpiarCamposFormulario();

            if(credenciales.get(0).equals("") == false){
                javax.swing.JOptionPane.showMessageDialog(
                null,
                "Empleado registrado con éxito \n" 
                + "Usuario: " + credenciales.get(0) + "\nContraseña: " + credenciales.get(1),
                "Éxito",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(
                null,
                "No se pudo registrar el empleado",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
            
            this.cerrarVentana();
            VentanaMenuRH ventana = new VentanaMenuRH("Menú Recursos Humanos");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
        });

        this.botonCancelar.addActionListener(e -> {
            limpiarCamposFormulario();
        });
    }

    private void limpiarCamposFormulario(){
        this.campoNombre.setText("");
        this.campoApellido.setText("");
        this.campoCorreo.setText("");
        this.campoTelefono.setText("");
        this.sexo.clearSelection();
        this.turno.clearSelection();
    }
    
}
