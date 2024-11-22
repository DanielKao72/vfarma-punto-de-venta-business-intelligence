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

        if (existeCampoVacio(nombre, apellido, correo, telefono, sexo, turno, rol)) {
            credenciales.add(""); credenciales.add("");
        } else {
            InformacionEmpleado nuevoEmpleado = new InformacionEmpleado(
                "", nombre, apellido, correo, telefono, sexo, turno, rol
            );
            credenciales = gestorEmpleados.agregarNuevoEmpleadoABaseDeDatos(nuevoEmpleado);
        } 
        return credenciales;
    }

    public InformacionEmpleado consultarInformacionEmpleado(String usuarioEmpleado) {
        InformacionEmpleado informacionEmpleado;

        if (existeCampoVacio(usuarioEmpleado)){
            informacionEmpleado = null;
        }
        else{
            informacionEmpleado = gestorEmpleados.consultarInformacionEmpleadoEnBaseDeDatos(usuarioEmpleado);
        }

        return informacionEmpleado;
    }

    public boolean editarInformacionEmpleado(InformacionEmpleado informacionEmpleadoEditado) {
        if(existeCampoVacio(
            informacionEmpleadoEditado.obtenerNombreEmpleado(),
            informacionEmpleadoEditado.obtenerApellidoEmpleado(),
            informacionEmpleadoEditado.obtenerCorreoEmpleado(),
            informacionEmpleadoEditado.obtenerTelefonoEmpleado(),
            informacionEmpleadoEditado.obtenerSexoEmpleado(),
            informacionEmpleadoEditado.obtenerTurnoEmpleado(),
            informacionEmpleadoEditado.obtenerRolEmpleado()
        )) return false;
        else{
            boolean empleadoActualizado = gestorEmpleados.editarInformacionEmpleadoEnBaseDeDatos(informacionEmpleadoEditado);
            if(empleadoActualizado) return true;
            else return false;
        }
    }

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
        if(existeCampoVacio(usuario, contrasenia)) return false;
        else{
            boolean empleadoExistente = gestorEmpleados.buscarEmpleadoEnBaseDeDatos(usuario, contrasenia);
            if(empleadoExistente) return true;
            else return false;
        }
    }

    public String obtenerRolEmpleado(String usuario, String contrasenia){
        String rol = gestorEmpleados.obtenerRolEmpleadoEnBaseDeDatos(usuario, contrasenia);
        return rol;
    }
}
