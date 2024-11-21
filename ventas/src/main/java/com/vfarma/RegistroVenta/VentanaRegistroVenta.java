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
import com.vfarma.Modelo.Efectivo;
import com.vfarma.Modelo.InformacionPersonaFisica;
import com.vfarma.Modelo.Pago;
import com.vfarma.Modelo.Producto;
import com.vfarma.Modelo.Recibo;
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
    private JButton botonLimpiar;
    private Cajero cajero;

    public VentanaRegistroVenta(String titulo) {
        super(titulo);
        this.productosAlmacen = new JComboBox<>();

        this.cajero = Cajero.obtenerInstancia();

        this.cajero.consultasProducto.obtenerNombreProductosEnExistencia().forEach(producto -> {
            this.productosAlmacen.addItem(producto.obtenerNombreProducto() + " --- " + producto.obtenerClaveProducto());
        });

        this.carritoCompras = GestorFormulario.crearTabla(new String[]{"Producto", "Cantidad", "Precio"});
        this.carritoCompras.setFillsViewportHeight(true);
        this.carritoCompras.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        this.carritoCompras.setPreferredScrollableViewportSize(new java.awt.Dimension(400, 150));
        this.carritoCompras.setFillsViewportHeight(true);

        this.tablaCarritoCompras = new JScrollPane(this.carritoCompras);
    }

    private void actualizarEstadoBotones() {

        boolean carritoVacio = this.carritoCompras.getRowCount() == 0;
        boolean campoDineroVacio = this.campoDineroRecibido.getText().trim().isEmpty();
    
        this.botonFinalizar.setEnabled(!carritoVacio && !campoDineroVacio && this.opcionRecibo.isSelected());
        this.botonContinuar.setEnabled(!carritoVacio && this.opcionFactura.isSelected());
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
        InformacionBoton informacionBotonLimpiar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonAgregar = GestorComponentes.crearBoton(informacionBotonAgregar, estilosBoton);
        this.botonContinuar = GestorComponentes.crearBoton(informacionBotonContinuar, estilosBoton);
        this.botonFinalizar = GestorComponentes.crearBoton(informacionBotonFinalizar, estilosBoton);
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonLimpiar, estilosBoton);

        this.botonFinalizar.setEnabled(false);

        botones.add(this.botonAgregar);
        botones.add(this.botonContinuar);
        botones.add(this.botonFinalizar);
        botones.add(this.botonLimpiar);

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

        this.botonLimpiar.addActionListener(e -> {
            DefaultTableModel modeloCarrito = (DefaultTableModel) this.carritoCompras.getModel();
            modeloCarrito.setRowCount(0);
            this.campoDineroRecibido.setText("");
            this.actualizarEstadoBotones();
        });



        this.opcionFactura.addActionListener(e -> {
            this.botonFinalizar.setEnabled(false);
            this.botonContinuar.setEnabled(true);
            this.campoDineroRecibido.setEnabled(false);
        });

        this.opcionRecibo.addActionListener(e -> {
            this.botonFinalizar.setEnabled(true);
            this.botonContinuar.setEnabled(false);
            this.campoDineroRecibido.setEnabled(true);
        });

        this.botonContinuar.addActionListener(e -> {
            VentanaFormularioFactura ventanaFactura = new VentanaFormularioFactura("Factura");
            ventanaFactura.iniciarVentana();
            ventanaFactura.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonAgregar.addActionListener(e -> {
            String productoSeleccionado = (String) this.productosAlmacen.getSelectedItem();
            String[] partes = productoSeleccionado.split("---");
            String nombreProducto = partes[0].trim();
            String idProducto = partes[1].trim();
            String cantidadText = this.campoCantidad.getText().trim();
        
            if (cantidadText.isEmpty()) {
                System.out.println("Por favor, ingrese una cantidad.");
                return;
            }
        
            try {
                int cantidadDeseada = Integer.parseInt(cantidadText);
                if (cantidadDeseada <= 0) {
                    System.out.println("La cantidad debe ser mayor que 0.");
                    return;
                }
        
                // Verificar la existencia en el inventario
                int existenciaDisponible = this.cajero.consultasProducto.obtenerExistenciaProductoPorId(Integer.parseInt(idProducto));
                if (cantidadDeseada > existenciaDisponible) {
                    System.out.println("No hay suficiente inventario para el producto seleccionado.");
                    return;
                }
        
                float precioProducto = this.cajero.consultasProducto.obtenerPrecioProductoPorId(Integer.parseInt(idProducto));
                float precioProductos = cantidadDeseada * precioProducto;
        
                DefaultTableModel modeloCarrito = (DefaultTableModel) this.carritoCompras.getModel();
                boolean productoEncontrado = false;
        
                // Verificar si el producto ya está en el carrito
                for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
                    String productoEnTabla = (String) modeloCarrito.getValueAt(i, 0);
                    if (productoEnTabla.equals(nombreProducto)) {
                        // Actualizar la cantidad y el precio total
                        int cantidadActual = Integer.parseInt((String) modeloCarrito.getValueAt(i, 1));
                        if (cantidadActual + cantidadDeseada > existenciaDisponible) {
                            System.out.println("La cantidad total excede la existencia disponible.");
                            return;
                        }
                        modeloCarrito.setValueAt(String.valueOf(cantidadActual + cantidadDeseada), i, 1);
                        float precioActual = (float) modeloCarrito.getValueAt(i, 2);
                        modeloCarrito.setValueAt(precioActual + precioProductos, i, 2);
                        productoEncontrado = true;
                        break;
                    }
                }
        
                // Si no está en el carrito, agregar una nueva fila
                if (!productoEncontrado) {
                    modeloCarrito.addRow(new Object[]{nombreProducto, cantidadText, precioProductos});
                }
        
                // Actualizar el carrito de compras en la información de ventas
                Producto productoEncontradoObj = this.cajero.consultasProducto.buscarProductoPorID(Integer.parseInt(idProducto));
                for (int i = 0; i < cantidadDeseada; i++) {
                    this.cajero.informacionVenta.obtenerCarritoCompras().agregarProducto(productoEncontradoObj);
                }
        
                this.actualizarEstadoBotones();
                this.carritoCompras.revalidate();
                this.carritoCompras.repaint();
        
            } catch (NumberFormatException ex) {
                System.out.println("Error: La cantidad ingresada no es un número válido.");
            }
        });
        

        //La venta sera por recibo
        this.botonFinalizar.addActionListener(e -> {
            float cantidadDineroRecibida = 0;
            
            try {
                String dineroRecibido = this.campoDineroRecibido.getText().trim();
                 cantidadDineroRecibida = Float.parseFloat(dineroRecibido);
                System.out.println("El dinero recibido es: " + cantidadDineroRecibida);
            } catch (NumberFormatException ex) {
                System.out.println("Error: La entrada no es un número válido.");
            }
            this.cajero.seleccionarTipoCliente(new InformacionPersonaFisica());
            

            float cantidadAPagar = this.cajero.informacionVenta.obtenerMontoTotalVenta();


            Efectivo efectivo = new Efectivo();
            efectivo.colocarCantidadAPagar(cantidadAPagar);
            efectivo.colocarDineroRecibido(cantidadDineroRecibida);

            Pago pago = new Pago();
            pago.colocarMetodoPago(efectivo);
            
            

            this.cajero.informacionVenta.obtenerInformacionCliente().colocarPago(pago);
            
            this.cajero.informacionVenta.colocarComprobante(new Recibo(this.cajero.informacionVenta));
            this.cajero.finalizarVenta();

           // Volver al menu inicial TO--DO
        });
    }
}
