package com.vfarma;

import com.vfarma.VentanasPDV.RecursosHumanos.VentanaMenuRH;
import com.vfarma.VentanasPDV.Ventas.VentanaMenuVenta;

public class Main {
    public static void main(String[] args) {
        VentanaMenuVenta ventanaMenuRH = new VentanaMenuVenta("Recursos Humanos");
        ventanaMenuRH.iniciarVentana();
        ventanaMenuRH.mostrarVentana();
    }
}
