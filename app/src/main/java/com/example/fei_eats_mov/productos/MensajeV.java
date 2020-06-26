package com.example.fei_eats_mov.productos;

public class MensajeV {
    private  String nombre;
    private String correo;
    private String celular;
//--
    //Clase constructora

    public MensajeV(String nombre){

    }
    public MensajeV(String nombre, String correo, String celular){
        this.nombre=nombre;
        this.correo=correo;
        this.celular=celular;
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

    public void setCorreo(String usuario) {
        this.correo = usuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
