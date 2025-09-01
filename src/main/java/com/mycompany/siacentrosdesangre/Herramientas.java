
package com.mycompany.siacentrosdesangre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Herramientas {
    
    /*Metodo leerArchivo: Encargado de leer un archivo de una ruta dada, pasando todas sus lineas
    de texto a un ArrayList de String, el cual es retornado.*/
    public static ArrayList<String> leerArchivo(String ruta)throws FileNotFoundException, IOException{
        int nLineas, x;
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        ArrayList<String> datosArchivo = new ArrayList<>();
        String linea = lector.readLine();
        while (linea != null) {
            datosArchivo.add(linea);
            linea = lector.readLine();
        }
        
        return datosArchivo;
    }
    
    /*Metodo cargarDatos: Encargado de procesar los distintos archivos de datos
    que usa el sistema, pasando la informacion de ahi a el objeto CentroDeSangre.
    Primero obtiene el nombre y las campanias de datosCampania, para despues obtener
    las donaciones de datosDonaciones.*/
    public static void cargarDatos(CentroDeSangre centro, String rutaCamp, String rutaDona)throws IOException{
        ArrayList<String> datos;
        datos = Herramientas.leerArchivo(rutaCamp);
        Campania auxCampania;
        Donacion auxDonacion;
        Donante auxDonante;
        String[] datosLinea;
        int x = 1; //Parte en 1 por que el 0 es el nombre del centro.
        //Lectura del nombre del centro de sangre.
        centro.setNombre(datos.get(0));
        //Lectura de las campanias.
        while(x < datos.size()){
            datosLinea = datos.get(x).split("\\*");
            auxCampania = new Campania(Integer.parseInt(datosLinea[0]), datosLinea[1]);
            centro.agregarCampania(auxCampania);
            x++;
        }
        
        //Lectura de las donaciones
        datos = Herramientas.leerArchivo(rutaDona);
        x = 0;//Reiniciamos el indice.
        while(x < datos.size()){
            datosLinea = datos.get(x).split("\\*");
            auxDonante = new Donante(datosLinea[2],datosLinea[3],Integer.parseInt(datosLinea[4]),datosLinea[5],datosLinea[6]);
            auxDonacion = new Donacion(Integer.parseInt(datosLinea[0]),datosLinea[1],auxDonante);
            centro.agregarDonacion(auxDonacion,Integer.parseInt(datosLinea[7]),datosLinea[5]);
            x++;
        }
        
    }
    
    /*Metodo guardarEnArchivo: Encargado de escribir en un archivo con ruta dada
    una cadena pasada por parametro. Su funcion es traspasar los datos del sistema a
    los documentos .txt.*/
    public static void guardarEnArchivo(String linea, String ruta)throws IOException{
        BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta,true));
        escritor.write(linea+"\n");
        escritor.close();
    }
    
    /*Metodo lector: Encargado de leer una entrada por consola del usuario,
    indicandole lo que tiene que ingresar por medio de un String recibido
    por parametro.*/
    public static String lector(String mensaje) throws IOException{
        String cadena;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(mensaje);
        cadena = lector.readLine();
        return cadena;
    }
    
    
    
}
