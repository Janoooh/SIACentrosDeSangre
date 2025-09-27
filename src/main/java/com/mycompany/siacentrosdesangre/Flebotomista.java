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
