package com.vfarma.BaseDatos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vfarma.Modelo.Producto;

public class ConsultasProducto {
    private final BaseDeDatos baseDeDatos;

    public ConsultasProducto(){
        this.baseDeDatos = BaseDeDatos.obtenerInstancia();
    }

    public List<Producto> obtenerTodaLaInfoDeTodosLosProductos() {
        String query = "SELECT p.*, c.*, f.*, pr.* "
                + "FROM Producto p "
                + "JOIN Categoria c ON p.CategoriaID = c.CategoriaID "
                + "JOIN Familia f ON p.FamiliaID = f.FamiliaID "
                + "JOIN Proveedor pr ON p.ProveedorID = pr.ProveedorID";
        return this.baseDeDatos.consultarTuplas(query, Producto.class);
    }

    public Producto buscarProductoPorID(int idProducto){
        String query = "SELECT * FROM Producto WHERE ProductoID = " + idProducto;
        return this.baseDeDatos.consultarTuplas(query, Producto.class).get(0);  
    }

    public int existenciaProducto(int idProducto){
        String query = "SELECT SUM(Cantidad) AS TotalCantidad" + 
                        "FROM Compra" + 
                        "WHERE ProductoID = ?; " + idProducto;
        return this.baseDeDatos.consultarUnValor(query, Integer.class);
    }

    public void restarExistenciaProducto(int productoId, int cantidadARestar){
        String query = "UPDATE Producto SET Existencia = Existencia - ? WHERE ProductoID = ?";
        try (PreparedStatement declaracion = this.baseDeDatos.obtenerConexionBaseDatos().prepareStatement(query)) {
            declaracion.setInt(1, cantidadARestar); 
            declaracion.setInt(2, productoId);
    
            int filasAfectadas = declaracion.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Existencia del producto actualizada correctamente.");
            } else {
                System.out.println("No se encontró el producto con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la existencia del producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String obtenerFechaCaducidad(int idProducto){
        String query = "SELECT FechaCaducidad FROM Compra WHERE ProductoID = " + idProducto ;
        return this.baseDeDatos.consultarUnValor(query, String.class);
    }

    public String obtenerProveedor(int idProducto){
        String query = "SELECT p.Nombre FROM Proveedor p JOIN Compra c ON p.ProveedorID = c.ProveedorID " + "WHERE c.ProductoID = " + idProducto + ";";
        return this.baseDeDatos.consultarUnValor(query, String.class);
    }
    
}
