package com.mycompany.siacentrosdesangre;
import java.util.ArrayList;

/**
 * Clase que representa una campaña de sangre.
 * Es una actividad organizada por una entidad para incentivar a donar sangre.
 */
public class Campania {
    private int id;
    private String localidad;
    private ArrayList<Donacion> donaciones;
    
    /**
     * Método constructor vacío, solo crea el arrayList para las donaciones.
     */
    public Campania(){
        this.donaciones = new ArrayList<>();
    }
    
    /**
     * Método constructor para la creación de una campaña de sangre.
     * @param id identificador de la campaña en el sistema.
     * @param localidad indica la localidad donde se ubica esta campaña.
     */
    public Campania(int id, String localidad){
        this.id = id;
        this.localidad = localidad;
        this.donaciones = new ArrayList<>();
    }
 
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void agregarDonacion(Donacion nuevo) {
        donaciones.add(nuevo);
    }
    
    public String getLocalidad() {
        return localidad;
    }
    
    public int getSizeDonaciones(){
        return donaciones.size();
    }
    
    /**
     * busca una donación dentro de la campaña. 
     * Recorre el ArrayList de donaciones y va preguntando a cada Donacion si tienen el mismo id que el buscado.
     * @param buscado representa el identificador de una donación.
     * @return una referencia a la donación que contiene el identificador dado. Si no está en la campaña retorna null.
     */
    public Donacion buscarDonacion(int buscado){
        int i;
        for(i = 0; i < donaciones.size(); i ++){
            if(buscado == donaciones.get(i).getId())
                return donaciones.get(i);
        }
        return null;
    }
    
    /**
     * Método para tener todos los datos de todas las donaciones en una arreglo de arreglos de cadenas.
     * Ej: {{"11.222.333-4", "Juan Ramirez", "98776622", "33", "Donante", "O-"}, {"99.888.777-6", "Maria Constanza", "9323231", "45", "Donante", "AB-"}, ...}.
     * Ocupa el tamaño del arrayList que contiene las donaciones para crear un arreglo. Recorre el arrayList de donaciones
     * y para cada donacion saca sus datos en forma de arreglo de cadenas, esos arreglos de cadenas se les van almacenando
     * en cada una de las posiciones del arreglo nuevo.
     * @param incluirTipo variable para incluir el tipo de persona dentro del arreglo.
     * @return arreglo de arreglos de cadenas con los datos de todas las donaciones
     */
    public String[][] datosMostrarDonaciones(boolean incluirTipo){
        int x;
        Donacion aux;
        String[][] retorno = new String[donaciones.size()][];
        
        for(x = 0; x < donaciones.size(); x++){
            aux = donaciones.get(x);
            retorno[x] = aux.getDatosDonacion(id, incluirTipo);
        }
        return retorno;
    }
    
    /**
     * Método que devuelve los datos de la campaña en un arreglo de cadenas. Id, localidad y cantidad de donaciones.
     * Ej: {"1", "Quillota", "5"}.
     * @return cadena con los datos de la campaña y la cantidad de donaciones en esa campaña.
     */
    public String[] getDatosCampania(){
        String[] retorno = {String.valueOf(id),localidad,String.valueOf(donaciones.size())};
        return retorno;
    }
    
    /**
     * Borra una donacion del ArrayList de donaciones.
     * Recorre el arrayList buscando la donacion que tenga el mismo id que el buscado.
     * @param id el identificador de la donacion buscada.
     * @return una referencia a la donacion eliminada. Si no se encuentra tira una excepción.
     * @throws NotFoundException Excepcion en el caso de que no haya ninguna donación con el id dado.
     */
    public Donacion borrarDonacion(int id)throws NotFoundException{
        int i;
        Donacion actual;
        for(i = 0; i < donaciones.size(); i++){
            actual = donaciones.get(i);
            if(actual.getId() == id){
                donaciones.remove(i);
                return actual;
            }
        }
        throw new NotFoundException("La donacion "+id+" no se encontro en la campania "+this.id); 
    }
    
    
    public void guardarDonaciones(String rutaDona){
        Donacion aux;
        String[] datosCamp, datosFormat;
        String linea;
        int x;
        
        for(x = 0; x < donaciones.size(); x++){
            aux = donaciones.get(x);
            datosCamp = aux.getDatosDonacion(id, false);
            datosFormat = new String[]{datosCamp[0],datosCamp[1],datosCamp[2],datosCamp[4],datosCamp[6]};
            linea = String.join("|", datosFormat);
            try{
                Herramientas.guardarEnArchivo(linea, rutaDona);
            }catch(Exception e){
                System.out.println("ERROR : "+e.getMessage());
            }
        }
    }
    public void desvincularPersonaDeDonacion(Persona persona){
        Donacion actual;
        int i;
        for(i = 0; i < donaciones.size(); i++){
            actual = donaciones.get(i);
            actual.desvincularPersona(persona);
        }
        
    }
    
}
