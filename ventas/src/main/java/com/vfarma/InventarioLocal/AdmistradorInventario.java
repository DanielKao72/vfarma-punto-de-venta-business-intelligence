package com.vfarma.InventarioLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import com.vfarma.BaseDatos.ControlInventario;
import com.vfarma.Modelo.Producto;

public class AdmistradorInventario {
    private final ControlInventario controlInventario;

    public AdmistradorInventario(){
        this.controlInventario = new ControlInventario();
    }

    private boolean validarExistenciaDeClave(String clave){
        return this.controlInventario.validarExistenciaDeClave(clave);
    }

    private boolean validarPrecio(String precio){
        try {
            Double.parseDouble(precio);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validarExistencia(String existencia){
        try {
            Integer.parseInt(existencia);
            if (Integer.parseInt(existencia) < 0){
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validarFechaCaducidad(String fechaCaducidad){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(fechaCaducidad);
            return true; 
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validarCategoria(String categoria){
        return this.controlInventario.validarCategoria(categoria);
    }

    // private String obtenerCategoria(String categoria){
    //     return null;
    // }

    public void registrarNuevoProducto(String nombre, String clave, String precio, String categoria){
        if (!validarExistenciaDeClave(clave) && validarPrecio(precio) && validarCategoria(categoria)){
            this.controlInventario.registrarNuevoProducto(nombre, clave, Double.parseDouble(precio), Integer.parseInt(categoria));    
        }
    }

    public Producto obtenerProductoPorClave(String clave){
        if (validarExistenciaDeClave(clave)){
            return this.controlInventario.buscarProductoPorClave(clave);
        }
        return null;
    }

    public List<Producto> obtenerTodosLosProductos(){
        return this.controlInventario.obtenerTodosLosProductos();
    }

    public void actualizarProducto(String clave, String precio, String existencia, String fechaCaducidad){
        if (validarExistenciaDeClave(clave) && validarPrecio(precio) && validarExistencia(existencia) && validarFechaCaducidad(fechaCaducidad)){
            this.controlInventario.actualizarProducto(clave, Integer.parseInt(precio), Integer.parseInt(existencia), fechaCaducidad);
        }
    }
}
