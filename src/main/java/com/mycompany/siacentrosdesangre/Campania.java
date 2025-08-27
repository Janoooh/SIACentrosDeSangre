
package com.mycompany.siacentrosdesangre;
import java.util.ArrayList;

public class Campania {
    private String localidad;
    private ArrayList<Donacion> donaciones;
    
    public Campania(){
        this.donaciones = new ArrayList<>();
    }
    
    public Campania(String localidad){
        this.localidad = localidad;
        this.donaciones = new ArrayList<>();
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDonaciones(ArrayList<Donacion> donaciones) {
        this.donaciones = donaciones;
    }

    public String getLocalidad() {
        return localidad;
    }

    public ArrayList<Donacion> getDonaciones() {
        return donaciones;
    }
    
}