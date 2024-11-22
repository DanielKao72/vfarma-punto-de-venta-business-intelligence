package com.vfarma.Farmacia.DatosFarmacia;

public class InformacionEmpleado {

    private String claveEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String correoEmpleado;
    private String telefonoEmpleado;
    private String sexoEmpleado;
    private String turnoEmpleado;
    private String rolEmpleado;

    public InformacionEmpleado(String clave, String nombre, String apellido, String correo, String telefono, String sexo, String turno, String rol) {
        this.claveEmpleado = clave;
        this.nombreEmpleado = nombre;
        this.apellidoEmpleado = apellido;
        this.correoEmpleado = correo;
        this.telefonoEmpleado = telefono;
        this.sexoEmpleado = sexo;
        this.turnoEmpleado = turno;
        this.rolEmpleado = rol;
    }

    public String obtenerClaveEmpleado(){
        return this.claveEmpleado;
    }
    
    public String obtenerNombreEmpleado(){
        return this.nombreEmpleado;
    }

    public String obtenerApellidoEmpleado(){
        return this.apellidoEmpleado;
    }

    public String obtenerCorreoEmpleado(){
        return this.correoEmpleado;
    }

    public String obtenerTelefonoEmpleado(){
        return this.telefonoEmpleado;
    }

    public String obtenerSexoEmpleado(){
        return this.sexoEmpleado;
    }

    public String obtenerTurnoEmpleado(){
        return this.turnoEmpleado;
    }

    public String obtenerRolEmpleado(){
        return this.rolEmpleado;
    }

    public void colocarClaveEmpleado(String clave){
        this.claveEmpleado = clave;
    }

    public void colocarNombreEmpleado(String nombre){
        this.nombreEmpleado = nombre;
    }

    public void colocarApellidoEmpleado(String apellido){
        this.apellidoEmpleado = apellido;
    }

    public void colocarCorreoEmpleado(String correo){
        this.correoEmpleado = correo;
    }

    public void colocarTelefonoEmpleado(String telefono){
        this.telefonoEmpleado = telefono;
    }

    public void colocarSexoEmpleado(String sexo){
        this.sexoEmpleado = sexo;
    }

    public void colocarTurnoEmpleado(String turno){
        this.turnoEmpleado = turno;
    }

    public void colocarRolEmpleado(String rol){
        this.rolEmpleado = rol;
    }
}