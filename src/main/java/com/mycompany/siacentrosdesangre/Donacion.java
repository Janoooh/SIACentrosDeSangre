package com.mycompany.siacentrosdesangre;


public class Donacion {
    private int id;
    private String fecha;
    private Donante donador;
    
    public Donacion(int id, String fecha, Donante donador){
        this.id = id;
        this.fecha = fecha;
        this.donador = donador;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDonador(Donante donador) {
        this.donador = donador;
    }

    public int getId(){
        return id;
    }
    
    public String getFecha() {
        return fecha;
    }

    public Donante getDonador() {
        return donador;
    }
    
    public void mostrarDonacion(){
        System.out.println("\nDonacion ID "+id+"     Fecha:"+fecha);
        donador.mostrarDonante();
    }
}
    