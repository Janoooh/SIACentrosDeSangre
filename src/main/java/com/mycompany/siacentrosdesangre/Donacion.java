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
    
    public String[] getDatosDonacionCompleta(){
        String[] datos = new String[7];
        String[] datosDonante;
        datosDonante = donador.getDatosDonante();
        datos[0] = String.valueOf(id);
        datos[1] = fecha;
        datos[2] = datosDonante[0];
        datos[3] = datosDonante[1];
        datos[4] = datosDonante[2];
        datos[5] = datosDonante[3];
        datos[6] = datosDonante[4];
        
        return datos;
    }
    
    public void mostrarDonacion(){
        System.out.println("\nDonacion ID "+id+"     Fecha:"+fecha);
        donador.mostrarDonante();
    }
}
    