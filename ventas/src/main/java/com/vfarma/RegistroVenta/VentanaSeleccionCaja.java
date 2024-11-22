package com.vfarma.RegistroVenta;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
    private final Formulario formulario;

    public VentanaSeleccionCaja(String titulo) {
        super(titulo);
        this.formulario = new Formulario();
    }

    @Override
    public Formulario crearCamposFormulario() {

        Cajero cajero = Cajero.obtenerCajero();
        ArrayList<String> cajasDisponibles = cajero.consultasCaja.obtenerNombresCajasDisponibles();

        if (cajasDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cajas disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        this.seleccionCaja = GestorFormulario.creaListaOpciones(cajasDisponibles.toArray(String[]::new));

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
        Cajero cajero = Cajero.obtenerCajero();

        this.gestorVentanaFormulario.obtenerBoton("CerrarSesion").addActionListener(e -> {
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {

            cajero.consultasCaja.desOcuparCaja(cajero.obtenerNombreCaja());

            // Regresar a la ventana de menú de ventas
            VentanaMenuVentas ventanaMenuVentas = new VentanaMenuVentas("Menú Ventas");
            ventanaMenuVentas.iniciarVentana();
            ventanaMenuVentas.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonContinuar.addActionListener(e -> {

            String cajaSeleccionada = (String) this.seleccionCaja.getSelectedItem();
            cajero.colocarNombreCaja(cajaSeleccionada);
            cajero.consultasCaja.ocuparCaja(cajaSeleccionada);

            // Avanzar a la ventana de registro de venta
            VentanaRegistroVenta ventana = new VentanaRegistroVenta("Registro de Venta");
            ventana.iniciarVentana();
            ventana.mostrarVentana();
            this.cerrarVentana();

        });
    }
}
