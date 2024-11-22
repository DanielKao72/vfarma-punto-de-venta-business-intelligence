package com.vfarma.VentanasPDV.Ventas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.Farmacia.DatosFarmacia.InformacionProducto;
import com.vfarma.Farmacia.Ventas.Cajero;


public class VentanaRegistroVenta extends VentanaFormulario {

    private JComboBox<String> productosAlmacen;
    private JSpinner campoCantidad;
    private JSpinner campoDineroRecibido;
    private JTable carritoCompras;
    private JScrollPane tablaCarritoCompras;
    private ButtonGroup tipoComprobante;
    private JRadioButton opcionFactura;
    private JRadioButton opcionRecibo;
    private JButton botonAgregar;
    private JButton botonContinuar;
    private JButton botonFinalizar;
    private JButton botonLimpiar;
    private Formulario formulario;

    public VentanaRegistroVenta(String titulo) {
        super(titulo);
        this.obtenerProductosDisponiblesParaComprar();
        this.inicializarTablaProductos();
        this.formulario = new Formulario();
    }

    private void obtenerProductosDisponiblesParaComprar() {
        this.productosAlmacen = new JComboBox<>();

        Cajero cajero = Cajero.obtenerCajero();
        cajero.consultasProducto.obtenerNombreProductosEnExistencia().forEach(producto -> {
            this.productosAlmacen.addItem(producto.obtenerNombreProducto() + " --- " + producto.obtenerClaveProducto());
        });
    }

    private void inicializarTablaProductos() {
        this.carritoCompras = GestorFormulario.crearTabla(new String[]{"Producto", "Cantidad", "Precio"});
        this.carritoCompras.setFillsViewportHeight(true);
        this.carritoCompras.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        this.carritoCompras.setPreferredScrollableViewportSize(new java.awt.Dimension(400, 150));
        this.carritoCompras.setFillsViewportHeight(true);

        this.tablaCarritoCompras = new JScrollPane(this.carritoCompras);

    }

    private void actualizarEstadoBotones() {
        boolean carritoVacio = true;

        if (this.carritoCompras.getRowCount() > 0) {
            carritoVacio = false;
        }

        boolean campoDineroVacio = this.campoDineroRecibido.getValue().toString().isEmpty();

        this.botonFinalizar.setEnabled(!carritoVacio && !campoDineroVacio && this.opcionRecibo.isSelected());
        this.botonContinuar.setEnabled(!carritoVacio && this.opcionFactura.isSelected());
    }

    @Override
    public Formulario crearCamposFormulario() {

        ArrayList<JRadioButton> opciones = new ArrayList<>();

        this.opcionFactura = GestorFormulario.crearOpcionMultiple("Factura");
        this.opcionRecibo = GestorFormulario.crearOpcionMultiple("Recibo");
        opciones.add(this.opcionFactura);
        opciones.add(this.opcionRecibo);

        this.tipoComprobante = this.crearGrupoBotones(opciones);

        this.campoCantidad = GestorFormulario.crearCampoNumerico();
        this.campoDineroRecibido = GestorFormulario.crearCampoNumerico();

        this.formulario.agregarCampo(new InformacionCampoFormulario("Producto:", this.productosAlmacen));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Cantidad:", this.campoCantidad));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Tipo de Comprobante:", this.tipoComprobante));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Dinero Recibido:", this.campoDineroRecibido));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Carrito de Compras:", this.tablaCarritoCompras));

        this.campoDineroRecibido.setEnabled(false);

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonAgregar = new InformacionBoton("Agregar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonContinuar = new InformacionBoton("Continuar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonFinalizar = new InformacionBoton("Finalizar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonLimpiar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonAgregar = GestorComponentes.crearBoton(informacionBotonAgregar, estilosBoton);
        this.botonContinuar = GestorComponentes.crearBoton(informacionBotonContinuar, estilosBoton);
        this.botonFinalizar = GestorComponentes.crearBoton(informacionBotonFinalizar, estilosBoton);
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonLimpiar, estilosBoton);

        this.botonFinalizar.setEnabled(false);
        this.botonContinuar.setEnabled(false);

        botones.add(this.botonAgregar);
        botones.add(this.botonContinuar);
        botones.add(this.botonFinalizar);
        botones.add(this.botonLimpiar);

        return botones;
    }

    private Boolean verificarSiElProductoYaEstaEnElCarrito(DefaultTableModel modeloCarrito, int cantidadDeseada, String nombreProducto, int existenciaDisponible, float precioProductos) {
        boolean productoEncontrado = false;

        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
            String productoEnTabla = (String) modeloCarrito.getValueAt(i, 0);
            if (productoEnTabla.equals(nombreProducto)) {

                int cantidadActual = Integer.parseInt((String) modeloCarrito.getValueAt(i, 1));
                if (cantidadActual + cantidadDeseada > existenciaDisponible) {
                   
                    return null;
                }

                modeloCarrito.setValueAt(String.valueOf(cantidadActual + cantidadDeseada), i, 1);
                float precioActual = (float) modeloCarrito.getValueAt(i, 2);
                modeloCarrito.setValueAt(precioActual + precioProductos, i, 2);
                productoEncontrado = true;

                break;
            }
        }
        return productoEncontrado;

    }

    @Override
    public void configurarEventos() {
        Cajero cajero = Cajero.obtenerCajero();

        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            String nombreCajaActual = cajero.obtenerNombreCaja();
            cajero.consultasCaja.desOcuparCaja(nombreCajaActual);
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            String nombreCajaActual = cajero.obtenerNombreCaja();
            cajero.consultasCaja.desOcuparCaja(nombreCajaActual);

            // Redireccion a la selección de caja
            VentanaSeleccionCaja ventana = new VentanaSeleccionCaja("Seleccionar Caja");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonLimpiar.addActionListener(e -> {
            DefaultTableModel modeloCarrito = (DefaultTableModel) this.carritoCompras.getModel();
            modeloCarrito.setRowCount(0);

            cajero.informacionVenta.obtenerCarritoCompras().vaciarCarrito();
            this.campoDineroRecibido.setValue(0);
            this.campoCantidad.setValue(0);

            this.actualizarEstadoBotones();
        });

        this.opcionFactura.addActionListener(e -> {
            this.botonFinalizar.setEnabled(false);
            this.botonContinuar.setEnabled(true);
            this.campoDineroRecibido.setEnabled(false);
            this.actualizarEstadoBotones();
        });

        this.opcionRecibo.addActionListener(e -> {
            this.botonFinalizar.setEnabled(true);
            this.botonContinuar.setEnabled(false);
            this.campoDineroRecibido.setEnabled(true);
            this.actualizarEstadoBotones();
        });

        this.botonContinuar.addActionListener(e -> {
            // Redireccion a la ventana de facturación
            VentanaFormularioFactura ventanaFactura = new VentanaFormularioFactura("Factura");
            ventanaFactura.iniciarVentana();
            ventanaFactura.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonAgregar.addActionListener(e -> {
            String productoSeleccionado = (String) this.productosAlmacen.getSelectedItem();

            String[] caracteristicasProducto = productoSeleccionado.split("---");
            String nombreProducto = caracteristicasProducto[0].trim();
            String idProducto = caracteristicasProducto[1].trim();

            String cantidadElementosProducto = this.campoCantidad.getValue().toString();

            if (cantidadElementosProducto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cantidadDeseadaDelProducto = Integer.parseInt(cantidadElementosProducto);
            if (cantidadDeseadaDelProducto <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int existenciaDisponible = cajero.consultasProducto.obtenerExistenciaProductoPorId(Integer.parseInt(idProducto));
            if (cantidadDeseadaDelProducto > existenciaDisponible) {
                JOptionPane.showMessageDialog(null, "No hay suficiente inventario para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            float precioProducto = cajero.consultasProducto.obtenerPrecioProductoPorId(Integer.parseInt(idProducto));
            float precioProductos = cantidadDeseadaDelProducto * precioProducto;

            DefaultTableModel modeloCarrito = (DefaultTableModel) this.carritoCompras.getModel();
            Boolean productoEncontrado = this.verificarSiElProductoYaEstaEnElCarrito(modeloCarrito, cantidadDeseadaDelProducto, nombreProducto, existenciaDisponible, precioProductos);
            
            if (productoEncontrado == null) {
                JOptionPane.showMessageDialog(null, "La cantidad total excede la existencia disponible", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!productoEncontrado) {
                modeloCarrito.addRow(new Object[]{nombreProducto, cantidadElementosProducto, precioProductos});
            }

            // Actualizar el carrito de compras en la información de ventas
            InformacionProducto productoEncontradoObj = cajero.consultasProducto.buscarProductoPorID(Integer.parseInt(idProducto));
            for (int i = 0; i < cantidadDeseadaDelProducto; i++) {
                cajero.informacionVenta.obtenerCarritoCompras().agregarProducto(productoEncontradoObj);
            }

            this.actualizarEstadoBotones();
            this.carritoCompras.revalidate();
            this.carritoCompras.repaint();
        });

        //La venta sera por medio de un recibo
        this.botonFinalizar.addActionListener(e -> {

            float cantidadDineroRecibida = 0;

            try {
                String dineroRecibido = this.campoDineroRecibido.getValue().toString();
                cantidadDineroRecibida = Float.parseFloat(dineroRecibido);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: La entrada no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cantidadDineroRecibida < cajero.informacionVenta.obtenerMontoTotalVenta()) {
                JOptionPane.showMessageDialog(null, "El dinero recibido es menor que el monto total de la venta. Monto Total Venta = $ " + cajero.informacionVenta.obtenerMontoTotalVenta(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cajero.realizarVentaConRecibo(cantidadDineroRecibida);
            float cambioAEntregarAlCliente = cajero.calcularCambioVenta();
            cajero.imprimirComprobante();

            JOptionPane.showMessageDialog(null, "Cambio a entregar al cliente: " + cambioAEntregarAlCliente, "Cambio", JOptionPane.INFORMATION_MESSAGE);

            cajero.terminarVenta();

            // Redireccion a la ventana de ventas
            this.cerrarVentana();
            VentanaMenuVenta ventana = new VentanaMenuVenta("Menú Ventas");
            ventana.iniciarVentana();
            ventana.mostrarVentana();

        });
    }

    public  ButtonGroup crearGrupoBotones(ArrayList<JRadioButton> opciones) {
        ButtonGroup grupoBotones = new ButtonGroup();

        opciones.get(0).setSelected(true);

        for (JRadioButton opcion : opciones) {
            grupoBotones.add(opcion);
        }

        return grupoBotones;
    }
}