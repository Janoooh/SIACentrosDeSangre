package com.mycompany.siacentrosdesangre;

/**
 * Clase que representa a un profesional encargado de extraer sangre de un donante.
 * Clase hija de {@link Persona}, además contiene su especialidad y su correo.
 */
public class Flebotomista extends Persona {
    private String especialidad;
    private String correo;
    
    /**
     * Constructor de un flebotomista, necesita todos los valores. 
     * @param rut identificador de la persona.
     * @param nombre nombre de la persona.
     * @param telefono numero de contacto de la persona.
     * @param edad edad de la persona
     * @param tipoPersona el tipo de la persona.Donante(1) o Flebotomista(2). En este caso será 2.
     * @param especialidad identifica la especializacion del profesional encargado.
     * @param correo indica el correo del flebotomista.
     */
    public Flebotomista(String rut, String nombre, String telefono, int edad, int tipoPersona, String especialidad, String correo) {
        super(rut, nombre, telefono, edad, tipoPersona);
        this.especialidad = especialidad;
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Método sobrecargado que permite tener los datos del flebotomista en un arreglo de cadenas.
     * Ej: {"77.666.555-4", "Pedro Aguilera", "932231344", "50", "Flebotomista", "Cirujano", "pedro.agl@hospi.mail.com"}
     * @param incluirTipo variable para saber si se quiere incluir el tipo de persona en la cadena.
     * @return arreglo de cadena con los datos del flebotomista
     */
    @Override
    public String[] getInfo(boolean incluirTipo){
        String[] infoGeneral;
        String[] infoPers = super.getInfo(incluirTipo);
        
        if(incluirTipo){
            infoGeneral = new String[]{infoPers[0],infoPers[1],infoPers[2],infoPers[3],infoPers[4],especialidad,correo};
        }else{
            infoGeneral = new String[]{infoPers[0],infoPers[1],infoPers[2],infoPers[3],especialidad, correo};
        }
        return infoGeneral;
    }
    
}
