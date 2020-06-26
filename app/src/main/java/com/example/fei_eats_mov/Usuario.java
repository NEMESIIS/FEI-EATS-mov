package com.example.fei_eats_mov;

public class Usuario {
    public String userid;
    public String nombre;
    public String correo;
    public String usuario;
    public String celular;
    public String contrasena;
    public String rol;


    public Usuario (){
//--
    }

    public Usuario(String userid, String nombre, String correo, String usuario, String celular, String contrasena, String rol){
        this.userid = userid;
        this.nombre = nombre;
        this.correo = correo;
        this.usuario = usuario;
        this.celular = celular;
        this.contrasena = contrasena;
        this.rol = rol;

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
