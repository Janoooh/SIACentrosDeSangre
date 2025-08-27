package com.mycompany.siacentrosdesangre;

public class Donacion {
    private String fecha;
    private int cantidad;
    private Donante donador;
    
    public Donacion(String fecha, int cantidad, Donante donador){
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.donador = donador;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDonador(Donante donador) {
        this.donador = donador;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Donante getDonador() {
        return donador;
    }
    
}