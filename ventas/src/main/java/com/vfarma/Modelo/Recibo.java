package com.vfarma.Modelo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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

    //@Override
    //public void generarComprobante(InformacionVenta informacionVenta) throws FileNotFoundException {
       // Document reciboCompleto = this.llenarInformacionComprobante();
        //super.enviarAImpresionComprobante(reciboCompleto);
    //}

    @Override
    public Document llenarInformacionComprobante() throws FileNotFoundException {

        // El siguiente recibo contiene los siguientes campos:
        String domicilioCliente = informacionVenta.obtenerInformacionCliente().getDomicilioCliente();
        String rfcCliente = informacionVenta.obtenerInformacionCliente().obtenerClaveRFCCliente();
        String claveRFCFarmacia = this.obtenerInformacionFarmacia().obtenerClaveRFCFarmacia();
        String domicilioSucursalFarmacia = this.obtenerInformacionFarmacia().obtenerDomicilioSucursalFarmacia();
        String nombreFarmacia = this.obtenerInformacionFarmacia().obtenerNombreFarmacia();

        InformacionPersonaFisica informacionCliente = (InformacionPersonaFisica) informacionVenta.obtenerInformacionCliente();
        String nombreCliente = informacionCliente.obtenerNombreCliente();
        String apellidosCliente = informacionCliente.obtenerApellidosCliente();

        ArrayList<Producto> productos = informacionVenta.obtenerCarritoCompras().obtenerTodosProductos();
        //--------------------------------------------------------------------------------------------------------


        PdfWriter escritorPDF = new PdfWriter("Recibo");
        PdfDocument documentoPDF = new PdfDocument(escritorPDF);
        Document documento = new Document(documentoPDF);

        documento.add(new Paragraph("Recibo de Compra")
                .setFontSize(20)
                .setBold()
                .setFontColor(ColorConstants.BLUE)
                .setMarginBottom(20));

        documento.add(new Paragraph(nombreFarmacia)
                .setFontSize(16)
                .setBold()
                .setMarginBottom(5));

        documento.add(new Paragraph(domicilioSucursalFarmacia)
                .setFontSize(12)
                .setMarginBottom(5));

        documento.add(new AreaBreak());

        Table tabla = new Table(2);
        tabla.addHeaderCell("Producto");
        tabla.addHeaderCell("Precio");

        productos.forEach(producto -> {
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
