package com.mycompany.siacentrosdesangre;
import java.util.ArrayList;

public class Campania {
    private int id;
    private String localidad;
    private ArrayList<Donacion> donaciones;
    
    public Campania(){
        this.donaciones = new ArrayList<>();
    }
    
    public Campania(int id, String localidad){
        this.id = id;
        this.localidad = localidad;
        this.donaciones = new ArrayList<>();
    }
 
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void agregarDonacion(Donacion nuevo) {
        donaciones.add(nuevo);
    }
    
    //necesario para crear por ventana
    /*public void agregarDonacion(int id, String fecha, String rut, String nombre, int edad, String tipoSangre, String telefono){
        //Donacion nuevo = new Donacion (id, fecha, rut, nombre, edad, tipoSangre, telefono);
        //donaciones.add(nuevo);
    }*/
    
    public String getLocalidad() {
        return localidad;
    }
    
    /*Metodo buscarDonacion: Encargado de buscar una donacion en el 
    ArrayList de donaciones, con una id pasada por parametro. Si la encuentra,
    retorna una referencia a ese objeto, sino, un null.*/
    public Donacion buscarDonacion(int buscado){
        int i;
        for(i = 0; i < donaciones.size(); i ++){
            if(buscado == donaciones.get(i).getId())
                return donaciones.get(i);
        }
        return null;
    }
    
    /*Metodo mostrarDonaciones: Encargado de mostrar por consola
    todas las donaciones existentes en esta campania.*/
    public void mostrarDonaciones(){
        int x;
        Donacion aux;
        System.out.println("\nCampania en "+localidad+" - ID "+id +" - "+donaciones.size()+" Registro(s)");
        if(donaciones.isEmpty())
            System.out.println("No existen donaciones en esta campania.");
        
        for(x = 0; x < donaciones.size() ; x++){
            aux = donaciones.get(x);
            aux.mostrarDonacion();
            
        }
    }
    public Donacion borrarDonacion(int id){
        int i;
        Donacion actual;
        for(i = 0; i < donaciones.size(); i++){
            actual = donaciones.get(i);
            if(actual.getId() == id){
                donaciones.remove(i);
                return actual;
            }
        }
        return null; 
    }
    
}
