package com.vfarma.RegistroDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vfarma.Almacenamiento.ConexionBaseDeDatos;

public class GestorCaja {
    private final ConexionBaseDeDatos baseDeDatos;
    private final Connection conexion;

    public GestorCaja() {
        this.baseDeDatos = ConexionBaseDeDatos.obtenerBaseDeDatos();
        this.conexion = baseDeDatos.abrirConexion();
    }

    public ArrayList<String> obtenerNombresCajasDisponibles() {
        String consultaSQL = "SELECT NumCaja FROM cajas WHERE Estado = 1";
        ArrayList<String> nombresCajas = new ArrayList<>();

        try (PreparedStatement declaracion = this.conexion.prepareStatement(consultaSQL);) {
            ResultSet conjuntoResultado = declaracion.executeQuery();
            while (conjuntoResultado.next()) {
                nombresCajas.add(conjuntoResultado.getString("NumCaja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombresCajas;
    }

    public void desOcuparCaja(String numCaja) {
        String consultaSQL = "UPDATE cajas SET Estado = 1 WHERE NumCaja = ?";

        try (PreparedStatement declaracion = this.conexion.prepareStatement(consultaSQL)) {
            declaracion.setString(1, numCaja);
            declaracion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ocuparCaja(String numCaja) {
        String consultaSQL = "UPDATE cajas SET Estado = 0 WHERE NumCaja = ?";

        try (PreparedStatement declaracion = this.conexion.prepareStatement(consultaSQL)) {
            declaracion.setString(1, numCaja);
            declaracion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}