package com.mycompany.siacentrosdesangre;

public class Donante {
    private String rut;
    private String nombre;
    private int edad;
    private String tipoSangre;
    private String telefono;
    
    
    public Donante(String rut, String nombre, int edad, String tipoSangre, String telefono){
        this.rut = rut;
        this.nombre = nombre;
        this.edad = edad;
        this.tipoSangre = tipoSangre;
        this.telefono = telefono;
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

    public void setNumeroTelefonico(String telefono) {
        this.telefono = telefono;
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
        return telefono;
    }
    
    /*Metodo getDatosDonante: Encargado de retornar un arreglo de String
    en donde se guardan todos los datos del donante, uno a uno.*/
    public String[] getDatosDonante(){
        String[] datos = new String[5];
        datos[0] = rut;
        datos[1] = nombre;
        datos[2] = String.valueOf(edad);
        datos[3] = tipoSangre;
        datos[4] = telefono;
        return datos;
    }
    
    /*Metodo mostrarDonante: Encargado de mostrar la informacion del
    donante por consola.*/
    public void mostrarDonante(){
        System.out.println("Rut: "+rut+"   Nombre: "+nombre+"   Edad: "+edad);
        System.out.println("Tipo Sangre: "+tipoSangre+"    Telefono: "+telefono);
    }
}
