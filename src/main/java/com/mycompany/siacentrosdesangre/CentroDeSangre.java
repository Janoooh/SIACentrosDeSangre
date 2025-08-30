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
        stockSangre.put(nombre.toUpperCase(), (stockSangre.get(nombre.toUpperCase()) + cantidad));
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
    
    public void agregarSangre()throws IOException{
        int nSangres;
        String tipoSangre;
        tipoSangre = Herramientas.lector("Ingrese el tipo de sangre: ");
        while(!stockSangre.containsKey(tipoSangre.toUpperCase()))
            tipoSangre = Herramientas.lector("El tipo de sangre no existe, ingrese un tipo correcto: ");
        
        nSangres = Integer.parseInt(Herramientas.lector("Ingrese la cantidad de bolsas de sangre: "));
        if(nSangres <= 0)
            System.out.println("El valor ingresado es invalido.");
        else
            this.agregarStockSangre(tipoSangre, nSangres);
         
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
        if(campanias.isEmpty())
            System.out.println("No existen campanias en el centro de sangre.");
        else
            System.out.println("\nEl centro " + nombre + " tiene campanias en : ");
        
        for(x = 0; x < campanias.size() ; x++){
            aux = campanias.get(x);
            idCamp = aux.getId();
            localCamp = aux.getLocalidad();
            System.out.println("Campania en "+localCamp + "   -  ID "+ idCamp);
        }
    }
    
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
    public void crearStockSangre() throws IOException{
        int x;
        ArrayList<String> datosArchivo = Herramientas.leerArchivo("tiposDeSangre.txt");
        for(x = 0; x < datosArchivo.size() ; x ++){
            this.agregarStockSangre(datosArchivo.get(x));
        }
    }
    public void mostrarStockSangre(CentroDeSangre centro) throws IOException{
        int i;
        ArrayList<String> datosArchivo = Herramientas.leerArchivo("tiposDeSangre.txt");
        String sangre;
        for(i= 0; i < datosArchivo.size() ; i ++){
            sangre = datosArchivo.get(i);
            System.out.println(sangre + " " + this.getStockSangre(sangre));
        }
    }
    
    public void mostrarDonacionesDeCampanias(){
        int x;
        Campania aux;
        if(campanias.isEmpty())
            System.out.println("No existen campanias en el centro de sangre.");
        else
            System.out.println("Donaciones por campania.\n");
        
        for(x = 0; x < campanias.size() ; x++){
            aux = campanias.get(x);
            aux.mostrarDonaciones();
        }
    }
    
    public Donacion crearDonacion(String rut) throws IOException{
        String idLeida = Herramientas.lector("Ingrese ID de donacion(Numerica): ");
        String fecha = Herramientas.lector("Ingrese fecha de la donacion(Formato DD/MM/AAAA): ");
        String nombre = Herramientas.lector("Ingrese el nombre del donante: ");
        int edad = Integer.parseInt(Herramientas.lector("Ingrese la edad del donante: "));
        String tipoSangre = Herramientas.lector("Ingrese el tipo de sangre del donante: ");
        while(!stockSangre.containsKey(tipoSangre.toUpperCase()))
            tipoSangre = Herramientas.lector("El tipo de sangre no existe, ingrese un tipo correcto: ");
        
        String numeroTelefonico = Herramientas.lector("Ingrese el numero de telefono del donante: ");
        
        Donante persona = new Donante(rut, nombre, edad, tipoSangre, numeroTelefonico);
        
        Donacion donacion = new Donacion(Integer.parseInt(idLeida), fecha, persona);
        return donacion;
    }
    
    //Metodo para agregar una donacion nueva que sera ingresada por el usuario.
    public void agregarDonacion() throws IOException{
        String idLeida = Herramientas.lector("Ingrese la ID de la campania(Numerica): ");
        String rutLeido;
        Donacion donante, nuevo;
        Campania camp = this.buscarCampania(Integer.parseInt(idLeida));
        if(camp != null){
            rutLeido = Herramientas.lector("Ingrese el rut del donante : ");
            donante = camp.buscarDonacion(rutLeido);
            if(donante == null){
                nuevo = crearDonacion(rutLeido);
                camp.agregarDonacion(nuevo);
                this.agregarStockSangre(nuevo.getDonador().getTipoSangre(), 1);
            }else{
                System.out.println("El rut ingresado ya existe.");
            }
        }else{
            System.out.println("La campania con id "+idLeida+" no existe.");
        }
    }
    
    //Metodo para agregar una donacion que ya se encuentra registrada en el txt
    public void agregarDonacion(Donacion nueva, int id, String tipoSangre){
        Campania aux;
        int x;
        for(x = 0; x < campanias.size(); x++){
            aux = campanias.get(x);
            if(id == aux.getId()){
                aux.agregarDonacion(nueva);
                this.agregarStockSangre(tipoSangre, 1);
            }
        }
    }
    
    public void agregarCampaniaNueva() throws IOException{
        int id;
        String localidad;
        id = Integer.parseInt(Herramientas.lector("Ingrese ID para campania(Numerica): "));
        localidad = Herramientas.lector("Ingrese localidad de la campania : ");
        Campania buscado = buscarCampania(id);
        if(buscado == null){
            Campania nuevo= new Campania(id ,localidad);
            agregarCampania(nuevo);
        }
    }
 
}

