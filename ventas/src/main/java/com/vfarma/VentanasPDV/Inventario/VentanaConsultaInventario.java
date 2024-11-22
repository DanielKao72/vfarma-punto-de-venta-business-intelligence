package com.vfarma.VentanasPDV.Inventario;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaConsultaInventario extends VentanaFormulario {
    private JTextField campoClaveProducto;
    private JTable informacionProducto;
    private JScrollPane tablaInformacionProducto;
    private JButton botonConsultarProducto;
    private JButton botonConsultarTodo;

    public VentanaConsultaInventario(String tituloVentana) {
        super(tituloVentana);
        this.formulario = this.crearCamposFormulario();
    }

    @Override
    public Formulario crearCamposFormulario() {
        this.campoClaveProducto = GestorFormulario.crearCampoTexto(20);
        this.informacionProducto = GestorFormulario.crearTabla(new String[] { "Clave de Producto", "Nombre", "Categoria", "Precio", "Existencia" });
        this.tablaInformacionProducto = new JScrollPane(this.informacionProducto);

        this.formulario.agregarCampo(new InformacionCampoFormulario("Clave de Producto:", this.campoClaveProducto));
        this.formulario.agregarCampo(new InformacionCampoFormulario("Información del Producto:", this.tablaInformacionProducto));

        return this.formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonConsultarProducto = new InformacionBoton("Consultar Producto", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonConsultarProducto = GestorComponentes.crearBoton(informacionBotonConsultarProducto, estilosBoton);

        InformacionBoton informacionBotonConsultarTodo = new InformacionBoton("Consultar Todo", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonConsultarTodo = GestorComponentes.crearBoton(informacionBotonConsultarTodo, estilosBoton);

        botones.add(this.botonConsultarProducto);
        botones.add(this.botonConsultarTodo);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            VentanaControlAcceso ventanaControlAcceso = new VentanaControlAcceso("Control de Acceso");
            ventanaControlAcceso.iniciarVentana();
            ventanaControlAcceso.mostrarVentana();
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            VentanaMenuInventario ventana = new VentanaMenuInventario("Inventario");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonConsultarProducto.addActionListener(e -> {
            // Consultar producto
        });

        this.botonConsultarTodo.addActionListener(e -> {
            // Consultar todos los productos
        });
    } 
}
