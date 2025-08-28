package com.mycompany.siacentrosdesangre;

import java.io.*;

public class SIACentrosDeSangre {

    public static void main(String[] args) throws IOException {
        SIACentrosDeSangre sistema = new SIACentrosDeSangre();
        CentroDeSangre centro = sistema.crearCentroSangre();
        String opLeida;
        int op;
        do{
            System.out.println("SISTEMA DE INFORMACION PARA CENTROS DE SANGRE");
            System.out.println("=============================================");
            System.out.println("1# Agregar registros");
            System.out.println("2# Mostrar registros");
            System.out.println("3# Cerrar programa");
            System.out.println("=============================================");
            opLeida = sistema.lector("Ingrese una opcion:");
            op = Integer.parseInt(opLeida);
            
            switch(op){
                case 1:
                    sistema.menuAgregar(centro);
                    break;
                case 2:
                    sistema.menuMostrar(centro);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        }while(op != 3);
    }
    
    public void menuAgregar(CentroDeSangre centro) throws IOException {
        while(true){
            System.out.println("\n===== Menu agregar =====");
            System.out.println("1. Agregar Campania");
            System.out.println("2. Agregar Donacion");
            System.out.println("3. Volver");
            System.out.println("==========================");
            String entrada = lector("Seleccione una opcion: ");
            int opcion = Integer.parseInt(entrada);
            
            switch(opcion){
                case 1:
                    agregarCampania(centro);
                    break;
                
                case 2:
                    agregarDonacion(centro);
                    break;
                
                case 3:
                   System.out.println("Volviendo a menu principal..");
                   return;
                
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    } 
    
    public void menuMostrar(CentroDeSangre centro) throws IOException {
        while(true){
            System.out.println("\n===== Menu mostrar =====");
            System.out.println("1. Mostrar Campania");
            System.out.println("2. Mostrar Donacion");
            System.out.println("3. Mostrar stock de sangre del centro.");
            System.out.println("4. Volver");
            System.out.println("==========================");
            String entrada = lector("Seleccione una opcion: ");
            int opcion = Integer.parseInt(entrada);
            
            switch(opcion){
                case 1:
                    mostrarCampanias(centro);
                    break;
                
                case 2:
                    mostrarDonaciones(centro);
                    break;
                    
                case 3:
                    mostrarStockSangre(centro);
                
                case 4:
                   System.out.println("Volviendo a menu principal..");
                   return;
                
                default:
                    System.out.println("Opcion invalida.");
                
            }
        }
    } 
    
    
    public String[] leerArchivo()throws FileNotFoundException, IOException{
        String ruta = "tiposDeSangre.txt";
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        String cadena [] = new String[10];
        String linea = lector.readLine();
        int i = 0;
        while (linea != null) {
            cadena[i] = linea;
            linea = lector.readLine();
            i ++;
        }
        return cadena;
    }
    
    public String lector(String mensaje) throws IOException{
        System.out.println(mensaje);
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String cadena = lector.readLine();
        return cadena;
    }
    
    public CentroDeSangre crearCentroSangre() throws IOException{
        CentroDeSangre centro = new CentroDeSangre(lector("Ingrese nombre del nuevo centro de sangre : "));
        return centro;
    }
    
    public void agregarCampania(CentroDeSangre centro) throws IOException{
        String localidad = lector("ingrese localidad de la campania : ");
        Campania buscado = centro.buscarCampania(localidad);
        if(buscado == null){
            Campania nuevo= new Campania(localidad);
            centro.agregarCampania(nuevo);
        }
    }
    
    public void crearStockSangre(CentroDeSangre centro) throws IOException{
        int i;
        String cadena[] = leerArchivo();
        for(i= 0; cadena[i] != null ; i ++){
            centro.agregarStockSangre(cadena[i], 0);
        }
    }
    
    public void mostrarStockSangre(CentroDeSangre centro) throws IOException{
        int i;
        String cadena[] = leerArchivo();
        for(i= 0; cadena[i] != null ; i ++){
            System.out.println(cadena[i] + " " + centro.getStockSangre(cadena[i]));
        }
    }
    
    public void agregarDonacion(CentroDeSangre centro) throws IOException{
        String aux = lector("ingrese la locacion de la campania : ");
        Campania camp = centro.buscarCampania(aux);
        if(camp != null){
            aux = lector("ingrese el rut del donante : ");
            Donacion donante = camp.buscarDonacion(aux);
            if(donante == null){
                Donacion nuevo = crearDonacion(aux);
                camp.agregarDonacion(nuevo);
                centro.agregarStockSangre(nuevo.getDonador().getTipoSangre(), 1);
            }
        }
        
    }
    
    public Donacion crearDonacion(String rut) throws IOException{
        String nombre = lector("Ingrese el nombre del donante: ");
        int edad = Integer.parseInt(lector("ingrese la edad del donante: "));
        String tipoSangre = lector("Ingrese el tipo de sangre del donante: ");
        String numeroTelefonico = lector("Ingrese el numero de telefono del donante: ");
        
        Donante persona = new Donante(rut, nombre, edad, tipoSangre, numeroTelefonico);
        
        String fecha = lector("Ingrese fecha: ");
        Donacion donacion = new Donacion(fecha, 1, persona);
        return donacion;
    }
    
    public void mostrarCampanias(CentroDeSangre centro){
        System.out.println("El centro " + centro.getNombre() + " tiene campanias en : ");
        String locaciones [] = centro.conseguirNombresCampanias();
        int i;
        for (i = 0; i < locaciones.length; i ++){
            System.out.println("    - "+ locaciones[i]);
        }
    }
    
    public void mostrarDonaciones(CentroDeSangre centro){
        System.out.println("El centro " + centro.getNombre() + " tiene campanias en : ");
        String locaciones [] = centro.conseguirNombresCampanias();
        String donantes [];
        Campania ll;
        
        int i, j;
        for (i = 0; i < locaciones.length; i ++){
            System.out.println("    - "+ locaciones[i]);
            ll = centro.buscarCampania(locaciones[i]);
            donantes = ll.obtenerInfoDonadores();
            
            for(j = 0; j < donantes.length; j ++){
                System.out.println("            - "+ donantes[j]);
            }
        }
    }
}