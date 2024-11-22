package com.vfarma.Inventario;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionBoton;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;
import com.vfarma.ComponentesVentana.InformacionEstilosBoton;
import com.vfarma.ControlAcceso.VentanaControlAcceso;
import com.vfarma.GestoresComponentesVentana.GestorComponentes;
import com.vfarma.GestoresComponentesVentana.GestorFormulario;
import com.vfarma.Ventanas.VentanaFormulario;

public class VentanaRegistroProducto extends VentanaFormulario {
    private JTextField campoClaveProducto;
    private JTextField campoNombreProducto;
    private JTextField campoPrecioProducto;
    // private JSpinner campoExistenciaProducto;
    private JButton botonAgregarProducto;
    private AdmistradorInventario admistradorInventario;

    public VentanaRegistroProducto(String tituloVentana) {
        super(tituloVentana);
        this.admistradorInventario = new AdmistradorInventario();
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();

        this.campoClaveProducto = GestorFormulario.crearCampoTexto(10);
        this.campoNombreProducto = GestorFormulario.crearCampoTexto(20);
        this.campoPrecioProducto = GestorFormulario.crearCampoTexto(5);
        // this.campoExistenciaProducto = GestorFormulario.crearCampoNumero();

        formulario.agregarCampo(new InformacionCampoFormulario("Clave de Producto:", this.campoClaveProducto));
        formulario.agregarCampo(new InformacionCampoFormulario("Nombre de Producto:", this.campoNombreProducto));
        formulario.agregarCampo(new InformacionCampoFormulario("Precio de Producto:", this.campoPrecioProducto));
        // formulario.agregarCampo(new InformacionCampoFormulario("Existencia de Producto:", this.campoExistenciaProducto));

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonAgregarProducto = new InformacionBoton("Agregar Producto", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonAgregarProducto = GestorComponentes.crearBoton(informacionBotonAgregarProducto, estilosBoton);

        botones.add(this.botonAgregarProducto);

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

        this.botonAgregarProducto.addActionListener(e -> {
            String clave = this.campoClaveProducto.getText();
            String nombre = this.campoNombreProducto.getText();
            String precio = this.campoPrecioProducto.getText();
            // String existencia = this.campoExistenciaProducto.getValue().toString();

            if (clave.isEmpty() || nombre.isEmpty() || precio.isEmpty()){
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Todos los campos son obligatorios",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            } 
            else {
                boolean exito = this.admistradorInventario.registrarNuevoProducto(clave, nombre, precio);

                if (exito){
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Producto registrado con éxito.",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else {
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Error al registrar el producto.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

    }
}
