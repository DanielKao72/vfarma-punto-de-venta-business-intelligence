package com.vfarma.Farmacia.RecursosHumanos;

import com.vfarma.Farmacia.Modelo.InformacionEmpleado;
import com.vfarma.RegistroDatos.GestorEmpleados;

public class FuncionarioRecursosHumanos {
    private static FuncionarioRecursosHumanos funcionarioRH;
    private GestorEmpleados registroEmpleados = new GestorEmpleados();

    private FuncionarioRecursosHumanos(){};

    public static FuncionarioRecursosHumanos obtenerFuncionarioRH(){
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
            boolean empleadoAgregado = this.registroEmpleados.agregarNuevoEmpleadoABaseDeDatos(nuevoEmpleado);
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
            informacionEmpleado = this.registroEmpleados.consultarInformacionEmpleadoEnBaseDeDatos(usuarioEmpleado);
        }

        return informacionEmpleado;
    }

    public boolean editarInformacionEmpleado(InformacionEmpleado informacionEmpleadoEditado) {
        boolean empleadoActualizado = this.registroEmpleados.editarInformacionEmpleadoEnBaseDeDatos(informacionEmpleadoEditado);
        if(empleadoActualizado) return true;
        else return false;
    }

    public boolean eliminarEmpleado(String idEmpleado) {
        if (existeCampoVacio(idEmpleado)) {
            return false;
        } else {
            boolean empleadoEliminado = this.registroEmpleados.eliminarEmpleadoDeBaseDeDatos(idEmpleado);
            if(empleadoEliminado) return true;
            else return false;
        }
    }
}
