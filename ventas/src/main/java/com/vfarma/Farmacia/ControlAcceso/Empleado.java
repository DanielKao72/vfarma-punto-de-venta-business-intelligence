package com.vfarma.Farmacia.ControlAcceso;

public class Empleado {
    private static Empleado empleado;

    private Empleado(){};

    public static Empleado obtenerEmpleado(){
        if(empleado == null) empleado = new Empleado();
        return empleado;
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        if (usuario.equals("admin") && contrasena.equals("admin")) {
            System.out.println("Sesión iniciada correctamente.");
            return true;
        } else {
            System.out.println("Error: Usuario o contraseña incorrectos.");
            return false;
        }
    }

    public boolean cerrarSesion() {
        System.out.println("Sesión cerrada correctamente.");
        return true;
    }
    
}
