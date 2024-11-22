package com.vfarma.Farmacia.RecursosHumanos;

import java.util.ArrayList;

import com.vfarma.Farmacia.DatosFarmacia.InformacionEmpleado;
import com.vfarma.RegistroDatos.GestorEmpleados;

public class FuncionarioRecursosHumanos {
    private static FuncionarioRecursosHumanos funcionarioRH;
    private GestorEmpleados gestorEmpleados = new GestorEmpleados();

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

    public ArrayList<String> agregarNuevoEmpleado(
        String nombre, 
        String apellido, 
        String correo, 
        String telefono, 
        String sexo, 
        String turno, 
        String rol
    ) {
        ArrayList<String> credenciales = new ArrayList<>();
        credenciales.add(""); credenciales.add("");

        if (existeCampoVacio(nombre, apellido, correo, telefono, sexo, turno, rol)) {
            System.out.println("Error: Algunos campos están vacíos o son inválidos.");
        } else {
            InformacionEmpleado nuevoEmpleado = new InformacionEmpleado(
                "", nombre, apellido, correo, telefono, sexo, turno, rol
            );
            credenciales = gestorEmpleados.agregarNuevoEmpleadoABaseDeDatos(nuevoEmpleado);
        } 
        return credenciales;
    }

    public InformacionEmpleado consultarInformacionEmpleado(String usuarioEmpleado) {
        InformacionEmpleado informacionEmpleado = null;

        if (existeCampoVacio(usuarioEmpleado)){
            System.out.println("Error: El ID de empleado no puede estar vacío.");
        }
        else{
            informacionEmpleado = gestorEmpleados.consultarInformacionEmpleadoEnBaseDeDatos(usuarioEmpleado);
        }

        return informacionEmpleado;
    }

    public boolean editarInformacionEmpleado(InformacionEmpleado informacionEmpleadoEditado) {
        boolean empleadoActualizado = gestorEmpleados.editarInformacionEmpleadoEnBaseDeDatos(informacionEmpleadoEditado);
        if(empleadoActualizado) return true;
        else return false;
    }

    //cambiar id por usuario
    public boolean eliminarEmpleado(String usuarioEmpleado) {
        if (existeCampoVacio(usuarioEmpleado)) {
            return false;
        } else {
            boolean empleadoEliminado = gestorEmpleados.eliminarEmpleadoDeBaseDeDatos(usuarioEmpleado);
            if(empleadoEliminado) return true;
            else return false;
        }
    }

    public boolean verificarExistenciaEmpleado(String usuario, String contrasenia){
        boolean empleadoExistente = gestorEmpleados.buscarEmpleadoEnBaseDeDatos(usuario, contrasenia);
        return empleadoExistente;
    }

    public String obtenerRolEmpleado(String usuario, String contrasenia){
        String rol = gestorEmpleados.obtenerRolEmpleadoEnBaseDeDatos(usuario, contrasenia);
        return rol;
    }
}
