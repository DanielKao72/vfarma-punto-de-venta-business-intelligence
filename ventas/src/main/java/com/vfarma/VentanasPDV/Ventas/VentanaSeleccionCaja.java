package com.vfarma.VentanasPDV.Ventas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.ventanas_pdv.ComponentesVentana.Formulario;
import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionCampoFormulario;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.GestoresComponentesVentana.GestorComponentes;
import com.ventanas_pdv.GestoresComponentesVentana.GestorFormulario;
import com.ventanas_pdv.Ventanas.VentanaFormulario;
import com.vfarma.Farmacia.Ventas.Cajero;
import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;

public class VentanaSeleccionCaja extends VentanaFormulario {
    private JComboBox<String> seleccionCaja;
    private JButton botonContinuar;

    public VentanaSeleccionCaja(String titulo) {
        super(titulo);
    }

    @Override
    public Formulario crearCamposFormulario() {
        this.formulario = new Formulario();
        Cajero cajero = Cajero.obtenerCajero();
        ArrayList<String> cajasDisponibles = cajero.consultasCaja.obtenerNombresCajasDisponibles();

        if (cajasDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cajas disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        this.seleccionCaja = GestorFormulario.creaListaOpciones(cajasDisponibles.toArray(String[]::new));

        this.formulario.agregarCampo(new InformacionCampoFormulario("Selecciona la caja:", this.seleccionCaja));

        return this.formulario;
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
            VentanaControlAcceso ventanaControlAcceso = new VentanaControlAcceso("Control de Acceso");
            ventanaControlAcceso.iniciarVentana();
            ventanaControlAcceso.mostrarVentana();
            this.cerrarVentana();
        });

        this.gestorVentanaFormulario.obtenerBoton("Volver").addActionListener(e -> {
            VentanaMenuVenta ventanaMenuVentas = new VentanaMenuVenta("Menú Ventas");
            ventanaMenuVentas.iniciarVentana();
            ventanaMenuVentas.mostrarVentana();
            this.cerrarVentana();
        });

        this.botonContinuar.addActionListener(e -> {
            String cajaSeleccionada = (String) this.seleccionCaja.getSelectedItem();
            Cajero cajero = Cajero.obtenerCajero();
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
