package com.vfarma.Farmacia.ControlAcceso;

import com.vfarma.Farmacia.RecursosHumanos.FuncionarioRecursosHumanos;

public class Empleado {
    private static Empleado empleado;

    private Empleado(){};

    public static Empleado obtenerEmpleado(){
        if(empleado == null) empleado = new Empleado();
        return empleado;
    }

    public String iniciarSesion(String usuario, String contrasena) {
        FuncionarioRecursosHumanos funcionarioRecursosHumanos = FuncionarioRecursosHumanos.obtenerFuncionarioRH();

        boolean esUnEmpleado = funcionarioRecursosHumanos.verificarExistenciaEmpleado(usuario, contrasena);

        if(esUnEmpleado) {
            return funcionarioRecursosHumanos.obtenerRolEmpleado(usuario, contrasena);
        }
        else{
            return "";
        }
    }
    
}
