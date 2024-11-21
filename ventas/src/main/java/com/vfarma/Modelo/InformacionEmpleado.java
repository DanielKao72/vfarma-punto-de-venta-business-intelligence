package com.vfarma.Modelo;

public class InformacionEmpleado {

    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String correoEmpleado;
    private String telefonoEmpleado;
    private String sexoEmpleado;
    private String turnoEmpleado;
    private String rolEmpleado;
    private String sucursalEmpleado;

    public InformacionEmpleado(
        String nombre, 
        String apellido, 
        String correo, 
        String telefono, 
        String sexo, 
        String turno, 
        String rol, 
        String sucursalEmpleado
    ) {
        this.nombreEmpleado = nombre;
        this.apellidoEmpleado = apellido;
        this.correoEmpleado = correo;
        this.telefonoEmpleado = telefono;
        this.sexoEmpleado = sexo;
        this.turnoEmpleado = turno;
        this.rolEmpleado = rol;
        this.sucursalEmpleado = sucursalEmpleado;
    }
    
    
    public String obtenerNombreEmpleado(){
        return this.nombreEmpleado;
    }

    public void colocarNombreEmpleado(String nombre){
        this.nombreEmpleado = nombre;
    }

    public String obtenerApellidoEmpleado(){
        return this.apellidoEmpleado;
    }

    public void colocarApellidoEmpleado(String apellido){
        this.apellidoEmpleado = apellido;
    }

    public String obtenerCorreoEmpleado(){
        return this.correoEmpleado;
    }

    public void colocarCorreoEmpleado(String correo){
        this.correoEmpleado = correo;
    }

    public String obtenerTelefonoEmpleado(){
        return this.telefonoEmpleado;
    }

    public void colocarTelefonoEmpleado(String telefono){
        this.telefonoEmpleado = telefono;
    }

    public String obtenerSexoEmpleado(){
        return this.sexoEmpleado;
    }

    public void colocarSexoEmpleado(String sexo){
        this.sexoEmpleado = sexo;
    }

    public String obtenerTurnoEmpleado(){
        return this.turnoEmpleado;
    }

    public void colocarTurnoEmpleado(String turno){
        this.turnoEmpleado = turno;
    }

    public String obtenerRolEmpleado(){
        return this.rolEmpleado;
    }

    public void colocarRolEmpleado(String rol){
        this.rolEmpleado = rol;
    }

    public String obtenerSucursalEmpleado(){
        return this.sucursalEmpleado;
    }   

    public void colocarSucursalEmpleado(String sucursal){
        this.sucursalEmpleado = sucursal;
    }
}
