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

    public Campania buscarCampania(String nombre) {
        int i;
        for(i = 0; i < campanias.size(); i ++){
            if(nombre.equals(campanias.get(i).getLocalidad()))
                return campanias.get(i);
        }
        return null;
    }
    
    public String []conseguirNombresCampanias(){
        String localidades[] = new String[campanias.size()];
        int i;
        
        for (i = 0; i < campanias.size(); i ++){
            localidades[i] = campanias.get(i).getLocalidad();
        }
        return localidades;
    }
}
