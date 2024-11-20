package com.vfarma.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.vfarma.Modelo.Producto;

public class ConsultasProducto {

    private final BaseDeDatos baseDeDatos;

    public ConsultasProducto() {
        this.baseDeDatos = BaseDeDatos.obtenerInstancia();
    }

    public int contarExistenciaProducto(int id) {
        String consultaSQL = "SELECT ExistenciaTotal FROM productos WHERE ClvProducto = ?";
        int existencia = 0;

        try (Connection conexion = baseDeDatos.obtenerConexionBaseDatos(); PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {

            peticion.setInt(1, id);
            ResultSet resultado = peticion.executeQuery();

            if (resultado.next()) {
                existencia = resultado.getInt("ExistenciaTotal");
            }
        } catch (SQLException e) {
        }

        return existencia;
    }

    public Producto buscarProductoPorID(int id) {
        String consultaSQL = "SELECT p.ClvProducto, p.Nombre, p.Precio, p.ExistenciaTotal, i.FechaCaducidad "
                + "FROM productos p "
                + "JOIN inventario i ON p.ClvProducto = i.ClvProducto "
                + "WHERE p.ClvProducto = ?";

        Producto producto = new Producto();

        try (Connection conexion = baseDeDatos.obtenerConexionBaseDatos(); PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {

            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                int ClvProducto = resultado.getInt("ClvProducto");
                String nombre = resultado.getString("Nombre");
                int precio = resultado.getInt("Precio");
                int existenciaTotal = resultado.getInt("ExistenciaTotal");
                Date fechaCaducidad = resultado.getDate("FechaCaducidad");

                producto.colocarClaveProducto(ClvProducto);
                producto.colocarNombreProducto(nombre);
                producto.colocarPrecioProducto(precio);
                producto.colocarExistenciaProducto(existenciaTotal);
                producto.colocarFechaCaducidad(fechaCaducidad);
            } else {
                System.out.println("No se encontró ningún producto con ID: " + id);
            }
        } catch (SQLException e) {
        }

        return producto;
    }

    public boolean restarExistenciaProducto(int id, int cantidadARestar) {
        String consultaSQL = "UPDATE productos SET ExistenciaTotal = ExistenciaTotal - ? WHERE ClvProducto = ? AND ExistenciaTotal >= ?";
        boolean exito = false;

        try (Connection conexion = baseDeDatos.obtenerConexionBaseDatos(); PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {

            statement.setInt(1, cantidadARestar);
            statement.setInt(2, id);
            statement.setInt(3, cantidadARestar);

            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                exito = true;
                System.out.println("Existencia restada exitosamente.");
            } else {
                System.out.println("No se pudo restar la existencia. Verifica el ID y la cantidad.");
            }
        } catch (SQLException e) {
        }

        return exito;
    }

}
