package com.vfarma.BaseDatos;

import com.vfarma.Modelo.InformacionEmpleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoConsultasBaseDatos {

    private final Connection conexion;

    public EmpleadoConsultasBaseDatos() {
        this.conexion = BaseDeDatos.obtenerInstancia().obtenerConexionBaseDatos();
    }

    private String generarUsuario(InformacionEmpleado empleado) {
        String iniciales = empleado.obtenerNombreEmpleado().substring(0, 1).toUpperCase() +
                empleado.obtenerApellidoEmpleado().substring(0, 1).toUpperCase();
        String siglasRol = empleado.obtenerRolEmpleado().substring(0, 3).toUpperCase();
        String sexo = empleado.obtenerSexoEmpleado().substring(0, 1).toUpperCase();
        String telefono = empleado.obtenerTelefonoEmpleado();
        String ultimosDigitosTelefono = telefono.substring(telefono.length() - 2);

        return iniciales + siglasRol + sexo + ultimosDigitosTelefono;
    }

    private String generarContrasenia(InformacionEmpleado empleado) {
        String iniciales = empleado.obtenerNombreEmpleado().substring(0, 1).toLowerCase() +
                empleado.obtenerApellidoEmpleado().substring(0, 1).toLowerCase();
        String sexo = empleado.obtenerSexoEmpleado().substring(0, 1).toLowerCase();
        String telefono = empleado.obtenerTelefonoEmpleado();
        String ultimosDigitosTelefono = telefono.substring(telefono.length() - 2);

        return iniciales + sexo + ultimosDigitosTelefono;
    }

    public void agregarNuevoEmpleadoABaseDeDatos(InformacionEmpleado empleado) {
        String queryInsertEmpleado = "INSERT INTO empleados (Nombre, Apellido, Correo, Telefono, Sexo, Turno, Rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String queryInsertCredenciales = "INSERT INTO credenciales (Usuario, Contrasenia, ClvEmpleado) VALUES (?, ?, ?)";

        try (PreparedStatement psEmpleado = conexion.prepareStatement(queryInsertEmpleado, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement psCredenciales = conexion.prepareStatement(queryInsertCredenciales)) {

            psEmpleado.setString(1, empleado.obtenerNombreEmpleado());
            psEmpleado.setString(2, empleado.obtenerApellidoEmpleado());
            psEmpleado.setString(3, empleado.obtenerCorreoEmpleado());
            psEmpleado.setString(4, empleado.obtenerTelefonoEmpleado());
            psEmpleado.setString(5, empleado.obtenerSexoEmpleado());
            psEmpleado.setString(6, empleado.obtenerTurnoEmpleado());
            psEmpleado.setString(7, empleado.obtenerRolEmpleado());
            psEmpleado.executeUpdate();

            ResultSet rs = psEmpleado.getGeneratedKeys();
            if (rs.next()) {
                int claveEmpleado = rs.getInt(1);
                String usuario = generarUsuario(empleado);
                String contrasenia = generarContrasenia(empleado);

                psCredenciales.setString(1, usuario);
                psCredenciales.setString(2, contrasenia);
                psCredenciales.setInt(3, claveEmpleado);
                psCredenciales.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public InformacionEmpleado consultarInformacionEmpleadoEnBaseDeDatos(String idEmpleado) {
        String query = "SELECT * FROM empleados WHERE ClvEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new InformacionEmpleado(
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Correo"),
                        rs.getString("Telefono"),
                        rs.getString("Sexo"),
                        rs.getString("Turno"),
                        rs.getString("Rol"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editarInformacionEmpleadoEnBaseDeDatos(String idEmpleado, InformacionEmpleado empleado) {
        String query = "UPDATE empleados SET Nombre = ?, Apellido = ?, Correo = ?, Telefono = ?, Sexo = ?, Turno = ?, Rol = ? WHERE ClvEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, empleado.obtenerNombreEmpleado());
            ps.setString(2, empleado.obtenerApellidoEmpleado());
            ps.setString(3, empleado.obtenerCorreoEmpleado());
            ps.setString(4, empleado.obtenerTelefonoEmpleado());
            ps.setString(5, empleado.obtenerSexoEmpleado());
            ps.setString(6, empleado.obtenerTurnoEmpleado());
            ps.setString(7, empleado.obtenerRolEmpleado());
            ps.setString(8, idEmpleado);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleadoDeBaseDeDatos(String idEmpleado) {
        String query = "DELETE FROM empleados WHERE ClvEmpleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, idEmpleado);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validarCredencialEmpleado(String usuario, String contrasenia) {
        String query = "SELECT * FROM credenciales WHERE Usuario = ? AND Contrasenia = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
