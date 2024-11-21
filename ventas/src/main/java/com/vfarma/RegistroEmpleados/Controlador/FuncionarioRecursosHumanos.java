package com.vfarma.RegistroEmpleados.Controlador;

import com.vfarma.BaseDatos.EmpleadoConsultasBaseDatos;
import com.vfarma.Modelo.InformacionEmpleado;

public class FuncionarioRecursosHumanos {

    private EmpleadoConsultasBaseDatos consultasBD = new EmpleadoConsultasBaseDatos();

    private boolean existeCampoVacio(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void agregarNuevoEmpleado(
        String nombre, 
        String apellido, 
        String correo, 
        String telefono, 
        String sexo, 
        String turno, 
        String rol, 
        String sucursalEmpleado
    ) {
        if (existeCampoVacio(nombre, apellido, correo, telefono, sexo, turno, rol, sucursalEmpleado)) {
            System.out.println("Error: Algunos campos están vacíos o son inválidos.");
        } else {
            InformacionEmpleado nuevoEmpleado = new InformacionEmpleado(
                nombre, apellido, correo, telefono, sexo, turno, rol, sucursalEmpleado
            );
            consultasBD.agregarNuevoEmpleadoABaseDeDatos(nuevoEmpleado);
        }
    }

    public void consultarInformacionEmpleado(String idEmpleado) {
        if (existeCampoVacio(idEmpleado)){
            System.out.println("Error: El ID de empleado no puede estar vacío.");
        }
        else{
            consultasBD.consultarInformacionEmpleadoEnBaseDeDatos(idEmpleado);
        }
    }

    public void editarInformacionEmpleado(
        String idEmpleado,
        String nombre, 
        String apellido, 
        String correo, 
        String telefono, 
        String sexo, 
        String turno, 
        String rol, 
        String sucursalEmpleado
    ) {
        if (existeCampoVacio(idEmpleado, nombre, apellido, correo, telefono, sexo, turno, rol, sucursalEmpleado)) {
            System.out.println("Error: Algunos campos están vacíos o son inválidos.");
        } else {
            InformacionEmpleado informacionEmpleadoEditado = new InformacionEmpleado(
                nombre, apellido, correo, telefono, sexo, turno, rol, sucursalEmpleado
            );
            consultasBD.editarInformacionEmpleadoEnBaseDeDatos(idEmpleado, informacionEmpleadoEditado);
        }
    }

    public void eliminarEmpleado(String idEmpleado) {
        if (existeCampoVacio(idEmpleado)) {
            System.out.println("Error: El ID de empleado no puede estar vacío.");
        } else {
            consultasBD.eliminarEmpleadoDeBaseDeDatos(idEmpleado);
        }
    }
}
