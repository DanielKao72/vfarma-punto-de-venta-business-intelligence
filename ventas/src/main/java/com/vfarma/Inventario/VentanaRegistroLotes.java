package com.vfarma.Inventario;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionBoton;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;
import com.vfarma.ComponentesVentana.InformacionEstilosBoton;
import com.vfarma.ControlAcceso.VentanaControlAcceso;
import com.vfarma.GestoresComponentesVentana.GestorComponentes;
import com.vfarma.GestoresComponentesVentana.GestorFormulario;
import com.vfarma.Ventanas.VentanaFormulario;

public class VentanaRegistroLotes extends VentanaFormulario {
    private JTextField claveProducto;
    private JSpinner numeroLote;
    private JCalendar fechaCaducidadLote;
    private JSpinner cantidadProductosLote;
    private JButton botonEditarLote;
    private AdmistradorInventario admistradorInventario;

    public VentanaRegistroLotes(String tituloVentana) {
        super(tituloVentana);
        this.admistradorInventario = new AdmistradorInventario();
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();

        this.claveProducto = GestorFormulario.crearCampoTexto(5);
        this.numeroLote = GestorFormulario.crearCampoNumero();
        this.fechaCaducidadLote = new JCalendar();
        this.cantidadProductosLote = GestorFormulario.crearCampoNumero();

        formulario.agregarCampo(new InformacionCampoFormulario("Clave Producto", this.claveProducto));
        formulario.agregarCampo(new InformacionCampoFormulario("Numero Lote", this.numeroLote));
        formulario.agregarCampo(new InformacionCampoFormulario("Fecha Caducidad Lote", this.fechaCaducidadLote));
        formulario.agregarCampo(new InformacionCampoFormulario("Cantidad de Productos", this.cantidadProductosLote));

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonEditar = new InformacionBoton("Agregar Lote", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonEditarLote = GestorComponentes.crearBoton(informacionBotonEditar, estilosBoton);

        botones.add(this.botonEditarLote);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            VentanaMenuInventario ventana = new VentanaMenuInventario("Inventario Local");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            VentanaControlAcceso ventana = new VentanaControlAcceso("Control de Acceso");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonEditarLote.addActionListener(e -> {
            String claveProducto = this.claveProducto.getText();
            String numeroLote = this.numeroLote.getValue().toString();
            String fechaCaducidad;
            //format yyyy-MM-dd
            fechaCaducidad = this.fechaCaducidadLote.getYearChooser().getYear() + "-" + (this.fechaCaducidadLote.getMonthChooser().getMonth() + 1) + "-" + this.fechaCaducidadLote.getDayChooser().getDay();

            String cantidadProductos = this.cantidadProductosLote.getValue().toString();
            
            if (claveProducto.isEmpty() || numeroLote.isEmpty() || fechaCaducidad.isEmpty() || cantidadProductos.isEmpty()){
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Todos los campos son obligatorios",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            } 
            else {
                boolean exito = this.admistradorInventario.registrarLoteProducto(claveProducto, numeroLote, fechaCaducidad, cantidadProductos);

                if (exito){
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Lote registrado con exito",
                        "Exito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Error al registrar el lote",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        });
    }
}
