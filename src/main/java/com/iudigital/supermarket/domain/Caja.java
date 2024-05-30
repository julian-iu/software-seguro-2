
package com.iudigital.supermarket.domain;


public class Caja implements Runnable {
    //atributos
     private int id;
    private Cliente cliente;
    private long  tiempoInicial;
    
     //constructor 
    public Caja(int id, Cliente cliente, long tiempoInicial) {
        this.id = id;
        this.cliente = cliente;
        this.tiempoInicial = tiempoInicial;
    }

     // METODOS  GET Y SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }
    
      //metodo
      @Override
    public void run() {
        float facturacion =0;
        System.out.println("La caja n " + this.id+ " ha empezado a procesar"
      +"la compra del cliente "+this.cliente.getNombre()
      +" -> "+(System.currentTimeMillis()- this.tiempoInicial) / 1000 
      + "segundos");
        
        for(Producto p : this.cliente.getProductos()){
            
            esperarSegundos();
            
            facturacion+= p.getPrecio();
              System.out.println(" l. " 
                      + p.getNombre()+ ", Valor: "+p.getPrecio()
                      +" -> "+(System.currentTimeMillis()- this.tiempoInicial) / 1000 + " segundos");
        }
          System.out.println(" l. proceso de facturación terminado, el total fue de " +facturacion +"$"
          +" -> "+(System.currentTimeMillis()- this.tiempoInicial) / 1000 
          + "segundos");      
    }
    
    private void  esperarSegundos(){
        try {
        // Espera de 1 segundo (1000 milisegundos)
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        // Reinterrumpe el hilo actual
        Thread.currentThread().interrupt();
        }
    }
     @Override
    public String toString(){
       return "Caja n° :" + this.id
       + "\n l." + this.cliente;
    } 
}
