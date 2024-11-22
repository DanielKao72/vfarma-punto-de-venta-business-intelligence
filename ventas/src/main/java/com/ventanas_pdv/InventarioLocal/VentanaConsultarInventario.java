package com.ventanas_pdv.InventarioLocal;

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

public class VentanaConsultarInventario extends VentanaFormulario {
    private JTextField campoClaveProducto;
    private JTable informacionProducto;
    private JScrollPane tablaInformacionProducto;
    private JButton botonConsultarProducto;
    private JButton botonConsultarTodo;


    public VentanaConsultarInventario(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();

        this.campoClaveProducto = GestorFormulario.crearCampoTexto(20);
        this.informacionProducto = GestorFormulario.crearTabla(new String[] { "Clave de Producto", "Nombre", "Categoria", "Precio", "Existencia" });
        this.tablaInformacionProducto = new JScrollPane(this.informacionProducto);

        formulario.agregarCampo(new InformacionCampoFormulario("Clave de Producto:", this.campoClaveProducto));
        formulario.agregarCampo(new InformacionCampoFormulario("Información del Producto:", this.tablaInformacionProducto));

        return formulario;
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
        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            this.cerrarVentana();
        });
    }
}
