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
    
    /*Metodo getInfo: encargado de retornar un arreglo de Strings con
    toda la informacion de la persona. Recibe un booleano que indica 
    si se quiere incluir el tipo de persona, o no. En caso de incluirse,
    se manda como texto el tipo que es, ya sea Donante o Flebotomista.*/
    public String[] getInfo(boolean incluirTipo){
        String[] info;
        
        if(!incluirTipo){
            info = new String[]{rut,nombre,telefono,String.valueOf(edad)};
            return info;
        }
        
        if(tipoPersona == 1)
            info = new String[]{rut,nombre,telefono,String.valueOf(edad),"Donante"};
        else
            info = new String[]{rut,nombre,telefono,String.valueOf(edad),"Flebotomista"};
        
        return info;
    }
}
