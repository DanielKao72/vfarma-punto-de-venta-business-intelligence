package com.vfarma.BaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vfarma.Modelo.LoteProducto;
import com.vfarma.Modelo.Producto;
import com.vfarma.Modelo.LoteProducto;

public class ControlInventario {
    private final BaseDeDatos baseDeDatos;
    private Connection conexion;

    public ControlInventario(){
        this.baseDeDatos = BaseDeDatos.obtenerInstancia();
        this.conexion = baseDeDatos.obtenerConexionBaseDatos();
    }

    public boolean validarExistenciaClaveProductoEnInventario(String clave){
        String consultaSQL = "SELECT * FROM Productos WHERE ClvProducto = '" + clave + "'";
        boolean existe = false;

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            ResultSet resultado = peticion.executeQuery();

            if (resultado.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }  
    
    public Producto buscarProductoPorClaveEnInventario(String clave){
        String consultaSQL = "SELECT * FROM Productos WHERE ClvProducto = '" + clave + "'";
        Producto producto = new Producto();

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            ResultSet resultado = peticion.executeQuery();

            if (resultado.next()) {
                int claveProducto = resultado.getInt("ClvProducto");
                String nombre = resultado.getString("Nombre");
                float precio = resultado.getFloat("Precio");
                int existencia = resultado.getInt("ExistenciaTotal");

                producto.colocarClaveProducto(claveProducto);
                producto.colocarNombreProducto(nombre);
                producto.colocarPrecioProducto(precio);
                producto.colocarExistenciaProducto(existencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }   

    public boolean registrarNuevoProductoEnInventario(Producto producto){
        String consultaSQL = "INSERT INTO Productos (ClvProducto, Nombre, Precio, ExistenciaTotal) VALUES ('" + 
        producto.obtenerClaveProducto() + "', '" + producto.obtenerNombreProducto() + "', " + producto.obtenerPrecioProducto() + ", " + producto.obtenerExistenciaProducto() + ")";
        boolean exito = false;

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public List<Producto> obtenerTodosLosProductosEnInventario(){
        String consultaSQL = "SELECT * FROM Productos";
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            ResultSet resultado = peticion.executeQuery();

            while (resultado.next()) {
                int clave = resultado.getInt("ClvProducto");
                String nombre = resultado.getString("Nombre");
                float precio = resultado.getFloat("Precio");
                int existencia = resultado.getInt("ExistenciaTotal");

                Producto producto = new Producto(clave, nombre, precio, existencia);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public boolean registrarProductoInventarioEnInventario(LoteProducto productoInventario){
        String consultaSQL = "INSERT INTO Inventario (ClvProducto, Lote, FechaCaducidad, Cantidad) VALUES ('" +
        productoInventario.obtenerClaveProducto() + "', '" + productoInventario.obtenerLote() + "', '" + productoInventario.obtenerFechaCaducidad() + "', " + productoInventario.obtenerCantidad() + ")";
        boolean exito = false;

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exito;
    }

    public boolean actualizarExistenciaProductoEnInventario(String clave, String cantidad) {
        String consultaSQL = "UPDATE Productos SET ExistenciaTotal = ExistenciaTotal + " + cantidad + " WHERE ClvProducto = " + clave;
        boolean exito = false;

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exito;
    }
}
