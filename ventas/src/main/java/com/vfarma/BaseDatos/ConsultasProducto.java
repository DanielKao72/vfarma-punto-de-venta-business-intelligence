package com.vfarma.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vfarma.Modelo.Producto;

public class ConsultasProducto {

    private final BaseDeDatos baseDeDatos;
    private Connection conexion;

    public ConsultasProducto() {
        this.baseDeDatos = BaseDeDatos.obtenerInstancia();

        this.conexion = baseDeDatos.obtenerConexionBaseDatos();

    }

    public int contarExistenciaProducto(int id) {
        String consultaSQL = "SELECT ExistenciaTotal FROM productos WHERE ClvProducto = ?";
        int existencia = 0;

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setInt(1, id);
            ResultSet resultado = peticion.executeQuery();

            if (resultado.next()) {
                existencia = resultado.getInt("ExistenciaTotal");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existencia;
    }

    public Producto buscarProductoPorID(int id) {
        String consultaSQL = "SELECT p.ClvProducto, p.Nombre, p.Precio, p.ExistenciaTotal, i.FechaCaducidad "
                + "FROM productos p "
                + "JOIN inventario i ON p.ClvProducto = i.ClvProducto "
                + "WHERE p.ClvProducto = ?";

        Producto producto = new Producto();

        try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                int ClvProducto = resultado.getInt("ClvProducto");
                String nombre = resultado.getString("Nombre");
                int precio = resultado.getInt("Precio");
                int existenciaTotal = resultado.getInt("ExistenciaTotal");
                //Date fechaCaducidad = resultado.getDate("FechaCaducidad");

                producto.colocarClaveProducto(ClvProducto);
                producto.colocarNombreProducto(nombre);
                producto.colocarPrecioProducto(precio);
                producto.colocarExistenciaProducto(existenciaTotal);
            } else {
                System.out.println("No se encontró ningún producto con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    public boolean restarExistenciaProducto(int id, int cantidadARestar) {
        String consultaSQL = "UPDATE productos SET ExistenciaTotal = ExistenciaTotal - ? WHERE ClvProducto = ? AND ExistenciaTotal >= ?";
        boolean exito = false;

        try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            statement.setInt(1, cantidadARestar);
            statement.setInt(2, id);
            statement.setInt(3, cantidadARestar);

            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                exito = true;
            } else {
                System.out.println("No se pudo restar la existencia. Verifica el ID y la cantidad.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exito;
    }

    public ArrayList<Producto> obtenerNombreProductosEnExistencia() {
    String consultaSQL = "SELECT ClvProducto, Nombre, Precio, ExistenciaTotal FROM productos WHERE ExistenciaTotal > 0";
    ArrayList<Producto> productosDisponibles = new ArrayList<>();

    try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            int clave = resultado.getInt("ClvProducto");
            String nombre = resultado.getString("Nombre");
            int precio = resultado.getInt("Precio");
            int existencia = resultado.getInt("ExistenciaTotal");

            Producto producto = new Producto(clave, nombre, precio, existencia);
            productosDisponibles.add(producto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return productosDisponibles;
}

// Método para obtener el precio de un producto por su ID
public Float obtenerPrecioProductoPorId(int idProducto) {
    String consultaSQL = "SELECT Precio FROM productos WHERE ClvProducto = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
        statement.setInt(1, idProducto);
        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {
            return resultado.getFloat("Precio");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // Retorna null si no se encuentra el producto o si ocurre un error
}

public Producto obtenerProductoPorId(int idProducto) {
    String consultaSQL = "SELECT p.ClvProducto, p.Nombre, p.Precio, p.ExistenciaTotal, i.FechaCaducidad "
            + "FROM productos p "
            + "JOIN inventario i ON p.ClvProducto = i.ClvProducto "
            + "WHERE p.ClvProducto = ?";

    Producto producto = null;

    try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
        statement.setInt(1, idProducto);
        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {
            int ClvProducto = resultado.getInt("ClvProducto");
            String nombre = resultado.getString("Nombre");
            int precio = resultado.getInt("Precio");
            int existenciaTotal = resultado.getInt("ExistenciaTotal");
            //Date fechaCaducidad = resultado.getDate("FechaCaducidad");

            producto = new Producto();
            producto.colocarClaveProducto(ClvProducto);
            producto.colocarNombreProducto(nombre);
            producto.colocarPrecioProducto(precio);
            producto.colocarExistenciaProducto(existenciaTotal);
            //producto.colocarFechaCaducidad(fechaCaducidad); // Si quieres manejar la fecha de caducidad también
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return producto; // Retorna el producto encontrado, o null si no se encuentra
}
}
