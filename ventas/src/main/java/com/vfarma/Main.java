package com.vfarma;

import com.vfarma.RegistroVenta.VentanaMenuVentas;

public class Main {

    public static void main(String[] args) {
        VentanaMenuVentas ventana = new VentanaMenuVentas("Ventas");

        ventana.iniciarVentana();
        ventana.mostrarVentana();
    }
}
