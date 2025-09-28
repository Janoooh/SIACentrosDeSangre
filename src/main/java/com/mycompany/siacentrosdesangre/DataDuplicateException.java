package com.mycompany.siacentrosdesangre;

/**
 * Excepción relacionada con la duplicidad de datos.
 * Se puede lanzar cuando se trata de agregar un objeto que ya existe a una colección.
 */
public class DataDuplicateException extends Exception {
    
    /**
     * Crea una excepcion de duplicidad.
     * @param msg mensaje con la espscificación del error.
     */
    public DataDuplicateException(String msg){
        super(msg);
    }
}
