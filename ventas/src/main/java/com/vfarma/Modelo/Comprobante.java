package com.vfarma.Modelo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

public abstract class Comprobante {

    protected final InformacionFarmacia informacionFarmacia;
    protected final InformacionVenta informacionVenta;
    protected Document hojaDocumento;

    public Comprobante(InformacionVenta informacionVenta) {
        this.informacionFarmacia = new InformacionFarmacia();
        this.informacionVenta = informacionVenta;
    }

    protected abstract Document crearHojaVacia();

    public abstract Document llenarInformacionComprobante() throws FileNotFoundException;

    public void enviarAImpresion(Document comprobante) {
        comprobante.close();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            DocFlavor docFlavor = DocFlavor.INPUT_STREAM.PDF;
            Doc documento = new SimpleDoc(inputStream, docFlavor, null);

            PrintRequestAttributeSet atributos = new HashPrintRequestAttributeSet();
            atributos.add(MediaSizeName.ISO_A4);
            atributos.add(new Copies(1));
            atributos.add(OrientationRequested.PORTRAIT);

            PrintService servicioImpresion = PrintServiceLookup.lookupDefaultPrintService();

            if (servicioImpresion != null) {
                DocPrintJob trabajoImpresion = servicioImpresion.createPrintJob();
                trabajoImpresion.print(documento, atributos);
                System.out.println("Comprobante enviada a la impresora con éxito.");
            } else {
                System.out.println("No se encontró una impresora predeterminada.");
            }

            inputStream.close();

        } catch (IOException | PrintException | java.io.IOException e) {
        }
    }

    protected void agregarParrafoTexto(String texto, int fontSize, boolean esNegrita, Color color, float marginBottom, TextAlignment alineacion) {
        Paragraph parrafo = new Paragraph(texto)
                .setFontSize(fontSize)
                .setMarginBottom(marginBottom)
                .setTextAlignment(alineacion);

        if (esNegrita) {
            parrafo.setBold();
        }

        if (color != null) {
            parrafo.setFontColor(color);
        }

        this.hojaDocumento.add(parrafo);
    }

    protected void agregarSaltoDeLinea() {
        this.hojaDocumento.add(new AreaBreak());
    }

}
