package com.vfarma.Modelo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

public final class Recibo extends Comprobante {

    public Recibo(InformacionVenta informacionVenta) {
        super(informacionVenta);
        super.hojaDocumento = this.crearHojaVacia();
    }

    @Override
    protected Document crearHojaVacia() {
        PdfWriter escritorPDF;
        try {
            escritorPDF = new PdfWriter("Recibo.pdf");
            PdfDocument documentoPDF = new PdfDocument(escritorPDF);
            Document documento = new Document(documentoPDF);
            return documento;
        } catch (FileNotFoundException ex) {
        }
        return null;

    }

    @Override
    public Document llenarInformacionComprobante() throws FileNotFoundException {

        // El siguiente recibo contiene los siguientes campos:
        String domicilioCliente = informacionVenta.obtenerInformacionCliente().obtenerDomicilioCliente();
        String rfcCliente = informacionVenta.obtenerInformacionCliente().obtenerClaveRFCCliente();
        String claveRFCFarmacia = super.informacionFarmacia.obtenerClaveRFCFarmacia();
        String domicilioSucursalFarmacia = super.informacionFarmacia.obtenerDomicilioSucursalFarmacia();
        String nombreFarmacia = super.informacionFarmacia.obtenerNombreFarmacia();

        InformacionCliente cliente = informacionVenta.obtenerInformacionCliente();
        String nombreCliente = "";
        String apellidosCliente = "";

        if (cliente instanceof InformacionPersonaFisica informacionCliente) {
            nombreCliente = informacionCliente.obtenerNombreCliente();
            apellidosCliente = informacionCliente.obtenerApellidosCliente();
        }

        ArrayList<Producto> productos = informacionVenta.obtenerCarritoCompras().obtenerTodosProductos();
        //--------------------------------------------------------------------------------------------------------

        super.agregarParrafoTexto("Recibo de Compra", 20, true, ColorConstants.BLUE, 20, TextAlignment.CENTER);
        super.agregarParrafoTexto(nombreFarmacia, 16, true, null, 5, TextAlignment.CENTER);
        super.agregarParrafoTexto(claveRFCFarmacia, 16, true, null, 5, TextAlignment.CENTER);
        super.agregarParrafoTexto(domicilioSucursalFarmacia, 12, false, null, 5, TextAlignment.CENTER);


        agregarParrafoTexto("Datos del Cliente:", 14, true, null, 10, TextAlignment.CENTER);
        agregarParrafoTexto("Nombre: " + nombreCliente + " " + apellidosCliente, 12, false, null, 0, TextAlignment.CENTER);
        agregarParrafoTexto("RFC: " + rfcCliente, 12, false, null, 0, TextAlignment.CENTER);
        agregarParrafoTexto("Domicilio: " + domicilioCliente, 12, false, null, 0, TextAlignment.CENTER);


        agregarParrafoTexto("Productos Comprados:", 14, true, null, 10, TextAlignment.CENTER);

        Table tablaProductos = new Table(2);
        tablaProductos.addHeaderCell("Producto");
        tablaProductos.addHeaderCell("Precio");

        productos.forEach(producto -> {
            tablaProductos.addCell(producto.obtenerNombreProducto());
            tablaProductos.addCell(String.format("$ %.2f", producto.obtenerPrecioProducto()));
        });

        super.hojaDocumento.add(tablaProductos);

        agregarParrafoTexto("Gracias por su compra!", 14, true, ColorConstants.GREEN, 20, TextAlignment.CENTER);
        agregarParrafoTexto("Fecha: " + java.time.LocalDate.now(), 12, false, null, 5, TextAlignment.CENTER);

        return super.hojaDocumento;
    }

}
