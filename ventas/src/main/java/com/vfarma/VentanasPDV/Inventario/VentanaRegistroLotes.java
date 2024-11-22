package com.vfarma.VentanasPDV.Inventario;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaRegistroLotes extends VentanaFormulario{
    private JTextField claveProducto;
    private JSpinner numeroLote;
    private JCalendar fechaCaducidadLote;
    private JSpinner cantidadProductosLote;
    private JButton botonRegistrarLote;
    private JButton botonLimpiar;

    public VentanaRegistroLotes(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        this.claveProducto = GestorFormulario.crearCampoTexto(5);
        this.numeroLote = GestorFormulario.crearCampoNumerico();
        this.fechaCaducidadLote = new JCalendar();
        this.cantidadProductosLote = GestorFormulario.crearCampoNumerico();

        this.formulario.agregarCampo(new InformacionCampoFormulario("Clave Producto", this.claveProducto));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Numero Lote", this.numeroLote));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Fecha Caducidad Lote", this.fechaCaducidadLote));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Cantidad de Productos", this.cantidadProductosLote));

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonEditar = new InformacionBoton("Agregar Lote", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        InformacionBoton informacionBotonLimpiar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));

        this.botonRegistrarLote = GestorComponentes.crearBoton(informacionBotonEditar, estilosBoton);
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonLimpiar, estilosBoton);

        botones.add(this.botonRegistrarLote);
        botones.add(this.botonLimpiar);

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

        this.botonLimpiar.addActionListener(e -> {
            this.claveProducto.setText("");
            this.numeroLote.setValue(0);
            this.fechaCaducidadLote.setDate(new Date());;
            this.cantidadProductosLote.setValue(0);
        });
    }
}
