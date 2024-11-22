package com.ventanas_pdv.RH;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.Ventanas.VentanaMenu;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;

public class VentanaMenuRH extends VentanaMenu {
    private JButton botonAgregarEmpleado;
    private JButton botonConsultarEmpleado;
    private JButton botonEditarEmpleado;
    private JButton botonEliminarEmpleado;


    public VentanaMenuRH(String tituloVentana) {
        super(tituloVentana);
    }

    @Override
    public ArrayList<JButton> crearOpcionesMenu() {
        ArrayList<JButton> opcionesMenu = new ArrayList<JButton>();
        Color colorFondoBotonAgregar = new Color(0, 44, 240);
        Color colorFondoBotonConsultar = new Color(6, 186, 33);
        Color colorFondoBotonEditar = new Color(217, 171, 16 );
        Color colorFondoBotonEliminar = new Color(252, 0, 0 );
        ImageIcon iconoAgregar = new ImageIcon("src/main/resources/iconos/ventas.png");
        ImageIcon iconoConsultar = new ImageIcon("src/main/resources/iconos/logoConsultarEmpleado.png");
        ImageIcon iconoEditar = new ImageIcon("src/main/resources/iconos/logoEditarEmpleado.png");
        ImageIcon iconoEliminar = new ImageIcon("src/main/resources/iconos/logoEliminarEmpleado.png");

        InformacionEstilosBoton estilosBotonAgregar = new InformacionEstilosBoton(Color.WHITE, colorFondoBotonAgregar, iconoAgregar, JButton.CENTER, JButton.BOTTOM);
        InformacionBoton informacionBoton = new InformacionBoton("Agregar Empleado", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonAgregarEmpleado = GestorComponentes.crearBoton(informacionBoton, estilosBotonAgregar);

        InformacionEstilosBoton estilosBotonConsultar = new InformacionEstilosBoton(Color.WHITE, colorFondoBotonConsultar, iconoConsultar, JButton.CENTER, JButton.BOTTOM);
        InformacionBoton informacionBotonConsultar = new InformacionBoton("Consultar Empleado", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonConsultarEmpleado = GestorComponentes.crearBoton(informacionBotonConsultar, estilosBotonConsultar);

        InformacionEstilosBoton estilosBotonEditar = new InformacionEstilosBoton(Color.WHITE, colorFondoBotonEditar, iconoEditar, JButton.CENTER, JButton.BOTTOM);
        InformacionBoton informacionBotonEditar = new InformacionBoton("Editar Empleado", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonEditarEmpleado = GestorComponentes.crearBoton(informacionBotonEditar, estilosBotonEditar);

        InformacionEstilosBoton estilosBotonEliminar = new InformacionEstilosBoton(Color.WHITE, colorFondoBotonEliminar, iconoEliminar, JButton.CENTER, JButton.BOTTOM);
        InformacionBoton informacionBotonEliminar = new InformacionBoton("Eliminar Empleado", BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.botonEliminarEmpleado = GestorComponentes.crearBoton(informacionBotonEliminar, estilosBotonEliminar);

        opcionesMenu.add(this.botonAgregarEmpleado);
        opcionesMenu.add(this.botonConsultarEmpleado);
        opcionesMenu.add(this.botonEditarEmpleado);
        opcionesMenu.add(this.botonEliminarEmpleado);

        return opcionesMenu;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaMenu.obtenerBoton("CerrarSesion").addActionListener(e -> {
            this.cerrarVentana();
        });
    }
}
