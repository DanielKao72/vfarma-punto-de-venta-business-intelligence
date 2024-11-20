package com.vfarma.RegistroVenta;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionBoton;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;
import com.vfarma.ComponentesVentana.InformacionEstilosBoton;
import com.vfarma.GestoresComponentesVentana.GestorComponentes;
import com.vfarma.GestoresComponentesVentana.GestorFormulario;
import com.vfarma.Modelo.Factura;
import com.vfarma.Modelo.InformacionCliente;
import com.vfarma.Modelo.InformacionPersonaFisica;
import com.vfarma.Modelo.InformacionPersonaMoral;
import com.vfarma.Ventanas.VentanaFormulario;

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
    private JButton botonCancelar;

    public VentanaFormularioFactura(String titulo) {
        super(titulo);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();
        ArrayList<JRadioButton> opciones = new ArrayList<>();

        this.opcionPersonaFisica = GestorFormulario.crearOpcionMultiple("Persona Física");
        this.opcionPersonaMoral = GestorFormulario.crearOpcionMultiple("Persona Moral");
        opciones.add(this.opcionPersonaFisica);
        opciones.add(this.opcionPersonaMoral);

        this.tipoPersona = GestorFormulario.crearGrupoBotones(opciones);
        this.campoNombre = GestorFormulario.crearCampoTexto(50);
        this.campoApellidos = GestorFormulario.crearCampoTexto(50);
        this.campoRazonSocial = GestorFormulario.crearCampoTexto(50);
        this.campoRegimenFiscal = GestorFormulario.crearCampoTexto(50);
        this.campoRFC = GestorFormulario.crearCampoTexto(13);
        this.campoDomicilio = GestorFormulario.crearCampoTexto(60);

        formulario.agregarCampo(new InformacionCampoFormulario("Tipo de Persona:", this.tipoPersona));
        formulario.agregarCampo(new InformacionCampoFormulario("Nombre:", this.campoNombre));
        formulario.agregarCampo(new InformacionCampoFormulario("Apellidos:", this.campoApellidos));
        formulario.agregarCampo(new InformacionCampoFormulario("Razón Social:", this.campoRazonSocial));
        formulario.agregarCampo(new InformacionCampoFormulario("Regimen Fiscal:", this.campoRegimenFiscal));
        formulario.agregarCampo(new InformacionCampoFormulario("RFC:", this.campoRFC));
        formulario.agregarCampo(new InformacionCampoFormulario("Domicilio:", this.campoDomicilio));

        this.campoRazonSocial.setEditable(false);
        this.campoRegimenFiscal.setEditable(false);

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonFinalizar = new InformacionBoton("Finalizar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Cancelar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonFinalizar = GestorComponentes.crearBoton(informacionBotonFinalizar, estilosBoton);
        this.botonCancelar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        botones.add(this.botonCancelar);
        botones.add(this.botonFinalizar);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
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
        }
        );

        this.opcionPersonaFisica.addActionListener(e -> {
            this.campoNombre.setEditable(true);
            this.campoApellidos.setEditable(true);
            this.campoRazonSocial.setEditable(false);
            this.campoRegimenFiscal.setEditable(false);
            this.campoRazonSocial.setText("");
            this.campoRegimenFiscal.setText("");
        }
        );

        this.botonFinalizar.addActionListener(e -> {
            String rfc = this.campoRFC.getText();
            String domicilio = this.campoDomicilio.getText();

            Cajero cajero = Cajero.obtenerInstancia();

            if (this.opcionPersonaFisica.isSelected()) {
                String nombre = this.campoNombre.getText();
                String apellidos = this.campoApellidos.getText();

                InformacionPersonaFisica cliente = new InformacionPersonaFisica();
                cliente.colocarClaveRFCCliente(rfc);
                cliente.setDomicilioCliente(domicilio);
                cliente.colocarNombreCliente(nombre);
                cliente.colocarApellidosCliente(apellidos);

                cajero.seleccionarTipoCliente(cliente);

            } else if (this.opcionPersonaMoral.isSelected()) {
                String razonSocial = this.campoRazonSocial.getText();
                String regimenFiscal = this.campoRegimenFiscal.getText();

                InformacionPersonaMoral cliente = new InformacionPersonaMoral();
                cliente.colocarClaveRFCCliente(rfc);
                cliente.setDomicilioCliente(domicilio);
                cliente.colocarRazonSocial(razonSocial);
                cliente.colocarRegimenFiscal(regimenFiscal);

                cajero.seleccionarTipoCliente(cliente);
            }
            cajero.finalizarVenta();

            this.cerrarVentana();
            VentanaMenuVentas ventana = new VentanaMenuVentas("Menú Ventas");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
        });
    }

}
