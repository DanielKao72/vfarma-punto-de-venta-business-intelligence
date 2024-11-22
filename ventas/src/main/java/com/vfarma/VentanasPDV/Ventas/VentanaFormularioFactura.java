package com.vfarma.VentanasPDV.Ventas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.Farmacia.DatosFarmacia.InformacionPersonaFisica;
import com.vfarma.Farmacia.DatosFarmacia.InformacionPersonaMoral;
import com.vfarma.Farmacia.Ventas.Cajero;
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
        this.formulario = new Formulario();
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

        this.botonFinalizar.addActionListener(e -> {
            Cajero cajero = Cajero.obtenerCajero();

            if (!this.todosLosCamposLlenos()) {
                JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String rfc = this.campoRFC.getText();
            String domicilio = this.campoDomicilio.getText();

            if (this.opcionPersonaFisica.isSelected()) {
                String nombre = this.campoNombre.getText();
                String apellidos = this.campoApellidos.getText();

                InformacionPersonaFisica cliente = new InformacionPersonaFisica(nombre, apellidos, domicilio, rfc);
                cajero.seleccionarTipoCliente(cliente);

            } else if (this.opcionPersonaMoral.isSelected()) {
                String razonSocial = this.campoRazonSocial.getText();
                String regimenFiscal = this.campoRegimenFiscal.getText();

                InformacionPersonaMoral cliente = new InformacionPersonaMoral(razonSocial, regimenFiscal, domicilio, rfc);
                cajero.seleccionarTipoCliente(cliente);
            }

            cajero.realizarVentaConFactura();

            cajero.imprimirComprobante();

            JOptionPane.showMessageDialog(null, "La factura se ha generado exitosamente ", "Factura", JOptionPane.INFORMATION_MESSAGE);

            cajero.terminarVenta();

            // Redireccion a la ventana de ventas
            this.cerrarVentana();
            VentanaMenuVenta ventana = new VentanaMenuVenta("Menú Ventas");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
        });
    }

    private Boolean todosLosCamposLlenos() {
        Boolean estanLlenosTodosLosCampos = true;
        if (this.opcionPersonaFisica.isSelected()) {
            if (this.campoNombre.getText().isEmpty()
                    || this.campoApellidos.getText().isEmpty()
                    || this.campoRFC.getText().isEmpty()
                    || this.campoDomicilio.getText().isEmpty()) {

                estanLlenosTodosLosCampos = false;
            }
        } else if (this.opcionPersonaMoral.isSelected()) {
            if (this.campoRazonSocial.getText().isEmpty()
                    || this.campoRegimenFiscal.getText().isEmpty()
                    || this.campoRFC.getText().isEmpty()
                    || this.campoDomicilio.getText().isEmpty()) {

                estanLlenosTodosLosCampos = false;
            }
        }
        return estanLlenosTodosLosCampos;
    }
}
