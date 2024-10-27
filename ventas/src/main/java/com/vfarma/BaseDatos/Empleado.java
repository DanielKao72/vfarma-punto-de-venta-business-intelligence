package com.vfarma.BaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Empleado implements MapeadorBaseDatos {

    private int usuarioID;
    private String claveEmpleado;
    private String nombre;
    private String sucursal;
    private String turno;
    private String rol;
    private String contrasena;

    @Override
    public void mapearDelConjuntoResultado(ResultSet conjuntoResultado) throws SQLException {
        this.usuarioID = conjuntoResultado.getInt("UsuarioID");
        this.claveEmpleado = conjuntoResultado.getString("ClaveEmpleado");
        this.nombre = conjuntoResultado.getString("Nombre");
        this.sucursal = conjuntoResultado.getString("Sucursal");
        this.turno = conjuntoResultado.getString("Turno");
        this.rol = conjuntoResultado.getString("Rol");
        this.contrasena = conjuntoResultado.getString("Contrasena");
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "usuarioID=" + usuarioID
                + ", claveEmpleado='" + claveEmpleado + '\''
                + ", nombre='" + nombre + '\''
                + ", sucursal='" + sucursal + '\''
                + ", turno='" + turno + '\''
                + ", rol='" + rol + '\''
                + ", contrasena='" + contrasena + '\''
                + '}';
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getClaveEmpleado() {
        return claveEmpleado;
    }

    public void setClaveEmpleado(String claveEmpleado) {
        this.claveEmpleado = claveEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
