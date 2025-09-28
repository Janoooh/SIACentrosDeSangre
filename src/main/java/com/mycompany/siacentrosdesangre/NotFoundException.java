package com.mycompany.siacentrosdesangre;

/**
 * Clase llamada para menejar excepciones en busquedas de elementos, más precisamente cuando no se encuentra el buscado.
 */
public class NotFoundException extends Exception{
    
    /**
     * Crea una nueva excepcion de tipo no encontrado.
     * @param msg cadena con la información relacionada de la excepción.
     */
    public NotFoundException(String msg){
        super(msg);
    }
}
