package com.vfarma.Almacenamiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    private static ConexionBaseDeDatos baseDeDatos;
    private Connection conexionBaseDatos;
    private final String urlBaseDatos = "jdbc:mysql://localhost:3306/vfarma";
    private final String usuarioBaseDatos = "root";
    private final String contrasenaBaseDatos = ""; 

    private ConexionBaseDeDatos() {}

    public static ConexionBaseDeDatos obtenerBaseDeDatos() {
        if (baseDeDatos == null) {
            baseDeDatos = new ConexionBaseDeDatos();
        }
        return baseDeDatos;
    }

    public Connection abrirConexion() {
        try {
            this.conexionBaseDatos = DriverManager.getConnection(this.urlBaseDatos, this.usuarioBaseDatos, this.contrasenaBaseDatos);
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
            e.printStackTrace();
        }

        return this.conexionBaseDatos;
    }

    public void cerrarConexion() {
        try {
            this.conexionBaseDatos.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión con MySQL");
            e.printStackTrace();
        }
    }
}
