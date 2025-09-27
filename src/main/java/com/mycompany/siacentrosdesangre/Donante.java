package com.mycompany.siacentrosdesangre;

public class Donante extends Persona{
    private String tipoSangre;
    
    
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

    /*Metodo getDatosDonante: Encargado de retornar un arreglo de String
    en donde se guardan todos los datos del donante, uno a uno.*/
    public String[] getDatosDonante(){
        String[] datos = new String[5];
        datos[0] = getRut();
        datos[1] = getNombre();
        datos[2] = getTelefono();
        datos[3] = String.valueOf(getEdad());
        datos[4] = tipoSangre;
        return datos;
    }
    
    /*Metodo mostrarDonante: Encargado de mostrar la informacion del
    donante por consola.*/
    public void mostrarDonante(){
        System.out.println("Rut: "+getRut()+"   Nombre: "+getNombre()+"   Edad: "+getEdad());
        System.out.println("Tipo Sangre: "+tipoSangre+"    Telefono: "+getTelefono());
    }
    
    public String[] getInfo(){
        String[] infoPers = super.getInfo();
        String[] infoGeneral = {infoPers[0],infoPers[1],infoPers[2],infoPers[3],infoPers[4],tipoSangre};
        return infoGeneral;
    }
}
