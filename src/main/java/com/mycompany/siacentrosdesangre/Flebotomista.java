package com.mycompany.siacentrosdesangre;

public class Flebotomista extends Persona {
    private String especialidad;
    private String correo;

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
    
    
    
}
