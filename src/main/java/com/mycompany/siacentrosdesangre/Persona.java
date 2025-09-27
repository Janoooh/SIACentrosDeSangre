package com.mycompany.siacentrosdesangre;

public class Persona {
    private String rut;
    private String nombre;
    private String telefono;
    private int edad;
    private int tipoPersona; //1-Donante 2-Flebotomista
    
    public Persona(String rut, String nombre, String telefono, int edad, int tipoPersona){
        this.rut = rut;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.tipoPersona = tipoPersona;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public int getTipoPersona(){
        return tipoPersona;
    }
    
    public void setTipoPersona(int tipoPersona){
        this.tipoPersona = tipoPersona;
    }
    
    public String[] getInfo(){
        String[] info;
        if(tipoPersona == 1)
            info = new String[]{rut,nombre,telefono,String.valueOf(edad),"Donante"};
        else
            info = new String[]{rut,nombre,telefono,String.valueOf(edad),"Flebotomista"};
        
        return info;
    }
}
