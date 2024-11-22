package com.vfarma.RegistroDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vfarma.Almacenamiento.ConexionBaseDeDatos;
import com.vfarma.Farmacia.DatosFarmacia.InformacionProducto;

public class GestorProductos {
    private final ConexionBaseDeDatos baseDeDatos;
    private final Connection conexion;

    public GestorProductos() {
        this.baseDeDatos = ConexionBaseDeDatos.obtenerBaseDeDatos();
        this.conexion = baseDeDatos.abrirConexion();
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

    public InformacionProducto buscarProductoPorID(int id) {
        String consultaSQL = "SELECT p.ClvProducto, p.Nombre, p.Precio, p.ExistenciaTotal, i.FechaCaducidad "
                + "FROM productos p "
                + "JOIN inventario i ON p.ClvProducto = i.ClvProducto "
                + "WHERE p.ClvProducto = ?";

        InformacionProducto producto = new InformacionProducto();

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setInt(1, id);
            ResultSet conjuntoResultado = peticion.executeQuery();

            if (conjuntoResultado.next()) {
                int ClvProducto = conjuntoResultado.getInt("ClvProducto");
                String nombre = conjuntoResultado.getString("Nombre");
                int precio = conjuntoResultado.getInt("Precio");
                int existenciaTotal = conjuntoResultado.getInt("ExistenciaTotal");

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

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setInt(1, cantidadARestar);
            peticion.setInt(2, id);
            peticion.setInt(3, cantidadARestar);

            int filasActualizadas = peticion.executeUpdate();
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

    public ArrayList<InformacionProducto> obtenerNombreProductosEnExistencia() {
        String consultaSQL = "SELECT ClvProducto, Nombre, Precio, ExistenciaTotal FROM productos WHERE ExistenciaTotal > 0";
        ArrayList<InformacionProducto> productosDisponibles = new ArrayList<>();

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            ResultSet resultado = peticion.executeQuery();

            while (resultado.next()) {
                int clave = resultado.getInt("ClvProducto");
                String nombre = resultado.getString("Nombre");
                int precio = resultado.getInt("Precio");
                int existencia = resultado.getInt("ExistenciaTotal");

                InformacionProducto producto = new InformacionProducto(clave, nombre, precio, existencia);
                productosDisponibles.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productosDisponibles;
    }

    public Float obtenerPrecioProductoPorId(int idProducto) {
        String consultaSQL = "SELECT Precio FROM productos WHERE ClvProducto = ?";

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setInt(1, idProducto);
            ResultSet resultado = peticion.executeQuery();

            if (resultado.next()) {
                return resultado.getFloat("Precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encuentra el producto
    }

    public InformacionProducto obtenerProductoPorId(int idProducto) {
        String consultaSQL = "SELECT p.ClvProducto, p.Nombre, p.Precio, p.ExistenciaTotal, i.FechaCaducidad "
                + "FROM productos p "
                + "JOIN inventario i ON p.ClvProducto = i.ClvProducto "
                + "WHERE p.ClvProducto = ?";

        InformacionProducto producto = null;

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setInt(1, idProducto);
            ResultSet resultado = peticion.executeQuery();

            if (resultado.next()) {
                int ClvProducto = resultado.getInt("ClvProducto");
                String nombre = resultado.getString("Nombre");
                int precio = resultado.getInt("Precio");
                int existenciaTotal = resultado.getInt("ExistenciaTotal");

                producto = new InformacionProducto();
                producto.colocarClaveProducto(ClvProducto);
                producto.colocarNombreProducto(nombre);
                producto.colocarPrecioProducto(precio);
                producto.colocarExistenciaProducto(existenciaTotal);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    public int obtenerExistenciaProductoPorId(int idProducto) {
        String query = "SELECT ExistenciaTotal FROM productos WHERE ClvProducto = ?";
        try (PreparedStatement peticion = conexion.prepareStatement(query)) {
            peticion.setInt(1, idProducto);
            try (ResultSet resultado = peticion.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("ExistenciaTotal");
                } else {
                    System.out.println("Producto no encontrado en el inventario.");
                    return 0; // Producto no encontrado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la existencia del producto: " + e.getMessage());
            return 0; // Error durante la consulta
        }
    }
}
