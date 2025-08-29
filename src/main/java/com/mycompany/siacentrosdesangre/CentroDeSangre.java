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
}

