package com.vfarma;

import com.vfarma.VentanasPDV.ControlAcceso.VentanaControlAcceso;
import com.vfarma.VentanasPDV.Inventario.VentanaMenuInventario;

public class Main {
    public static void main(String[] args) {
        VentanaMenuInventario ventana = new VentanaMenuInventario("Inventario Local");
        ventana.iniciarVentana();
        ventana.mostrarVentana();
    }
}
