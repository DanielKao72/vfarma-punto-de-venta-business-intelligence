package com.vfarma.Modelo;

import java.util.HashMap;

public class Recibo extends Comprobante{

    @Override
    public void llenarInformacionComprobante(InformacionVenta informacionVenta){
        HashMap<String, String> campos = new HashMap<>();
        campos.put("Domicilio Cliente", informacionVenta.informacionCliente.getDomicilioCliente());
        campos.put("RFC Cliente", informacionVenta.informacionCliente.getClaveRFCCliente());
        
        
    }

    @Override
    public void generarComprobante(){
        
    }

    @Override
    public void imprimir(){
        
    }
    
}
