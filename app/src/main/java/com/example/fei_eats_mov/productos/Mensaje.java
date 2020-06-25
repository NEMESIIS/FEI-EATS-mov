package com.example.fei_eats_mov.productos;

public class Mensaje {
    private  String nombre;
    private String precio;
    private String descripcion;

    //Clase constructora

    public Mensaje (String nombre){

    }
    public Mensaje(String nombre, String precio, String descripcion){
        this.nombre=nombre;
        this.precio=precio;
        this.descripcion=descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
