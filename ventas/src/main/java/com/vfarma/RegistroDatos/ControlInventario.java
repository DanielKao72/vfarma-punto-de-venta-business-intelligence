package com.vfarma.RegistroDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vfarma.Farmacia.DatosFarmacia.InformacionProducto;
import com.vfarma.Farmacia.DatosFarmacia.InformacionLoteProducto;
import com.vfarma.Almacenamiento.ConexionBaseDeDatos;

public class ControlInventario {
    private final ConexionBaseDeDatos baseDeDatos;
    private Connection conexion;

    public ControlInventario(){
        baseDeDatos = ConexionBaseDeDatos.obtenerBaseDeDatos();
        conexion = baseDeDatos.abrirConexion();
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
    
    public InformacionProducto buscarProductoPorClaveEnInventario(String clave){
        String consultaSQL = "SELECT * FROM Productos WHERE ClvProducto = '" + clave + "'";
        InformacionProducto producto = new InformacionProducto();

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

    public boolean registrarNuevoProductoEnInventario(InformacionProducto producto){
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

    public List<InformacionProducto> obtenerTodosLosProductosEnInventario(){
        String consultaSQL = "SELECT * FROM Productos";
        List<InformacionProducto> productos = new ArrayList<>();

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            ResultSet resultado = peticion.executeQuery();

            while (resultado.next()) {
                int clave = resultado.getInt("ClvProducto");
                String nombre = resultado.getString("Nombre");
                float precio = resultado.getFloat("Precio");
                int existencia = resultado.getInt("ExistenciaTotal");

                InformacionProducto producto = new InformacionProducto(clave, nombre, precio, existencia);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public boolean registrarProductoInventarioEnInventario(InformacionLoteProducto productoInventario){
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
