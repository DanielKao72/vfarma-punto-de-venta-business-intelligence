package com.ventanas_pdv;

import com.ventanas_pdv.InventarioLocal.VentanaConsultarInventario;
import com.ventanas_pdv.InventarioLocal.VentanaMenuInventario;
import com.ventanas_pdv.RH.VentanaAgregarEmpleado;
import com.ventanas_pdv.RH.VentanaConsultarEmpleado;
import com.ventanas_pdv.RH.VentanaEditarEmpleado;
import com.ventanas_pdv.RH.VentanaEliminarEmpleado;
import com.ventanas_pdv.RH.VentanaMenuRH;
import com.ventanas_pdv.Ventanas.VentanaMenu;
import com.ventanas_pdv.Ventas.VentanaFormularioFactura;
import com.ventanas_pdv.Ventas.VentanaMenuVentas;
import com.ventanas_pdv.Ventas.VentanaSeleccionCaja;

public class Main {
    public static void main(String[] args) {
        //VentanaFormularioFactura ventana = new VentanaFormularioFactura("Factura");
        VentanaEditarEmpleado ventana = new VentanaEditarEmpleado("Ventas");
        //VentanaSeleccionCaja ventana = new VentanaSeleccionCaja("Seleccionar Caja");
        //VentanaRegistroVenta ventana = new VentanaRegistroVenta("Registro de Venta");
        //VentanaMenuRH ventana = new VentanaMenuRH("Recursos Humanos");
        //VentanaAgregarEmpleado ventana = new VentanaAgregarEmpleado("Agregar Empleado");
        //VentanaConsultarEmpleado ventana = new VentanaConsultarEmpleado("Consultar Empleado");
        //VentanaConsultarInventario ventana = new VentanaConsultarInventario("Consultar Inventario");
        ventana.iniciarVentana();
        ventana.mostrarVentana();
    }
}