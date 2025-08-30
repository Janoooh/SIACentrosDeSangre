
package com.mycompany.siacentrosdesangre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Herramientas {
    
    public static ArrayList<String> leerArchivo()throws FileNotFoundException, IOException{
        int nLineas, x;
        String ruta = "tiposDeSangre.txt";
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        ArrayList<String> datosArchivo = new ArrayList<>();
        String linea = lector.readLine();
        while (linea != null) {
            datosArchivo.add(linea);
            linea = lector.readLine();
        }
        
        return datosArchivo;
    }
    
    public static String lector(String mensaje) throws IOException{
        System.out.print(mensaje);
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String cadena = lector.readLine();
        return cadena;
    }
    
    
    
}
