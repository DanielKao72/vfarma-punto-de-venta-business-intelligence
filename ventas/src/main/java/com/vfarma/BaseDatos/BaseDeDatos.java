package com.vfarma.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDatos {

    private static BaseDeDatos instancia;
    private Connection conexionBaseDatos;
    private final String urlBaseDatos = "jdbc:mysql://localhost:3306/vfarma";
    private final String usuarioBaseDatos = "root";
    private final String contraseniaBaseDatos = "racp120417"; 
    private BaseDeDatos() {
        try {
            this.conexionBaseDatos = DriverManager.getConnection(urlBaseDatos, usuarioBaseDatos, contraseniaBaseDatos);
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
            e.printStackTrace();
        }
    }

    public static BaseDeDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    public Connection obtenerConexionBaseDatos() {
        return conexionBaseDatos;
    }
}
