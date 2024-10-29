package com.vfarma.BaseDatos;
import java.util.List;
import com.vfarma.Modelo.Producto;

public class ControlInventario {
    private final BaseDeDatos baseDeDatos;

    public ControlInventario(){
        this.baseDeDatos = BaseDeDatos.obtenerInstancia();
    }

    public boolean validarExistenciaDeClave(String clave){
        String query = "SELECT * FROM Producto WHERE Clave = '" + clave + "'";
        return this.baseDeDatos.consultarTuplas(query, Producto.class).size() > 0;
    }

    public boolean validarCategoria(String categoria){
        String query = "SELECT * FROM Categoria WHERE Nombre = '" + categoria + "'";
        return this.baseDeDatos.consultarTuplas(query, Producto.class).size() > 0;
    }

    // public int obtenerCategoria(String categoria){
    //     String query = "SELECT CategoriaID FROM Categoria WHERE Nombre = '" + categoria + "'";
    //     return this.baseDeDatos.consultarTuplas(query, Producto.class).get(0).obtenerCategoriaID();
    // }

    public void registrarNuevoProducto(String nombre, String clave, double precio, int categoriaID){
        String query = "INSERT INTO Producto (Nombre, Clave, Precio, CategoriaID) VALUES ('" + nombre + "', '" + clave + "', " + precio + ", " + categoriaID + ")";
        this.baseDeDatos.consultarUnValor(query, null);
    }

    public Producto buscarProductoPorClave(String clave){
        String query = "SELECT * FROM Producto WHERE Clave = '" + clave + "'";
        return this.baseDeDatos.consultarTuplas(query, Producto.class).get(0);
    }

    public List<Producto> obtenerTodosLosProductos(){
        String query = "SELECT * FROM Producto";
        return this.baseDeDatos.consultarTuplas(query, Producto.class);
    }

    public void actualizarProducto(String clave, int precio, int existencia, String fechaCaducidad){
        String query = "UPDATE Producto SET Precio = " + precio + ", Existencia = " + existencia + ", FechaCaducidad = '" + fechaCaducidad + "' WHERE Clave = '" + clave + "'";
        this.baseDeDatos.consultarUnValor(query, null);
    }
}
