package com.example.fei_eats_mov;

public class Producto {
    public String produid;
    public String nombre;
    public String precio;
    public String descripcion;
    public String categoria;

    public Producto(){

    }

    public Producto(String produid, String nombre, String precio, String descripcion, String categoria){
        this.produid = produid;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;

    }

    public String getProduid() {
        return produid;
    }

    public void setProduid(String produid) {
        this.produid = produid;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
