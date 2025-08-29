package com.mycompany.siacentrosdesangre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CentroDeSangre {
    private String nombre;
    private ArrayList<Campania> campanias;
    private HashMap<String, Integer> stockSangre;
    
    public CentroDeSangre(){
        this.campanias = new ArrayList<>();
        this.stockSangre = new HashMap<>();
    }
    
    public CentroDeSangre(String nombre){
        this.nombre = nombre;
        this.campanias = new ArrayList<>();
        this.stockSangre = new HashMap<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarCampania(Campania nuevo) {
        campanias.add(nuevo);
    }
    
    public void agregarStockSangre(String nombre) {
        stockSangre.put(nombre, 0);
    }

    public void agregarStockSangre(String nombre, int cantidad) {
        stockSangre.put(nombre, (stockSangre.get(nombre) + cantidad));
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Integer getStockSangre(String clave) {
        if(stockSangre.containsKey(clave) == true)
            return stockSangre.get(clave);
        else
            return null;
    }

    public Campania buscarCampania(int id) {
        int i;
        for(i = 0; i < campanias.size(); i ++){
            if(id == campanias.get(i).getId())
                return campanias.get(i);
        }
        return null;
    }
    
    public void mostrarCampanias(){
        Campania aux;
        String localCamp;
        int x, idCamp;
        System.out.println("El centro " + nombre + " tiene campanias en : ");
        for(x = 0; x < campanias.size() ; x++){
            aux = campanias.get(x);
            idCamp = aux.getId();
            localCamp = aux.getLocalidad();
            System.out.println(idCamp + "   - "+ localCamp);
        }
    }
    
    /*public void mostrarDonaciones(){
        int x, y;
        
        System.out.println("El centro " + nombre + " tiene campanias en : ");
        
        
        
        for (x = 0; x < campanias.size(); x++){
            System.out.println("    - "+ locaciones[i]);
            ll = centro.buscarCampania(locaciones[i]);
            donantes = ll.obtenerInfoDonadores();
            
            for(y = 0; y < donantes.length; y ++){
                System.out.println("            - "+ donantes[j]);
            }
        }
    }*/
    
    public String []conseguirNombresCampanias(){
        String localidades[] = new String[campanias.size()];
        int i;
        
        for (i = 0; i < campanias.size(); i ++){
            localidades[i] = campanias.get(i).getLocalidad();
        }
        return localidades;
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
    
    public void mostrarDonacionesDeCampanias(){
        int x;
        Campania aux;
        System.out.println(campanias.size());
        for(x = 0; x < campanias.size() ; x++){
            aux = campanias.get(x);
            aux.mostrarDonaciones();
        }
    }
    
    public Donacion crearDonacion(String rut) throws IOException{
        String idLeida = Herramientas.lector("Ingrese ID: ");
        String fecha = Herramientas.lector("Ingrese fecha: ");
        String nombre = Herramientas.lector("Ingrese el nombre del donante: ");
        int edad = Integer.parseInt(Herramientas.lector("Ingrese la edad del donante: "));
        String tipoSangre = Herramientas.lector("Ingrese el tipo de sangre del donante: ");
        String numeroTelefonico = Herramientas.lector("Ingrese el numero de telefono del donante: ");
        
        Donante persona = new Donante(rut, nombre, edad, tipoSangre, numeroTelefonico);
        
        Donacion donacion = new Donacion(Integer.parseInt(idLeida), fecha, persona);
        return donacion;
    }
    
    public void agregarDonacion() throws IOException{
        String idLeida = Herramientas.lector("Ingrese la ID de la campania : ");
        String rutLeido;
        Donacion donante, nuevo;
        Campania camp = this.buscarCampania(Integer.parseInt(idLeida));
        if(camp != null){
            rutLeido = Herramientas.lector("Ingrese el rut del donante : ");
            donante = camp.buscarDonacion(rutLeido);
            if(donante == null){
                nuevo = crearDonacion(rutLeido);
                camp.agregarDonacion(nuevo);
                agregarStockSangre(nuevo.getDonador().getTipoSangre(), 1);
            }
        }
    }
    
    public void agregarCampaniaNueva() throws IOException{
        int id;
        String localidad;
        id = Integer.parseInt(Herramientas.lector("Ingrese ID para campania : "));
        localidad = Herramientas.lector("Ingrese localidad de la campania : ");
        Campania buscado = buscarCampania(id);
        if(buscado == null){
            Campania nuevo= new Campania(id ,localidad);
            agregarCampania(nuevo);
        }
    }
    
    public void crearStockSangre() throws IOException{
        int i;
        String cadena[] = Herramientas.leerArchivo();
        for(i= 0; cadena[i] != null ; i ++){
            agregarStockSangre(cadena[i]);
        }
    }
    
    public void mostrarStockSangre() throws IOException{
        int i;
        String cadena[] = Herramientas.leerArchivo();
        for(i= 0; cadena[i] != null ; i ++){
            System.out.println(cadena[i] + " " + getStockSangre(cadena[i]));
        }
    }
}

