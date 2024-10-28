package com.vfarma.Modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

public class Factura extends Comprobante {

    public Factura(InformacionVenta informacionVenta) {
        super(informacionVenta);
    }

    @Override
    public void generarComprobante(InformacionVenta informacionVenta) throws FileNotFoundException {
        HashMap<String, String> informacionRecibo = llenarInformacionComprobante(informacionVenta);
        Document reciboCompleto = construirComprobante(informacionRecibo);
        super.imprimirComprobante(reciboCompleto);
    }

    @Override
    public HashMap<String, String> llenarInformacionComprobante(InformacionVenta informacionVenta) {

        HashMap<String, String> camposgenerales = super.llenarInformacionComprobante(informacionVenta);

        HashMap<String, String> camposParticulares = new HashMap<>();
        InformacionPersonaMoral informacionCliente = (InformacionPersonaMoral) informacionVenta.informacionCliente;

        camposParticulares.put("Regimen Fiscal", informacionCliente.obtenerRegimenFiscal());
        camposParticulares.put("Razon Social", informacionCliente.obtenerRazonSocial());

        return unirHashMaps(camposParticulares, camposgenerales);
    }

    @Override
    public Document construirComprobante(HashMap<String, String> campos) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter("Factura");
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Factura de Compra")
                .setFontSize(24)
                .setBold()
                .setFontColor(ColorConstants.BLUE)
                .setMarginBottom(20)
                .setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph(campos.get("Nombre Farmacia"))
                .setFontSize(18)
                .setBold()
                .setMarginBottom(5)
                .setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph(campos.get("Domicilio Sucursal Farmacia"))
                .setFontSize(12)
                .setMarginBottom(5)
                .setTextAlignment(TextAlignment.CENTER));

        document.add(new AreaBreak());

        document.add(new Paragraph("Datos del Cliente:")
                .setFontSize(14)
                .setBold()
                .setMarginTop(10));

        document.add(new AreaBreak());

        document.add(new Paragraph("Productos Comprados:")
                .setFontSize(14)
                .setBold()
                .setMarginTop(10));

        String productosComprados = campos.get("Productos Comprados");
        String[] productos = productosComprados.split(", ");

        Table table = new Table(2);
        table.addHeaderCell("Producto");
        table.addHeaderCell("Cantidad");

        for (String producto : productos) {
            table.addCell(producto);
            table.addCell("1");
        }

        document.add(table);

        document.add(new AreaBreak());
        document.add(new Paragraph("Gracias por su compra!")
                .setFontSize(16)
                .setBold()
                .setFontColor(ColorConstants.GREEN)
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Fecha: " + java.time.LocalDate.now())
                .setFontSize(12)
                .setMarginTop(5)
                .setTextAlignment(TextAlignment.CENTER));

        return document;

    }

    

    private void agregarCamposAlDocumento(Document document, Map<String, String> campos) throws IOException {
        Table table = new Table(2);
        for (Map.Entry<String, String> entry : campos.entrySet()) {
            table.addCell(new Paragraph(entry.getKey()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontSize(12));
            table.addCell(new Paragraph(entry.getValue()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)).setFontSize(12));
        }
        document.add(table);
        document.add(new Paragraph("\n"));
    }
}
