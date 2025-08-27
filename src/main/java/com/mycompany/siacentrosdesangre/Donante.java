package com.mycompany.siacentrosdesangre;

public class Donante {
    private String rut;
    private String nombre;
    private int edad;
    private String tipoSangre;
    private String numeroTelefonico;
    
    
    public Donante(String rut, String nombre, int edad, String tipoSangre, String numeroTelefonico){
        this.rut = rut;
        this.nombre = nombre;
        this.edad = edad;
        this.tipoSangre = tipoSangre;
        this.numeroTelefonico = numeroTelefonico;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }
}
