package com.vfarma.RegistroEmpleados.Controlador;

import com.vfarma.BaseDatos.EmpleadoConsultasBaseDatos;
import com.vfarma.Modelo.InformacionEmpleado;

public class FuncionarioRecursosHumanos {

    private static FuncionarioRecursosHumanos funcionarioRH;
    private EmpleadoConsultasBaseDatos consultasBD = new EmpleadoConsultasBaseDatos();

    private FuncionarioRecursosHumanos(){};

    public static FuncionarioRecursosHumanos llamarFuncionarioRH(){
        if(funcionarioRH == null) funcionarioRH = new FuncionarioRecursosHumanos();
        return funcionarioRH;
    }

    private boolean existeCampoVacio(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean agregarNuevoEmpleado(
        String nombre, 
        String apellido, 
        String correo, 
        String telefono, 
        String sexo, 
        String turno, 
        String rol
    ) {
        if (existeCampoVacio(nombre, apellido, correo, telefono, sexo, turno, rol)) {
            System.out.println("Error: Algunos campos están vacíos o son inválidos.");
            return false;
        } else {
            InformacionEmpleado nuevoEmpleado = new InformacionEmpleado(
                "", nombre, apellido, correo, telefono, sexo, turno, rol
            );
            boolean empleadoAgregado = consultasBD.agregarNuevoEmpleadoABaseDeDatos(nuevoEmpleado);
            if(empleadoAgregado) return true;
            else return false;
        } 
    }

    public InformacionEmpleado consultarInformacionEmpleado(String usuarioEmpleado) {
        InformacionEmpleado informacionEmpleado = null;

        if (existeCampoVacio(usuarioEmpleado)){
            System.out.println("Error: El ID de empleado no puede estar vacío.");
        }
        else{
            informacionEmpleado = consultasBD.consultarInformacionEmpleadoEnBaseDeDatos(usuarioEmpleado);
        }

        return informacionEmpleado;
    }

    public boolean editarInformacionEmpleado(InformacionEmpleado informacionEmpleadoEditado) {
        boolean empleadoActualizado = consultasBD.editarInformacionEmpleadoEnBaseDeDatos(informacionEmpleadoEditado);
        if(empleadoActualizado) return true;
        else return false;
    }

    public boolean eliminarEmpleado(String idEmpleado) {
        if (existeCampoVacio(idEmpleado)) {
            return false;
        } else {
            boolean empleadoEliminado = consultasBD.eliminarEmpleadoDeBaseDeDatos(idEmpleado);
            if(empleadoEliminado) return true;
            else return false;
        }
    }
}
