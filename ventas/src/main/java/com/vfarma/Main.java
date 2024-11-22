package com.vfarma;

import com.vfarma.ControlAcceso.VentanaControlAcceso;
import com.vfarma.Inventario.VentanaConsultarInventario;
import com.vfarma.Inventario.VentanaMenuInventario;
import com.vfarma.Inventario.VentanaRegistroProducto;
// import com.vfarma.RH.VentanaAgregarEmpleado;
// import com.vfarma.RH.VentanaConsultarEmpleado;
// import com.vfarma.RH.VentanaEditarEmpleado;
// import com.vfarma.RH.VentanaEliminarEmpleado;
// import com.vfarma.RH.VentanaMenuRH;
import com.vfarma.Ventanas.VentanaMenu;
// import com.vfarma.Ventas.VentanaFormularioFactura;
// import com.vfarma.Ventas.VentanaMenuVentas;
// import com.vfarma.Ventas.VentanaSeleccionCaja;

public class Main {
    public static void main(String[] args) {
        //VentanaFormularioFactura ventana = new VentanaFormularioFactura("Factura");
        //VentanaMenuVentas ventana = new VentanaMenuVentas("Ventas");
        //VentanaSeleccionCaja ventana = new VentanaSeleccionCaja("Seleccionar Caja");
        //VentanaRegistroVenta ventana = new VentanaRegistroVenta("Registro de Venta");
        //VentanaMenuRH ventana = new VentanaMenuRH("Recursos Humanos");
        //VentanaAgregarEmpleado ventana = new VentanaAgregarEmpleado("Agregar Empleado");
        // VentanaConsultarEmpleado ventana = new VentanaConsultarEmpleado("Consultar Empleado");
        VentanaMenuInventario ventana = new VentanaMenuInventario("Inventario Local");
        //VentanaControlAcceso ventana = new VentanaControlAcceso("Control de Acceso");
        //VentanaMenuInventario ventana = new VentanaMenuInventario("Inventario Local");
        //VentanaEditarEmpleado ventana = new VentanaEditarEmpleado("Editar Empleado");
        //VentanaRegistroProducto ventana = new VentanaRegistroProducto("Registro de Producto");
        ventana.iniciarVentana();
        ventana.mostrarVentana();
    }
}