package com.vfarma;

import com.vfarma.VentanasPDV.RecursosHumanos.VentanaMenuRH;

public class Main {
    public static void main(String[] args) {
        VentanaMenuRH   ventana = new VentanaMenuRH("Recursos Humanos");



        //VentanaMenuInventario ventana = new VentanaMenuInventario("Inventario Local");
        ventana.iniciarVentana();
        ventana.mostrarVentana();
    }
}
