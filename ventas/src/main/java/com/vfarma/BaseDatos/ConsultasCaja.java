package com.vfarma.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultasCaja {

    private final BaseDeDatos baseDeDatos;
    private Connection conexion;

    public ConsultasCaja() {
        this.baseDeDatos = BaseDeDatos.obtenerInstancia();

        this.conexion = baseDeDatos.obtenerConexionBaseDatos();

    }

    public ArrayList<String> obtenerNombresCajasDisponibles(){
        String query = "SELECT NumCaja FROM cajas WHERE Estado = 1";
        ArrayList<String> nombresCajas = new ArrayList<>();
      
        try (PreparedStatement stmt = this.conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                nombresCajas.add(rs.getString("NumCaja"));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
   
        return nombresCajas;
    }

    public void desOcuparCaja(String numCaja) {
        String query = "UPDATE cajas SET Estado = 1 WHERE NumCaja = ?";
    
        try (PreparedStatement stmt = this.conexion.prepareStatement(query)) {
            stmt.setString(1, numCaja);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void ocuparCaja(String numCaja) {
        String query = "UPDATE cajas SET Estado = 0 WHERE NumCaja = ?";
    
        try (PreparedStatement stmt = this.conexion.prepareStatement(query)) {
            stmt.setString(1, numCaja);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

   
}

