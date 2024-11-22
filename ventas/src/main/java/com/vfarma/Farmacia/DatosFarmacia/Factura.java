package com.vfarma.Farmacia.DatosFarmacia;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

public final class Factura extends Comprobante {

    public Factura(InformacionVenta informacionVenta) {
        super(informacionVenta);
        super.hojaDocumento = this.crearHojaVacia();
    }

    @Override
    protected Document crearHojaVacia() {
        PdfWriter escritorPDF;
        try {
            escritorPDF = new PdfWriter("Factura.pdf");
            PdfDocument documentoPDF = new PdfDocument(escritorPDF);
            Document documento = new Document(documentoPDF);
            return documento;
        } catch (FileNotFoundException ex) {
        }
        return null;

    }

    @Override
    public Document llenarInformacionComprobante() throws FileNotFoundException {

        // La siguiente factura contiene los siguientes campos:
        String domicilioCliente = informacionVenta.obtenerInformacionCliente().obtenerDomicilioCliente();
        String rfcCliente = informacionVenta.obtenerInformacionCliente().obtenerClaveRFCCliente();
        String claveRFCFarmacia = super.informacionFarmacia.obtenerClaveRFCFarmacia();
        String domicilioSucursalFarmacia = super.informacionFarmacia.obtenerDomicilioSucursalFarmacia();
        String nombreFarmacia = super.informacionFarmacia.obtenerNombreFarmacia();

        InformacionCliente cliente = informacionVenta.obtenerInformacionCliente();
        ArrayList<InformacionProducto> productos = informacionVenta.obtenerCarritoCompras().obtenerTodosProductos();

        //--------------------------------------------------------------------------------------------------------
        super.agregarParrafoTexto("Factura de Compra", 24, true, ColorConstants.BLUE, 20, TextAlignment.CENTER);
        super.agregarParrafoTexto(nombreFarmacia, 18, true, null, 5, TextAlignment.CENTER);
        super.agregarParrafoTexto(domicilioSucursalFarmacia, 12, false, null, 5, TextAlignment.CENTER);
        super.agregarParrafoTexto("RFC: " + claveRFCFarmacia, 12, false, null, 15, TextAlignment.CENTER);

        super.agregarParrafoTexto("Datos del Cliente:", 14, true, null, 10, TextAlignment.LEFT);
        super.agregarParrafoTexto("RFC: " + rfcCliente, 12, false, null, 0, TextAlignment.LEFT);
        super.agregarParrafoTexto("Domicilio: " + domicilioCliente, 12, false, null, 0, TextAlignment.LEFT);

        if (cliente instanceof InformacionPersonaMoral informacionCliente) {

            String regimenFiscal = informacionCliente.obtenerRegimenFiscal();
            String razonSocial = informacionCliente.obtenerRazonSocial();
            super.agregarParrafoTexto("Régimen Fiscal: " + regimenFiscal, 12, false, null, 0, TextAlignment.LEFT);
            super.agregarParrafoTexto("Razón Social: " + razonSocial, 12, false, null, 0, TextAlignment.LEFT);

        } else if (cliente instanceof InformacionPersonaFisica informacionCliente) {

            String nombreCliente = informacionCliente.obtenerNombreCliente();
            String apellidoCliente = informacionCliente.obtenerApellidosCliente();
            super.agregarParrafoTexto("nombreCliente: " + nombreCliente, 12, false, null, 0, TextAlignment.LEFT);
            super.agregarParrafoTexto("apellidoCliente: " + apellidoCliente, 12, false, null, 0, TextAlignment.LEFT);
        }

        super.agregarParrafoTexto("Productos Comprados:", 14, true, null, 10, TextAlignment.LEFT);

        Table tablaProductos = new Table(2);
        tablaProductos.addHeaderCell(new Cell().add(new Paragraph("Producto").setBold()));
        tablaProductos.addHeaderCell(new Cell().add(new Paragraph("Precio").setBold()));

        productos.forEach(producto -> {
            tablaProductos.addCell(new Cell().add(new Paragraph(producto.obtenerNombreProducto())));
            tablaProductos.addCell(new Cell().add(new Paragraph(String.format("$ %.2f", producto.obtenerPrecioProducto()))));
        });

        super.hojaDocumento.add(tablaProductos);

        super.agregarParrafoTexto("Gracias por su compra!", 16, true, ColorConstants.GREEN, 20, TextAlignment.CENTER);
        super.agregarParrafoTexto("Fecha: " + java.time.LocalDate.now(), 12, false, null, 5, TextAlignment.CENTER);

        return super.hojaDocumento;

    }
}