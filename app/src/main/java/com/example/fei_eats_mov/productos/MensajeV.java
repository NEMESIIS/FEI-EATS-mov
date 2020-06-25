package com.example.fei_eats_mov.productos;

public class MensajeV {
    private  String nombre;
    private String usuario;
    private String celular;

    //Clase constructora

    public MensajeV(String nombre){

    }
    public MensajeV(String nombre, String usuario, String celular){
        this.nombre=nombre;
        this.usuario=usuario;
        this.celular=celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
