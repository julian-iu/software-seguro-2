
package com.iudigital.supermarket.domain;

import java.util.List;

public class Cliente {
    //atributos
   private final int id;
   private final String nombre;
   private final List<Producto> productos;

    //constructor 
    public Cliente(int id, String nombre, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }

      //metodo
    @Override
    public String toString() {
        return "Cliente"+this.id  + ":" + this.nombre ;
    }

     // METODOS  GET 
    public int getId() {
        return id;
    }
  
    public String getNombre() {
        return nombre;
    }
    
   
    public List<Producto> getProductos() {
        return productos;
    }
   
   
   
}
