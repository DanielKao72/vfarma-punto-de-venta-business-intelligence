package com.vfarma.Modelo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

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
import com.itextpdf.layout.Document;

public abstract class Comprobante {

    private final InformacionFarmacia informacionFarmacia;
    private final InformacionVenta informacionVenta;

    public Comprobante(InformacionVenta informacionVenta) {
        this.informacionFarmacia = new InformacionFarmacia();
        this.informacionVenta = informacionVenta;
    }

    protected ArrayList<Producto> obtenerProductosComprados(){
        return this.informacionVenta.obtenerCarritoCompras().obtenerTodosProductos();
    }

    public abstract void generarComprobante(InformacionVenta informacionVenta) throws FileNotFoundException;

    protected HashMap<String, String> llenarInformacionComprobante(InformacionVenta informacionVenta) {
        HashMap<String, String> campos = new HashMap<>();
        campos.put("Domicilio Cliente", informacionVenta.obtenerInformacionCliente().getDomicilioCliente());
        campos.put("RFC Cliente", informacionVenta.obtenerInformacionCliente().obtenerClaveRFCCliente());
        campos.put("Clave RFC Farmacia", this.informacionFarmacia.obtenerClaveRFCFarmacia());
        campos.put("Domicilio Sucursal Farmacia", this.informacionFarmacia.obtenerDomicilioSucursalFarmacia());
        campos.put("Nombre Farmacia", this.informacionFarmacia.obtenerNombreFarmacia());
        return campos;
    }

    protected abstract Document construirComprobante(HashMap<String, String> campos) throws FileNotFoundException;

    protected HashMap<String, String> unirHashMaps(HashMap<String, String> map1, HashMap<String, String> map2) {
        HashMap<String, String> HashMapResultante = new HashMap<>(map1);

        for (String key : map2.keySet()) {
            HashMapResultante.put(key, map2.get(key));
        }

        return HashMapResultante;
    }

    protected void imprimirComprobante(Document comprobante) {
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
            e.printStackTrace();
        }
    }

}
