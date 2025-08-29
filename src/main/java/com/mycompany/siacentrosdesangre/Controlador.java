
package com.mycompany.siacentrosdesangre;

import java.io.IOException;

public class Controlador {
    
    
    
    public void iniciar()throws IOException{
        CentroDeSangre centro = this.crearCentroSangre();
        Menu menuPrincipal = new Menu();
        this.crearStockSangre(centro);
        menuPrincipal.mostrarMenuPrincipal(this,centro);
    }
    
    public  CentroDeSangre crearCentroSangre() throws IOException{
        CentroDeSangre centro = new CentroDeSangre(Herramientas.lector("Ingrese nombre del nuevo centro de sangre : "));
        return centro;
    }
    
    public void crearStockSangre(CentroDeSangre centro) throws IOException{
        int i;
        String cadena[] = Herramientas.leerArchivo();
        for(i= 0; cadena[i] != null ; i ++){
            centro.agregarStockSangre(cadena[i]);
        }
    }
    
    public void mostrarStockSangre(CentroDeSangre centro) throws IOException{
        int i;
        String cadena[] = Herramientas.leerArchivo();
        for(i= 0; cadena[i] != null ; i ++){
            System.out.println(cadena[i] + " " + centro.getStockSangre(cadena[i]));
        }
    }
    
    public void agregarCampania(CentroDeSangre centro) throws IOException{
        String localidad = Herramientas.lector("ingrese localidad de la campania : ");
        Campania buscado = centro.buscarCampania(localidad);
        if(buscado == null){
            Campania nuevo= new Campania(localidad);
            centro.agregarCampania(nuevo);
        }
    }
    
    public void mostrarCampanias(CentroDeSangre centro){
        System.out.println("El centro " + centro.getNombre() + " tiene campanias en : ");
        String locaciones [] = centro.conseguirNombresCampanias();
        int i;
        for (i = 0; i < locaciones.length; i ++){
            System.out.println("    - "+ locaciones[i]);
        }
    }
    
    public void agregarDonacion(CentroDeSangre centro) throws IOException{
        String aux = Herramientas.lector("ingrese la locacion de la campania : ");
        Campania camp = centro.buscarCampania(aux);
        if(camp != null){
            aux = Herramientas.lector("ingrese el rut del donante : ");
            Donacion donante = camp.buscarDonacion(aux);
            if(donante == null){
                Donacion nuevo = crearDonacion(aux);
                camp.agregarDonacion(nuevo);
                centro.agregarStockSangre(nuevo.getDonador().getTipoSangre(), 1);
            }
        }
    }
    
    public Donacion crearDonacion(String rut) throws IOException{
        String nombre = Herramientas.lector("Ingrese el nombre del donante: ");
        int edad = Integer.parseInt(Herramientas.lector("ingrese la edad del donante: "));
        String tipoSangre = Herramientas.lector("Ingrese el tipo de sangre del donante: ");
        String numeroTelefonico = Herramientas.lector("Ingrese el numero de telefono del donante: ");
        
        Donante persona = new Donante(rut, nombre, edad, tipoSangre, numeroTelefonico);
        
        String fecha = Herramientas.lector("Ingrese fecha: ");
        Donacion donacion = new Donacion(fecha, 1, persona);
        return donacion;
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
    

