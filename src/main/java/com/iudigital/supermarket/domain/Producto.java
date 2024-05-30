
package com.iudigital.supermarket.domain;


public class Producto {
    //atributos
    private String nombre;
    private float precio;

    //constructor 
    public Producto(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
         //metodo
    @Override
    public String toString() {
        return this.nombre +": "+this.precio+"$";
    }

    // METODOS  GET Y SET
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
