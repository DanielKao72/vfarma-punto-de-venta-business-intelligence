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

    protected <T extends MapeadorBaseDatos> List<T> consultarTuplas(String query, Class<T> type) {
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

    protected <T> T consultarUnValor(String query, Class<T> type) {
        T resultado = null;
    
        try (Statement declaracion = this.conexionBaseDatos.createStatement()) {
            ResultSet conjuntoResultado = declaracion.executeQuery(query);
    
            if (conjuntoResultado.next()) {
                resultado = type.getDeclaredConstructor().newInstance();
                if (resultado instanceof MapeadorBaseDatos) {
                    ((MapeadorBaseDatos) resultado).mapearDelConjuntoResultado(conjuntoResultado);
                } else {
                    throw new IllegalArgumentException("La clase " + type.getName() + " debe implementar MapeadorBaseDatos");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al mapear los resultados: " + e.getMessage());
            e.printStackTrace();
        }
    
        return resultado;
    }

    public Connection obtenerConexionBaseDatos() {
        return conexionBaseDatos;
    }
    
    

    
    


    
    


}
