package com.vfarma.RegistroVenta;

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
import javax.swing.table.DefaultTableModel;

import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionBoton;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;
import com.vfarma.ComponentesVentana.InformacionEstilosBoton;
import com.vfarma.GestoresComponentesVentana.GestorComponentes;
import com.vfarma.GestoresComponentesVentana.GestorFormulario;
import com.vfarma.Ventanas.VentanaFormulario;

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
        this.productosAlmacen = new JComboBox<>();
        this.productosAlmacen.addItem("Aspirina 500mg");
        this.productosAlmacen.addItem("Paracetamol 650mg");
        this.productosAlmacen.addItem("Ibuprofeno 400mg");
        this.productosAlmacen.addItem("Amoxicilina 500mg");
        this.productosAlmacen.addItem("Vitamina C 1000mg");

        // Crear la tabla con datos por defecto
        this.carritoCompras = GestorFormulario.crearTabla(new String[] { "Producto", "Cantidad", "Precio" });
this.carritoCompras.setFillsViewportHeight(true); 
this.carritoCompras.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

// Establecer un tamaño preferido para la tabla dentro del JScrollPane
this.tablaCarritoCompras = new JScrollPane(this.carritoCompras);
this.carritoCompras.setPreferredScrollableViewportSize(new java.awt.Dimension(400, 150)); 

      
        this.carritoCompras.setFillsViewportHeight(true);
        this.carritoCompras.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        this.tablaCarritoCompras = new JScrollPane(this.carritoCompras);
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

        this.campoCantidad = GestorFormulario.crearCampoTexto(2);
        this.campoDineroRecibido = GestorFormulario.crearCampoTexto(2);

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

        this.botonAgregar.addActionListener(e -> {
            // Recuperar los valores de los campos
            String productoSeleccionado = (String) this.productosAlmacen.getSelectedItem();
            String cantidadText = (String) this.campoCantidad.getText().trim(); // Agregar trim() para eliminar espacios extra
            String precio = "10.00"; // Precio de ejemplo

            // Obtener el modelo de la tabla para agregar la fila
            DefaultTableModel modeloCarrito = (DefaultTableModel) this.carritoCompras.getModel();

            // Agregar la fila con los datos seleccionados
            modeloCarrito.addRow(new Object[]{productoSeleccionado, cantidadText, precio});
            this.carritoCompras.revalidate();
            this.carritoCompras.repaint();
        });

        this.botonFinalizar.addActionListener(e -> {
            if (this.opcionRecibo.isSelected()) {
                String productoSeleccionado = (String) this.productosAlmacen.getSelectedItem();
                String cantidad = this.campoCantidad.getText();
                String dineroRecibido = this.campoDineroRecibido.getText();

                ArrayList<String[]> detallesCarrito = new ArrayList<>();
                for (int i = 0; i < this.carritoCompras.getRowCount(); i++) {
                    String producto = (String) this.carritoCompras.getValueAt(i, 0);
                    String cantidadProducto = (String) this.carritoCompras.getValueAt(i, 1);
                    String precio = (String) this.carritoCompras.getValueAt(i, 2);
                    detallesCarrito.add(new String[]{producto, cantidadProducto, precio});
                }

                System.out.println("Datos para el Recibo:");
                System.out.println("Producto Seleccionado: " + productoSeleccionado);
                System.out.println("Cantidad: " + cantidad);
                System.out.println("Dinero Recibido: " + dineroRecibido);
                System.out.println("Carrito de Compras:");
                for (String[] detalle : detallesCarrito) {
                    System.out.println("Producto: " + detalle[0] + ", Cantidad: " + detalle[1] + ", Precio: " + detalle[2]);
                }
            }
            this.cerrarVentana();
        });
    }
}
