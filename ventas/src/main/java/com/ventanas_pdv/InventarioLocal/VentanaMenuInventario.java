package com.ventanas_pdv.InventarioLocal;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.Ventanas.VentanaMenu;

public class VentanaMenuInventario extends VentanaMenu {
    private JButton botonAgregarProducto;
    private JButton botonConsultarInventario;
    private JButton botonEditarInventario;

    public VentanaMenuInventario(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public ArrayList<JButton> crearOpcionesMenu() {
        ArrayList<JButton> opcionesMenu = new ArrayList<JButton>();
        Color colorFondoBotonAgregarProducto = new Color(0, 44, 240);
        Color colorFondoBotonConsultarInventario = new Color(6, 186, 33);
        Color colorFondoBotonEditarInventario = new Color(217, 171, 16 );
        ImageIcon iconoAgregar = new ImageIcon("src/main/resources/iconos/ventas.png");
        ImageIcon iconoConsultar = new ImageIcon("src/main/resources/iconos/logoConsultarEmpleado.png");
        ImageIcon iconoEditar = new ImageIcon("src/main/resources/iconos/logoEditarEmpleado.png");

        InformacionEstilosBoton estilosBotonAgregarProducto = new InformacionEstilosBoton(Color.WHITE, colorFondoBotonAgregarProducto, iconoAgregar, JButton.CENTER, JButton.BOTTOM);
        InformacionBoton informacionBoton = new InformacionBoton("Agregar Producto", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonAgregarProducto = GestorComponentes.crearBoton(informacionBoton, estilosBotonAgregarProducto);

        InformacionEstilosBoton estilosBotonConsultarInventario = new InformacionEstilosBoton(Color.WHITE, colorFondoBotonConsultarInventario, iconoConsultar, JButton.CENTER, JButton.BOTTOM); 
        InformacionBoton informacionBotonConsultar = new InformacionBoton("Consultar Inventario", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonConsultarInventario = GestorComponentes.crearBoton(informacionBotonConsultar, estilosBotonConsultarInventario);

        InformacionEstilosBoton estilosBotonEditarInventario = new InformacionEstilosBoton(Color.WHITE, colorFondoBotonEditarInventario, iconoEditar, JButton.CENTER, JButton.BOTTOM);
        InformacionBoton informacionBotonEditar = new InformacionBoton("Editar Inventario", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonEditarInventario = GestorComponentes.crearBoton(informacionBotonEditar, estilosBotonEditarInventario);

        opcionesMenu.add(this.botonAgregarProducto);
        opcionesMenu.add(this.botonConsultarInventario);
        opcionesMenu.add(this.botonEditarInventario);

        return opcionesMenu;
    }
    
    @Override
    public void configurarEventos() {
        this.gestorVentanaMenu.obtenerBoton("CerrarSesion").addActionListener(e -> {
            this.cerrarVentana();
        });
    }

}
