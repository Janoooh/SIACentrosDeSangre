package com.mycompany.siacentrosdesangre;


public class Donacion {
    private int id;
    private String fecha;
    private Persona donador;
    private Persona flebotomista;
    
    public Donacion(int id, String fecha, Persona donador, Persona flebotomista){
        this.id = id;
        this.fecha = fecha;
        this.donador = donador;
        this.flebotomista = flebotomista;
    }
    
    //metodo constructor que toma todos los datos duros, necesario para crear por ventana
    public Donacion(int id, String fecha, String rut, String nombre, int edad, String tipoSangre, String telefono, int tipoPersona){
        this.id = id;
        this.fecha = fecha;
        donador = new Donante(rut, nombre, telefono, edad, tipoPersona, tipoSangre);
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
    
    public void setFlebotomista(Persona flebotomista){
        this.flebotomista = flebotomista;
    }

    public int getId(){
        return id;
    }
    
    public String getFecha() {
        return fecha;
    }

    public Persona getDonador() {
        return donador;
    }
    
    public Persona getFlebotomista(){
        return flebotomista;
    }
    
    /*Metodo getDatosDonacionCompleta: Encargado de retornar un
    arreglo de String con toda la informacion de la donacion, osea,
    los datos del donante tambien se guardan uno a uno en el arreglo.*/
    public String[] getDatosDonacion(int idCamp){
        String[] infDonante = donador.getInfo();
        String[] infFlebo = flebotomista.getInfo();
        String[] datos = {String.valueOf(id),fecha,infDonante[0],infDonante[1],infFlebo[0],infFlebo[1],String.valueOf(idCamp)};
        return datos;
    }
    
    /*Metodo mostrarDonacion: Encargado de mostrar la id y fecha de la
    donacion, ademas de la informacion del donante.*/
    public void mostrarDonacion(){
        System.out.println("\nDonacion ID "+id+"     Fecha:"+fecha);
        ((Donante)donador).mostrarDonante();
    }
    
}

    