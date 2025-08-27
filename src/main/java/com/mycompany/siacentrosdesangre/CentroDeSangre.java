
package com.mycompany.siacentrosdesangre;

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

    public void setCampanias(ArrayList<Campania> campanias) {
        this.campanias = campanias;
    }

    public void setStockSangre(HashMap<String, Integer> stockSangre) {
        this.stockSangre = stockSangre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Campania> getCampanias() {
        return campanias;
    }

    public HashMap<String, Integer> getStockSangre() {
        return stockSangre;
    }
    
}
