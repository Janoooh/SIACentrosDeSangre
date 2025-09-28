package com.mycompany.siacentrosdesangre;

/**
 * Clase que contiene los datos básicos para representar una persona.
 * clase padre de {@link Donante}, {@link Flebotomista}. 
 */
public class Persona {
    private String rut;
    private String nombre;
    private String telefono;
    private int edad;
    private int tipoPersona;
    
    /**
     * Crea una persona con todos los datos especificados
     * @param rut es la forma de identificacion usada para las personas.
     * @param nombre nombre de la persona.
     * @param telefono numero de telefono que se le quiere asociar a la persona.
     * @param edad edad de la persona.
     * @param tipoPersona hay dos tipos donantes(1) o flebotomistas(2).
     */
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

    /**
     * método que toma los datos de una persona y los pasa a un arreglo de cadenas. 
     * Ej: {"11.222.333-4", "Juan Ramirez", "98776622", "33", "Donante"}
     * @param incluirTipo variable de control de flujo. Si es true returna todos los datos de la persona, si es false excluye el tipo de esa persona(último dato).
     * @return cadena con los datos de la persona.
     */
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
