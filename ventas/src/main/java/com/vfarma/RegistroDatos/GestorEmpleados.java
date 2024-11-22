package com.vfarma.RegistroDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vfarma.Almacenamiento.ConexionBaseDeDatos;
import com.vfarma.Farmacia.DatosFarmacia.InformacionEmpleado;

public class GestorEmpleados {
    private final ConexionBaseDeDatos baseDeDatos;
    private final Connection conexion;

    public GestorEmpleados() {
        this.baseDeDatos = ConexionBaseDeDatos.obtenerBaseDeDatos();
        this.conexion = baseDeDatos.abrirConexion();
    }

    private String generarUsuarioEmpleado(InformacionEmpleado empleado) {
        String inicialesNombreEmpleado = empleado.obtenerNombreEmpleado().substring(0, 1).toUpperCase() +
                empleado.obtenerApellidoEmpleado().substring(0, 1).toUpperCase();
        String siglasRol = empleado.obtenerRolEmpleado().substring(0, 3).toUpperCase();
        String sexo = empleado.obtenerSexoEmpleado().substring(0, 1).toUpperCase();
        String telefono = empleado.obtenerTelefonoEmpleado();
        String ultimosDigitosTelefono = telefono.substring(telefono.length() - 2);

        return inicialesNombreEmpleado + siglasRol + sexo + ultimosDigitosTelefono;
    }

    private String generarContraseniaEmpleado(InformacionEmpleado empleado) {
        String inicialesNombreEmpleado = empleado.obtenerNombreEmpleado().substring(0, 1).toLowerCase() +
                empleado.obtenerApellidoEmpleado().substring(0, 1).toLowerCase();
        String sexo = empleado.obtenerSexoEmpleado().substring(0, 1).toLowerCase();
        String telefono = empleado.obtenerTelefonoEmpleado();
        String ultimosDigitosTelefono = telefono.substring(telefono.length() - 2);

        return inicialesNombreEmpleado + sexo + ultimosDigitosTelefono;
    }

    public ArrayList<String> agregarNuevoEmpleadoABaseDeDatos(InformacionEmpleado empleado) {
        ArrayList<String> credenciales = new ArrayList<>();
        credenciales.add(""); 
        credenciales.add("");
    
        try {
            int claveEmpleado = agregarInformacionEmpleado(empleado);
            if (claveEmpleado > 0) {
                credenciales = generarCredencialesEmpleado(empleado, claveEmpleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credenciales;
    }
    
    private int agregarInformacionEmpleado(InformacionEmpleado empleado) throws SQLException {
        String consultaSQL = "INSERT INTO empleados (Nombre, Apellido, Correo, Telefono, Sexo, Turno, Rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int claveEmpleado = -1;
    
        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            peticion.setString(1, empleado.obtenerNombreEmpleado());
            peticion.setString(2, empleado.obtenerApellidoEmpleado());
            peticion.setString(3, empleado.obtenerCorreoEmpleado());
            peticion.setString(4, empleado.obtenerTelefonoEmpleado());
            peticion.setString(5, empleado.obtenerSexoEmpleado());
            peticion.setString(6, empleado.obtenerTurnoEmpleado());
            peticion.setString(7, empleado.obtenerRolEmpleado());
            peticion.executeUpdate();
    
            ResultSet resultado = peticion.getGeneratedKeys();
            if (resultado.next()) {
                claveEmpleado = resultado.getInt(1);
            }
        }
        return claveEmpleado;
    }
    
    private ArrayList<String> generarCredencialesEmpleado(InformacionEmpleado empleado, int claveEmpleado) throws SQLException {
        String consultaSQL = "INSERT INTO credenciales (Usuario, Contrasenia, ClvEmpleado) VALUES (?, ?, ?)";
        ArrayList<String> credenciales = new ArrayList<>();
    
        String usuario = generarUsuarioEmpleado(empleado);
        String contrasenia = generarContraseniaEmpleado(empleado);
        credenciales.add(usuario);
        credenciales.add(contrasenia);
    
        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setString(1, usuario);
            peticion.setString(2, contrasenia);
            peticion.setInt(3, claveEmpleado);
            peticion.executeUpdate();
        }
        return credenciales;
    }
    
    public InformacionEmpleado consultarInformacionEmpleadoEnBaseDeDatos(String usuarioEmpleado) {
        String consultaSQL = "SELECT c.*, e.* " +
                   "FROM credenciales c " +
                   "JOIN empleados e ON c.ClvEmpleado = e.ClvEmpleado " +
                   "WHERE c.Usuario = ?";

        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setString(1, usuarioEmpleado);
            ResultSet resultado = peticion.executeQuery();
            if (resultado.next()) {
                return new InformacionEmpleado(
                        resultado.getString("ClvEmpleado"),
                        resultado.getString("Nombre"),
                        resultado.getString("Apellido"),
                        resultado.getString("Correo"),
                        resultado.getString("Telefono"),
                        resultado.getString("Sexo"),
                        resultado.getString("Turno"),
                        resultado.getString("Rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean editarInformacionEmpleadoEnBaseDeDatos(InformacionEmpleado empleado) {
        String consultaSQL = "UPDATE empleados SET Nombre = ?, Apellido = ?, Correo = ?, Telefono = ?, Sexo = ?, Turno = ?, Rol = ? WHERE ClvEmpleado = ?";
        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setString(1, empleado.obtenerNombreEmpleado());
            peticion.setString(2, empleado.obtenerApellidoEmpleado());
            peticion.setString(3, empleado.obtenerCorreoEmpleado());
            peticion.setString(4, empleado.obtenerTelefonoEmpleado());
            peticion.setString(5, empleado.obtenerSexoEmpleado());
            peticion.setString(6, empleado.obtenerTurnoEmpleado());
            peticion.setString(7, empleado.obtenerRolEmpleado());
            peticion.setString(8, empleado.obtenerClaveEmpleado());
            
            int registrosActualizados = peticion.executeUpdate();
            if(registrosActualizados > 0) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarEmpleadoDeBaseDeDatos(String usuarioEmpleado) {
        String consultaSQL = "DELETE FROM empleados WHERE ClvEmpleado = ?";
        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            String claveEmpleado = buscarEmpleadoPorUsuarioEnBaseDeDatos(usuarioEmpleado);
            if (claveEmpleado != null) {
                peticion.setString(1, claveEmpleado);
                int registrosEliminados = peticion.executeUpdate();
                if(registrosEliminados > 0) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private String buscarEmpleadoPorUsuarioEnBaseDeDatos(String usuarioEmpleado) throws SQLException {
        String consultaSQL = "SELECT ClvEmpleado FROM credenciales WHERE Usuario = ?";
        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setString(1, usuarioEmpleado);
            try (ResultSet resultado = peticion.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getString("ClvEmpleado");
                }
            }
        }
        return null;
    }
    

    public boolean buscarEmpleadoEnBaseDeDatos(String usuario, String contrasenia) {
        String consultaSQL = "SELECT * FROM credenciales WHERE Usuario = ? AND Contrasenia = ?";
        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setString(1, usuario);
            peticion.setString(2, contrasenia);
            ResultSet resultado = peticion.executeQuery();
            return resultado.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String obtenerRolEmpleadoEnBaseDeDatos(String usuario, String contrasenia) {
        String consultaSQL = "SELECT e.Rol FROM empleados e " +
                       "JOIN credenciales c ON e.ClvEmpleado = c.ClvEmpleado " +
                       "WHERE c.Usuario = ? AND c.Contrasenia = ?";
        String rol = "";
        try (PreparedStatement peticion = conexion.prepareStatement(consultaSQL)) {
            peticion.setString(1, usuario);
            peticion.setString(2, contrasenia);
            try (ResultSet resultado = peticion.executeQuery()) {
                if (resultado.next()) {
                    rol = resultado.getString("Rol");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }
}
