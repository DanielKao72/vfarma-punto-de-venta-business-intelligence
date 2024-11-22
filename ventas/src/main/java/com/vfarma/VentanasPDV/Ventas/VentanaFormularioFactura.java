package com.vfarma.VentanasPDV.Ventas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaFormularioFactura extends VentanaFormulario {
    private ButtonGroup tipoPersona;
    private JRadioButton opcionPersonaFisica;
    private JRadioButton opcionPersonaMoral;
    private JTextField campoNombre;
    private JTextField campoApellidos;
    private JTextField campoRegimenFiscal;
    private JTextField campoRazonSocial;
    private JTextField campoRFC;
    private JTextField campoDomicilio;
    private JButton botonFinalizar;
    private JButton botonLimpiar;

    public VentanaFormularioFactura(String titulo) {
        super(titulo);
    }

    @Override
    public Formulario crearCamposFormulario() {
        ArrayList<JRadioButton> opciones = new ArrayList<>();

        this.opcionPersonaFisica = GestorFormulario.crearOpcionMultiple("Persona Física");
        this.opcionPersonaMoral = GestorFormulario.crearOpcionMultiple("Persona Moral");
        opciones.add(this.opcionPersonaFisica);
        opciones.add(this.opcionPersonaMoral);

        this.tipoPersona = GestorFormulario.crearCampoOpcionMultiple(opciones);
        this.campoNombre = GestorFormulario.crearCampoTexto(50);
        this.campoApellidos = GestorFormulario.crearCampoTexto(50);
        this.campoRazonSocial = GestorFormulario.crearCampoTexto(50);
        this.campoRegimenFiscal = GestorFormulario.crearCampoTexto(50);
        this.campoRFC = GestorFormulario.crearCampoTexto(13);
        this.campoDomicilio = GestorFormulario.crearCampoTexto(60);

        this.formulario.agregarCampo(new InformacionCampoFormulario("Tipo de Persona:", this.tipoPersona));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Nombre:", this.campoNombre));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Apellidos:", this.campoApellidos));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Razón Social:", this.campoRazonSocial));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Regimen Fiscal:", this.campoRegimenFiscal));
        this.formulario.agregarCampo(new InformacionCampoFormulario("RFC:", this.campoRFC));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Domicilio:", this.campoDomicilio));

        this.campoRazonSocial.setEditable(false);
        this.campoRegimenFiscal.setEditable(false);

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonFinalizar = new InformacionBoton("Finalizar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonFinalizar = GestorComponentes.crearBoton(informacionBotonFinalizar, estilosBoton);
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonLimpiar);
        botones.add(this.botonFinalizar);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            VentanaControlAcceso ventana = new VentanaControlAcceso("Control de Acceso");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            VentanaRegistroVenta ventana = new VentanaRegistroVenta("Registro de Venta");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.opcionPersonaMoral.addActionListener(e -> {
            this.campoNombre.setEditable(false);
            this.campoApellidos.setEditable(false);
            this.campoNombre.setText("");
            this.campoApellidos.setText("");
            this.campoRazonSocial.setEditable(true);
            this.campoRegimenFiscal.setEditable(true);
        });

        this.opcionPersonaFisica.addActionListener(e -> {
            this.campoNombre.setEditable(true);
            this.campoApellidos.setEditable(true);
            this.campoRazonSocial.setEditable(false);
            this.campoRegimenFiscal.setEditable(false);
            this.campoRazonSocial.setText("");
            this.campoRegimenFiscal.setText("");
        });

        this.botonFinalizar.addActionListener(e -> {
            this.cerrarVentana();
            VentanaMenuVenta ventana = new VentanaMenuVenta("Menú Ventas");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
        });

        this.botonLimpiar.addActionListener(e -> {
            this.campoNombre.setText("");
            this.campoApellidos.setText("");
            this.campoRazonSocial.setText("");
            this.campoRegimenFiscal.setText("");
            this.campoRFC.setText("");
            this.campoDomicilio.setText("");
        });
    }
}
