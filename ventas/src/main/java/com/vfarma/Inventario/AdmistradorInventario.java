package com.vfarma.Inventario;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import com.vfarma.BaseDatos.ControlInventario;
import com.vfarma.Modelo.Producto;
import com.vfarma.Modelo.LoteProducto;

public class AdmistradorInventario {
    private final ControlInventario controlInventario;

    public AdmistradorInventario(){
        this.controlInventario = new ControlInventario();
    }

    private boolean validarExistenciaDeClave(String clave){
        boolean existe = false;
        existe = this.controlInventario.validarExistenciaClaveProductoEnInventario(clave);
        return existe;
    }

    private boolean validarPrecio(String precio){
        boolean precioValido = true;
        try {
            Double.parseDouble(precio);
            if (Double.parseDouble(precio) < 0){
                precioValido = false;
            }
        } catch (NumberFormatException e) {
            precioValido = false;
        }

        return precioValido;
    }

    private boolean validarExistencia(String existencia){
        boolean existe = true;
        try {
            Integer.parseInt(existencia);
            if (Integer.parseInt(existencia) < 0){
                existe = false;
            }
        } catch (NumberFormatException e) {
            existe = false;
        }
        return existe;
    }

    private boolean validarCanitdad(String cantidad){
        boolean cantidadValida = true;
        try {
            Integer.parseInt(cantidad);
            if (Integer.parseInt(cantidad) < 0){
                cantidadValida = false;
            }
        } catch (NumberFormatException e) {
            cantidadValida = false;
        }
        return cantidadValida;
    }

    private boolean validarFecha(String fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        boolean fechaValida = false;
        try {
            sdf.parse(fecha);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaActual = new Date();
            Date fechaIngresada = sdf2.parse(fecha);
            if (fechaIngresada.after(fechaActual)){
                fechaValida = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fechaValida;
    }

    private boolean actualizarExistenciaProducto(String clave, String cantidad){
        boolean exito = false;
        exito = this.controlInventario.actualizarExistenciaProductoEnInventario(clave, cantidad);
        return exito;
    }

    public boolean registrarNuevoProducto(String clave, String nombre, String precio){
        boolean exito = false;
        if (!validarExistenciaDeClave(clave) && validarPrecio(precio)){
            Producto producto = new Producto(Integer.parseInt(clave), nombre, (float)Double.parseDouble(precio), 0);
            exito = this.controlInventario.registrarNuevoProductoEnInventario(producto); 
        }
        return exito;
    }

    public Producto obtenerProductoPorClave(String clave){
        Producto producto = new Producto();
        if (validarExistenciaDeClave(clave)){
            producto = this.controlInventario.buscarProductoPorClaveEnInventario(clave);
        }
        else{
            producto = null;
        }
        return producto;
    }

    public List<Producto> obtenerTodosLosProductos(){
        List<Producto> productos = new ArrayList<>();
        productos = this.controlInventario.obtenerTodosLosProductosEnInventario();
        return productos;
    }

    public boolean registrarLoteProducto(String clave, String numlote, String fechaCaducidad, String cantidad){
        boolean exito = false;
        if (validarExistenciaDeClave(clave) && validarFecha(fechaCaducidad) && validarCanitdad(cantidad)){
            Date fecha = new Date();
            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaCaducidad);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String fechaSQL = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
            
            LoteProducto lote = new LoteProducto(Integer.parseInt(clave), Integer.parseInt(numlote), fechaSQL, Integer.parseInt(cantidad));
            exito = this.controlInventario.registrarProductoInventarioEnInventario(lote);

            if (exito){
                exito = actualizarExistenciaProducto(clave, cantidad);
            }
        }
        return exito;
    }
}
