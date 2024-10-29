package com.vfarma.Modelo;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class Recibo extends Comprobante {

    public Recibo(InformacionVenta informacionVenta) {
        super(informacionVenta);
    }

    @Override
    public void generarComprobante(InformacionVenta informacionVenta) throws FileNotFoundException {
        HashMap<String, String> informacionRecibo = llenarInformacionComprobante(informacionVenta);
        Document reciboCompleto = construirComprobante(informacionRecibo);
        imprimirComprobante(reciboCompleto);
    }

    @Override
    public HashMap<String, String> llenarInformacionComprobante(InformacionVenta informacionVenta) {

        HashMap<String, String> camposgenerales = super.llenarInformacionComprobante(informacionVenta);

        HashMap<String, String> camposParticulares = new HashMap<>();
        InformacionPersonaFisica informacionCliente = (InformacionPersonaFisica) informacionVenta.obtenerInformacionCliente();

        camposParticulares.put("Nombre Cliente", informacionCliente.obtenerNombreCliente());
        camposParticulares.put("Apellido Cliente", informacionCliente.obtenerApellidosCliente());

        return unirHashMaps(camposParticulares, camposgenerales);
    }

    @Override
    public Document construirComprobante(HashMap<String, String> campos) throws FileNotFoundException {

        PdfWriter escritorPDF = new PdfWriter("Recibo");
        PdfDocument documentoPDF = new PdfDocument(escritorPDF);
        Document documento = new Document(documentoPDF);

        documento.add(new Paragraph("Recibo de Compra")
                .setFontSize(20)
                .setBold()
                .setFontColor(ColorConstants.BLUE)
                .setMarginBottom(20));

        documento.add(new Paragraph(campos.get("Nombre Farmacia"))
                .setFontSize(16)
                .setBold()
                .setMarginBottom(5));
        documento.add(new Paragraph(campos.get("Domicilio Sucursal Farmacia"))
                .setFontSize(12)
                .setMarginBottom(5));

        documento.add(new AreaBreak());

         Table tabla = new Table(2);
         tabla.addHeaderCell("Producto");
         tabla.addHeaderCell("Precio");

        super.obtenerProductosComprados().forEach(producto -> {

            tabla.addCell(producto.obtenerNombreProducto());
            tabla.addCell(String.valueOf(producto.obtenerPrecioProducto()));
        });


        documento.add(new AreaBreak());
        documento.add(new Paragraph("Gracias por su compra!")
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.GREEN)
                .setMarginTop(20));
        documento.add(new Paragraph("Fecha: " + java.time.LocalDate.now())
                .setFontSize(12)
                .setMarginTop(5));

        documento.close();
        return documento;

    }

}
