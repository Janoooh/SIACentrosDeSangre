package com.mycompany.siacentrosdesangre;

/**
 * Clase donante, representa a las personas que donaron sangre.
 * clase hija de {@link Persona}, contiene el tipo de sangre de la persona.
 */
public class Donante extends Persona{
    private String tipoSangre;
    
    /**
     * Constructor de un donante, toma todos los datos necesarios para crear una {@link Persona} y
     * su tipo de sangre para crear el donante.
     * @param rut identificador de la persona.
     * @param nombre nombre de la persona.
     * @param telefono numero de contacto de la persona.
     * @param edad edad de la persona
     * @param tipoPersona el tipo de la persona.Donante(1) o Flebotomista(2). En este caso será 1.
     * @param tipoSangre tipo de sangre que tiene el donante. {"O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"}.
     */
    public Donante(String rut, String nombre, String telefono, int edad, int tipoPersona, String tipoSangre){
        super(rut,nombre,telefono,edad,tipoPersona);
        this.tipoSangre = tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }
    
    /**
     * Método sobrecargado que permite retornar los datos de un donante y agregarlos a un arreglo de cadenas.
     * Ej: {"11.222.333-4", "Juan Ramirez", "98776622", "33", "Donante", "O-"}
     * @param incluirTipo variable que representa si se quiere que el arreglo contenga el dato con el tipo de persona
     * @return arreglo de cadenas con los datos del donante.
     */
    @Override
    public String[] getInfo(boolean incluirTipo){
        String[] infoGeneral;
        String[] infoPers = super.getInfo(incluirTipo);
        
        if(incluirTipo){
            infoGeneral = new String[]{infoPers[0],infoPers[1],infoPers[2],infoPers[3],infoPers[4],tipoSangre};
        }else{
            infoGeneral = new String[]{infoPers[0],infoPers[1],infoPers[2],infoPers[3],tipoSangre};
        }
        
        return infoGeneral;
    }
}
