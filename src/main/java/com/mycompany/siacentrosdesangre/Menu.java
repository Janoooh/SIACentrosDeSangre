
package com.mycompany.siacentrosdesangre;

import java.io.IOException;

public class Menu {
    
    /*Metodo mostrarMenuPrincipal: Encargado de mostrar las opciones
    principales del sistema, permitiendo interactuar con este para poder
    moverse por el programa.*/
    public void mostrarMenuPrincipal(CentroDeSangre centro) throws IOException {
        String opLeida;
        int op = 0;

        do {
            System.out.println("\nSISTEMA DE INFORMACION CENTRO DE SANGRE "+centro.getNombre().toUpperCase());
            System.out.println("===================================================");
            System.out.println("1. Agregar registros.");
            System.out.println("2. Mostrar registros.");
            System.out.println("3. Cerrar programa.");
            System.out.println("===================================================");


            opLeida = Herramientas.lector("Ingrese una opcion: ");
            op = Integer.parseInt(opLeida);

            switch (op) {
                case 1:
                    this.menuAgregar(centro);
                    break;
                case 2:
                    this.menuMostrar(centro);
                    break;
                case 3:
                    System.out.println("Cerrando programa...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (op != 3);
       }

    /*Metodo menuAgregar: Representa el menu donde se encuentran todas las
    opciones para agregar datos al sistema.*/
    public void menuAgregar(CentroDeSangre centro) throws IOException {
        while(true){
            System.out.println("\n=========    AGREGAR REGISTRO    ================");
            System.out.println("===================================================");
            System.out.println("1. Agregar Campania.");
            System.out.println("2. Agregar Donacion.");
            System.out.println("3. Agregar sangre al inventario.");
            System.out.println("4. Volver.");
            System.out.println("===================================================");
            String entrada = Herramientas.lector("Seleccione una opcion: ");
            int opcion = Integer.parseInt(entrada);

            switch(opcion){
                case 1:
                    
                    break;

                case 2:
                    centro.agregarDonacion();
                    break;

                case 3:
                    centro.agregarSangre();
                    break;
                    
                case 4:
                   System.out.println("Volviendo a menu principal..");
                   return;

                default:
                    System.out.println("Opcion invalida.");
            }
            }
    } 

    
    /*Metodo menuMostrar: Representa el menu donde se encuentran todas las
    opciones para mostrar datos registrados en el sistema.*/
    public void menuMostrar(CentroDeSangre centro) throws IOException {
        while(true){
            System.out.println("\n========    MOSTRAR REGISTROS    ================");
            System.out.println("===================================================");
            System.out.println("1. Mostrar Campania.");
            System.out.println("2. Mostrar Donacion.");
            System.out.println("3. Mostrar stock de sangre del centro.");
            System.out.println("4. Volver.");
            System.out.println("===================================================");
            String entrada = Herramientas.lector("Seleccione una opcion: ");
            int opcion = Integer.parseInt(entrada);

            switch(opcion){
                case 1:
                    centro.mostrarCampanias();
                    break;

                case 2:
                    centro.mostrarDonacionesDeCampanias();
                    break;

                case 3:
                    centro.mostrarStockSangre(centro);
                    break;

                case 4:
                   System.out.println("Volviendo a menu principal..");
                   return;

                default:
                    System.out.println("Opcion invalida.");

            }
        }
    } 
}