package com.mycompany.siacentrosdesangre;

/**
 * Clase que representa el detalle de una donacion de sangre.
 */
public class Donacion {
    private int id;
    private String fecha;
    private Persona donador;
    private Persona flebotomista;
    
    /**
     * Constructor de la donacion.
     * @param id identificador de la donacion
     * @param fecha fecha de cuando ocurrio la donacion
     * @param donador objeto tipo donador contiene los datos del donador de sangre
     * @param flebotomista objeto tipo flebotomista, contiene los datos del profesional a cargo de la extraccion de sangre
     */
    public Donacion(int id, String fecha, Persona donador, Persona flebotomista){
        this.id = id;
        this.fecha = fecha;
        this.donador = donador;
        this.flebotomista = flebotomista;
    }
    
    /**
     * Constructor de donación que recibe todos los datos primitivos necesarios para la creacion.
     * @param id identificador de la donación.
     * @param fecha indica la fecha que se realizó la donación.
     * @param rut identificador de la persona que realizó la donación.
     * @param nombre nombre del donante.
     * @param edad edad del donante.
     * @param tipoSangre tipo de sangre del donante.
     * @param telefono número de teléfono del donante.
     * @param tipoPersona tipo de persona. En este caso Donante(1).
     */
    public Donacion(int id, String fecha, String rut, String nombre, int edad, String tipoSangre, String telefono, int tipoPersona){
        this.id = id;
        this.fecha = fecha;
        donador = new Donante(rut, nombre, telefono, edad, tipoPersona, tipoSangre);
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDonador(Donante donador) {
        this.donador = donador;
    }
    
    public void setFlebotomista(Persona flebotomista){
        this.flebotomista = flebotomista;
    }

    public int getId(){
        return id;
    }
    
    public String getFecha() {
        return fecha;
    }

    public Persona getDonador() {
        return donador;
    }
    
    public Persona getFlebotomista(){
        return flebotomista;
    }
    
    /*Metodo getDatosDonacionCompleta: Encargado de retornar un
    arreglo de String con toda la informacion de la donacion, osea,
    los datos del donante tambien se guardan uno a uno en el arreglo.*/
    
    /**
     * Método que retorna los datos de la donacion.
     * Guarda los datos tanto del Donante como del Flebotomista en arreglos de cadenas.
     * Creo un tercer arreglo de cadenas para juntar los datos y luego lo retorna.
     * No retorna todo los datos de los objetos Donante ni del Flebotomista. Se retorna lo siguiente:
     * Id Donacion, fecha donacion, rut donante, nombre donante, rut flebotomista, nombre flebotomista y Id campaña.
     * Ej: {"1", "27/10/2020", "11.222.333-4", "Juan Ramirez", "77.666.555-4", "Pedro Aguilera", "20"}.
     * @param idCamp identificador de una campaña.
     * @param incluirTipo variable que identifica si se quiere incluir el tipo de persona en la cadena de datos de la persona,
     * @return arreglo de cadenas con los datos de la donacion.
     */
    public String[] getDatosDonacion(int idCamp, boolean incluirTipo){
        String[] infDonante = donador.getInfo(incluirTipo);
        String[] infFlebo = flebotomista.getInfo(incluirTipo);
        String[] datos = {String.valueOf(id),fecha,infDonante[0],infDonante[1],infFlebo[0],infFlebo[1],String.valueOf(idCamp)};
        return datos;
    }
    
    /*Metodo mostrarDonacion: Encargado de mostrar la id y fecha de la
    donacion, ademas de la informacion del donante.*/
    public void mostrarDonacion(){
        System.out.println("\nDonacion ID "+id+"     Fecha:"+fecha);
        ((Donante)donador).mostrarDonante();
    }
    
}

    