package com.iudigital.supermarket.Main;
//todoas las importaciones a utilizar
import com.iudigital.supermarket.domain.Producto;
import com.iudigital.supermarket.domain.Cliente;
import com.iudigital.supermarket.domain.Caja;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuperMarket {
private static final Logger LOGGER = Logger.getLogger(SuperMarket.class.getName());
    //hacemos uso de scanner para tomar los datos
    static Scanner sc = new Scanner(System.in);
 // se crea la clase main para ejecutar nuestro proyecto en la clase supermarket
    public static void main(String[] args) {
      //se crear atributo de tipo int llamado option para el menu
        int option = -1;
        
        long tiempoInicial = System.currentTimeMillis();
        //se crean arreglos para crear clientes,cajas,productos
        List<Cliente> clientes = new ArrayList<>();
        List<Caja> cajas = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
       // productos creados
        Producto producto1 = new Producto("Manzana", 2.3f);
        Producto producto2 = new Producto("Cafe", 10.8f);
        Producto producto3 = new Producto("Lulo", 4.3f);
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
       //clientes creados
        Cliente cl1 = new Cliente(1, "Jorge", productos);
        Cliente cl2 = new Cliente(2, "Ana Luz", productos);
        Cliente cl3 = new Cliente(3, "Pedro", productos);
        clientes.add(cl1);
        clientes.add(cl2);
        clientes.add(cl3);
       //cajas creadas
        Caja cj1 = new Caja(1, cl1, tiempoInicial);
        Caja cj2 = new Caja(2, cl2, tiempoInicial);
        Caja cj3 = new Caja(3, cl3, tiempoInicial);
        cajas.add(cj1);
        cajas.add(cj2);
        cajas.add(cj3);

        // se crea el menu principal selecionar un numero
        while (option != 7) {

             LOGGER.info("""
                    Menú:
                    1. Iniciar proceso de compra
                    2. Crear clientes
                    3. Mostrar clientes
                    4. Mostrar clientes por id
                    5. Crear caja
                    6. Mostrar cajas Activas
                    7. Salir
                    """);
             LOGGER.info("Respuesta:");
            option = sc.nextInt();

            sc.nextLine();

            switch (option) {
                case 1 -> iniciarHilos(cajas);
                case 2 -> clientes.add(crearCliente());
                case 3 -> leerClientes(clientes);
                case 4 -> leerCliente(obtenerClientePorId(clientes));
                case 5 -> crearCaja(cajas, obtenerClientePorId(clientes), tiempoInicial);
                case 6 -> leerCajas(cajas);
                case 7 ->  LOGGER.info("Cerrando app");
                default -> LOGGER.warning("Digite un número válido");
            }
        }
        sc.close();
    }
    //metodo para mostar la lista de los clientes
 private static void leerClientes(List<Cliente> listaCliente) {
        LOGGER.info("Lista de clientes");
        // hacemos uso if para obtener la lista
        if (!listaCliente.isEmpty()) {
            for (Cliente cliente : listaCliente) {
                LOGGER.info("* " + cliente);
            }
            //si noy clientes creados nos muestra el mensaje
        } else {
            LOGGER.info("No hay clientes en la lista");
        }
       LOGGER.info("");
    }

    private static void leerCliente(Cliente cliente) {
        if (cliente == null) {
            LOGGER.warning("Cliente no encontrado.");
            return;
        }

       LOGGER.info("Cliente: " + cliente);
       LOGGER.info("Productos: ");
        for (Producto producto : cliente.getProductos()) {
           LOGGER.info(" - " + producto);
        }
       LOGGER.info("");
    }
   //metodo para buscar clientes por su id 
    private static Cliente obtenerClientePorId(List<Cliente> listaCliente) {
        LOGGER.info("Digite el id del cliente");
        int idCliente = sc.nextInt();
        sc.nextLine();

        for (Cliente cliente : listaCliente) {
            if (idCliente == cliente.getId()) {
                return cliente;
            }
        }
       LOGGER.warning("No se encuentra el cliente");
       LOGGER.info("");
        return null;
    }
    //metodo para crear clientes 
    private static Cliente crearCliente() {
        //se piden los datos 
        LOGGER.info("Digite el id");
        int idCliente = sc.nextInt();
        sc.nextLine();

         LOGGER.info("Digite el nombre");
        String nombreCliente = sc.nextLine();
    //se guardad en el arreglo y se pide un nuevo producto
         LOGGER.info("Qué productos comprará el cliente");
        List<Producto> productosCliente = crearProductos();
        Cliente nuevoCliente =new Cliente(idCliente, nombreCliente, productosCliente);
        // se retorna el nuevocliente
        return nuevoCliente;
    }

  //metodo para crear productos 
     private static List<Producto> crearProductos() {
      List<Producto> productos = new ArrayList<>();
        int option;
        
        do {
             LOGGER.info("Digite el nombre del producto");
            String nombreProducto = sc.nextLine();

            float precioProducto = 0;
            while (true) {
                try {
                     LOGGER.info("Digite el precio del producto");
                    precioProducto = sc.nextFloat();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                   LOGGER.log(Level.SEVERE,"Entrada inválida. Por favor, digite un número válido para el precio.");
                    sc.nextLine(); // Clear the invalid input
                }
            }
       
        Producto productoNuevo = new Producto(nombreProducto, precioProducto);
        productos.add(productoNuevo);
//si deseas agregar otro producto al cliente creado seleciona 1 si la compra esta lista 0
         LOGGER.info("¿Desea agregar otro producto? (1=si/0=no)");
         LOGGER.info("-> Respuesta:");
        option = sc.nextInt();
        sc.nextLine();
    } while (option == 1);

    LOGGER.info("");
    
    return productos;
}
    //metodo para crear caja nueva para crear una caja tiene que crear un cliente antes 
    private static void crearCaja(List<Caja> listaCajas, Cliente cliente, long time) {
        int idCaja = listaCajas.size() + 1;
        Caja cajaNueva = new Caja(idCaja, cliente, time);
        listaCajas.add(cajaNueva);
         LOGGER.info("Caja creada con éxito");
    }
//motodo para mostar las cajas 
    private static void leerCajas(List<Caja> listaCajas) {
        LOGGER.info("Lista de cajas");

        if (!listaCajas.isEmpty()) {
            for (Caja caja : listaCajas) {
                 LOGGER.info("* " + caja);
            }
        } else {
             LOGGER.info("No hay cajas en la lista");
        }
        LOGGER.info("");
    }
   //metodo para iniciar el proceso en el supermercado en todas las cajas 
    private static void iniciarHilos(List<Caja> listaCajas) {
        long time = System.currentTimeMillis();
        for (Caja caja : listaCajas) {
            caja.setTiempoInicial(time);
            caja.run();
            LOGGER.info("");
        }
    }
   
}

