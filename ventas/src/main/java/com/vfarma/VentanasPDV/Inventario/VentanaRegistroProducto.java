package com.vfarma.VentanasPDV.Inventario;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.Farmacia.Inventario.AdministradorInventario;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaRegistroProducto extends VentanaFormulario {
    private JTextField campoClaveProducto;
    private JTextField campoNombreProducto;
    private JTextField campoPrecioProducto;
    private JButton botonAgregarProducto;
    private JButton botonLimpiar;

    public VentanaRegistroProducto(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        this.formulario = new Formulario();
        this.campoClaveProducto = GestorFormulario.crearCampoTexto(10);
        this.campoNombreProducto = GestorFormulario.crearCampoTexto(20);
        this.campoPrecioProducto = GestorFormulario.crearCampoTexto(5);

        this.formulario.agregarCampo(new InformacionCampoFormulario("Clave de Producto:", this.campoClaveProducto));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Nombre de Producto:", this.campoNombreProducto));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Precio de Producto:", this.campoPrecioProducto));

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonAgregarProducto = new InformacionBoton("Agregar Producto", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonAgregarProducto = GestorComponentes.crearBoton(informacionBotonAgregarProducto, estilosBoton);
        InformacionBoton informacionBotonLimpiar = new InformacionBoton("Limpiar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonLimpiar = GestorComponentes.crearBoton(informacionBotonLimpiar, estilosBoton);
        
        botones.add(this.botonAgregarProducto);
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

        this.botonAgregarProducto.addActionListener(e -> {
            String clave = this.campoClaveProducto.getText();
            String nombre = this.campoNombreProducto.getText();
            String precio = this.campoPrecioProducto.getText();
            AdministradorInventario admistradorInventario = AdministradorInventario.obtenerAdministradorInventario();

            if (clave.isEmpty() || nombre.isEmpty() || precio.isEmpty()){
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Todos los campos son obligatorios",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            } 
            else {
                boolean exito = admistradorInventario.registrarNuevoProducto(clave, nombre, precio);

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
