
package com.mycompany.siacentrosdesangre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Herramientas {
    
    public static String[] leerArchivo()throws FileNotFoundException, IOException{
        String ruta = "tiposDeSangre.txt";
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        String cadena [] = new String[10];
        String linea = lector.readLine();
        int i = 0;
        while (linea != null) {
            cadena[i] = linea;
            linea = lector.readLine();
            i ++;
        }
        return cadena;
    }
    
    public static String lector(String mensaje) throws IOException{
        System.out.println(mensaje);
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String cadena = lector.readLine();
        return cadena;
    }
    
    
    
}
