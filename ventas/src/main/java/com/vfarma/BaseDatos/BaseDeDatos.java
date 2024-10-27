package com.vfarma.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {

    private static BaseDeDatos instancia;
    private Connection conexionBaseDatos;
    private final String urlBaseDatos = "jdbc:mysql://localhost:3306/prueba";
    private final String usuarioBaseDatos = "root";
    private final String contraseniaBaseDatos = "";

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

    private <T extends MapeadorBaseDatos> List<T> consultaBaseDatos(String query, Class<T> type) {
        List<T> resultados = new ArrayList<>();

        try (Statement declaracion = this.conexionBaseDatos.createStatement()) {
            ResultSet conjuntoResultado = declaracion.executeQuery(query);

            while (conjuntoResultado.next()) {
                T campoConsulta = type.getDeclaredConstructor().newInstance();
                campoConsulta.mapearDelConjuntoResultado(conjuntoResultado);
                resultados.add(campoConsulta);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al mapear los resultados: " + e.getMessage());
            e.printStackTrace();
        }

        return resultados;
    }

    public List<Empleado> obtenerTodosLosUsuarios() {
        return this.consultaBaseDatos("SELECT * FROM usuario", Empleado.class);
    }

    public List<Producto> obtenerTodaLaInfoDeTodosLosProductos() {
        String query = "SELECT p.*, c.*, f.*, pr.* "
                + "FROM Producto p "
                + "JOIN Categoria c ON p.CategoriaID = c.CategoriaID "
                + "JOIN Familia f ON p.FamiliaID = f.FamiliaID "
                + "JOIN Proveedor pr ON p.ProveedorID = pr.ProveedorID";
        return this.consultaBaseDatos(query, Producto.class);
    }
}
