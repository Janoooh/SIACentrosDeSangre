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
    
    public String getLocalidad() {
        return localidad;
    }
    
    public Donacion buscarDonacion(String buscado){
        int i;
        for(i = 0; i < donaciones.size(); i ++){
            if(donaciones.get(i).getDonador().getRut().equals(buscado))
                return donaciones.get(i);
        }
        return null;
    }
    
    public void mostrarDonaciones(){
        int x;
        Donacion aux;
        System.out.println("\nCampania en "+localidad+" - ID "+id);
        if(donaciones.isEmpty())
            System.out.println("No existen donaciones en esta campania.");
        
        for(x = 0; x < donaciones.size() ; x++){
            aux = donaciones.get(x);
            aux.mostrarDonacion();
            
        }
    }
    
}
