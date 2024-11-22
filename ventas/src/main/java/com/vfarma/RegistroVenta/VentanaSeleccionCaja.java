package com.vfarma.RegistroVenta;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.vfarma.ComponentesVentana.Formulario;
import com.vfarma.ComponentesVentana.InformacionBoton;
import com.vfarma.ComponentesVentana.InformacionCampoFormulario;
import com.vfarma.ComponentesVentana.InformacionEstilosBoton;
import com.vfarma.GestoresComponentesVentana.GestorComponentes;
import com.vfarma.GestoresComponentesVentana.GestorFormulario;
import com.vfarma.Ventanas.VentanaFormulario;

public class VentanaSeleccionCaja extends VentanaFormulario {

    private JComboBox<String> seleccionCaja;
    private JButton botonContinuar;

    public VentanaSeleccionCaja(String titulo) {
        super(titulo);
    }

    @Override
    public Formulario crearCamposFormulario() {
        Formulario formulario = new Formulario();

        Cajero cajero = Cajero.obtenerInstancia();
        ArrayList<String> cajasDisponibles = cajero.consultasCaja.obtenerNombresCajasDisponibles();

        if (cajasDisponibles.isEmpty()) {
            System.out.println("No hay cajas disponibles.");
        }

        this.seleccionCaja = GestorFormulario.creaListaOpciones(cajasDisponibles.toArray(new String[0]));

        formulario.agregarCampo(new InformacionCampoFormulario("Selecciona la caja:", this.seleccionCaja));

        return formulario;
    }

    @Override
    public ArrayList<JButton> crearBotones() {
        ArrayList<JButton> botones = new ArrayList<>();

        InformacionEstilosBoton estilosBoton = new InformacionEstilosBoton(Color.WHITE, new Color(66, 7, 124));
        InformacionBoton informacionBotonContinuar = new InformacionBoton("Continuar", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        this.botonContinuar = GestorComponentes.crearBoton(informacionBotonContinuar, estilosBoton);

        botones.add(this.botonContinuar);

        return botones;
    }

    @Override
    public void configurarEventos() {
        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            Cajero cajero = Cajero.obtenerInstancia();
            cajero.consultasCaja.desOcuparCaja(cajero.obtenerNombreCaja());

            VentanaMenuVentas ventanaMenuVentas = new VentanaMenuVentas("Menú Ventas");
            ventanaMenuVentas.iniciarVentana();
            ventanaMenuVentas.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonContinuar.addActionListener(e -> {

            Cajero cajero = Cajero.obtenerInstancia();
            String cajaSeleccionada = (String) this.seleccionCaja.getSelectedItem();
            cajero.colocarNombreCaja(cajaSeleccionada);
            cajero.consultasCaja.ocuparCaja(cajaSeleccionada);

            VentanaRegistroVenta ventana = new VentanaRegistroVenta("Registro de Venta");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();

        });
    }
}
