package com.ventanas_pdv.Ventas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
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

public class VentanaRegistroVenta extends VentanaFormulario {
    private JComboBox<String> productosAlmacen;
    private JTextField campoCantidad;
    private JTextField campoDineroRecibido;
    private JTable carritoCompras;
    private JScrollPane tablaCarritoCompras;
    private ButtonGroup tipoComprobante;
    private JRadioButton opcionFactura;
    private JRadioButton opcionRecibo;
    private JButton botonAgregar;
    private JButton botonContinuar;
    private JButton botonFinalizar;
    private JButton botonCancelar;

    public VentanaRegistroVenta(String titulo) {
        super(titulo);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();
        ArrayList<JRadioButton> opciones = new ArrayList<>();

        this.opcionFactura = GestorFormulario.crearOpcionMultiple("Factura");
        this.opcionRecibo = GestorFormulario.crearOpcionMultiple("Recibo");
        opciones.add(this.opcionFactura);
        opciones.add(this.opcionRecibo);

        this.tipoComprobante = GestorFormulario.crearGrupoBotones(opciones);
        this.productosAlmacen = GestorFormulario.crearListaOpciones();
        this.campoCantidad = GestorFormulario.crearCampoTexto(2);
        this.campoDineroRecibido = GestorFormulario.crearCampoTexto(2);
        this.carritoCompras = GestorFormulario.crearTabla(new String[] { "Producto", "Cantidad", "Precio" });
        this.tablaCarritoCompras = new JScrollPane(this.carritoCompras);
        
        formulario.agregarCampo(new InformacionCampoFormulario("Producto:", this.productosAlmacen));
        formulario.agregarCampo(new InformacionCampoFormulario("Cantidad:", this.campoCantidad));
        formulario.agregarCampo(new InformacionCampoFormulario("Tipo de Comprobante:", this.tipoComprobante));
        formulario.agregarCampo(new InformacionCampoFormulario("Dinero Recibido:", this.campoDineroRecibido));
        formulario.agregarCampo(new InformacionCampoFormulario("Carrito de Compras:", this.tablaCarritoCompras));

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonAgregar = new InformacionBoton("Agregar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonContinuar = new InformacionBoton("Continuar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonFinalizar = new InformacionBoton("Finalizar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonCancelar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonAgregar = GestorComponentes.crearBoton(informacionBotonAgregar, estilosBoton);
        this.botonContinuar = GestorComponentes.crearBoton(informacionBotonContinuar, estilosBoton);
        this.botonFinalizar = GestorComponentes.crearBoton(informacionBotonFinalizar, estilosBoton);
        this.botonCancelar = GestorComponentes.crearBoton(informacionBotonCancelar, estilosBoton);

        this.botonFinalizar.setEnabled(false);

        botones.add(this.botonAgregar);
        botones.add(this.botonContinuar);
        botones.add(this.botonFinalizar);
        botones.add(this.botonCancelar);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            VentanaSeleccionCaja ventana = new VentanaSeleccionCaja("Seleccionar Caja");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.opcionFactura.addActionListener(e -> {
            this.botonFinalizar.setEnabled(false);
            this.botonContinuar.setEnabled(true);
        });

        this.opcionRecibo.addActionListener(e -> {
            this.botonFinalizar.setEnabled(true);
            this.botonContinuar.setEnabled(false);
        });

        this.botonContinuar.addActionListener(e -> {
            VentanaFormularioFactura ventanaFactura = new VentanaFormularioFactura("Factura");
            ventanaFactura.iniciarVentana();
            ventanaFactura.mostrarVentana();
            this.cerrarVentana();
        });
    }
    
}
