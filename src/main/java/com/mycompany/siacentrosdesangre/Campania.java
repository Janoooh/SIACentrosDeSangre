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
        System.out.println("Campania "+id+" - Localidad "+localidad);
        if(donaciones.isEmpty())
            System.out.println("No existen donaciones en esta campania.");
        
        for(x = 0; x < donaciones.size() ; x++){
            aux = donaciones.get(x);
            aux.mostrarDonacion();
            
        }
    }
    
    public String[] obtenerInfoDonadores(){
        String info[] = new String [donaciones.size()];
        int i;
        
        for (i = 0; i < donaciones.size(); i ++){
            info[i] = "Fecha: " + donaciones.get(i).getFecha()+ " Nombre: " + donaciones.get(i).getDonador().getNombre() + "\n" + 
                    "              Rut : " + donaciones.get(i).getDonador().getRut() + " Tipo de sangre : " + donaciones.get(i).getDonador().getTipoSangre() + "\n" +
                    "              Numero de telefono: " + donaciones.get(i).getDonador().getNumeroTelefonico();
        }
        return info;
    }
    
}
