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
    
    public static void cargarSangre(CentroDeSangre centro, String rutaSangre)throws IOException{
        ArrayList<String> datosArch;
        String[] sangre = {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
        int x;
        
        try{
            datosArch = leerArchivo(rutaSangre);
            for(x = 0; x < datosArch.size(); x++)
                centro.agregarStockSangre(sangre[x], Integer.parseInt(datosArch.get(x)));
            
        }catch(FileNotFoundException e){
            System.out.println("LA RUTA "+rutaSangre+" NO ES VALIDA.");
            System.exit(1);
        }catch(NumberFormatException e){
            System.out.println("SE LEYO UN DATO ERRONEO.");
            System.exit(1);
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
            System.exit(1);
        }
    }
    
    public static void cargarPersonas(CentroDeSangre centro, String rutaPers)throws IOException{
        ArrayList<String> datosArch;
        int x;
        String[] datosLinea;
        try{
            datosArch = leerArchivo(rutaPers);
            for(x = 0; x < datosArch.size(); x++){
                datosLinea = datosArch.get(x).split("\\|");
                if(datosLinea.length >= 4){
                    if(datosLinea[4].equals("1"))
                        centro.agregarPersona(datosLinea[0], datosLinea[1], datosLinea[2], Integer.parseInt(datosLinea[3]), Integer.parseInt(datosLinea[4]), datosLinea[5]);
                    if(datosLinea[4].equals("2"))
                        centro.agregarPersona(datosLinea[0], datosLinea[1], datosLinea[2], Integer.parseInt(datosLinea[3]), Integer.parseInt(datosLinea[4]), datosLinea[5],datosLinea[6]);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("LA RUTA "+rutaPers+" NO ES VALIDA.");
            System.exit(1);
        }catch(NumberFormatException e){
            System.out.println("SE LEYO UN DATO ERRONEO.");
            System.exit(1);
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
            System.exit(1);
        }
        return;
    }
    
    public static void cargarCampanias(CentroDeSangre centro, String rutaCamp){
        ArrayList<String> datosArch;
        int x;
        String[] datosLinea;
        try{
            datosArch = leerArchivo(rutaCamp);
            for(x = 0; x < datosArch.size(); x++){
                datosLinea = datosArch.get(x).split("\\|");
                centro.agregarCampaniaNueva(Integer.parseInt(datosLinea[0]), datosLinea[1]);
            }
        }catch(FileNotFoundException e){
            System.out.println("LA RUTA "+rutaCamp+" NO ES VALIDA.");
            System.exit(1);
        }catch(NumberFormatException e){
            System.out.println("SE LEYO UN DATO ERRONEO.");
            System.exit(1);
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
            System.exit(1);
        }
        return;
    }
    
    public static void cargarDonaciones(CentroDeSangre centro, String rutaDona){
        ArrayList<String> datosArch;
        int x;
        Campania aux;
        String[] datosLinea;
        Persona donante, flebo;
        try{
            datosArch = leerArchivo(rutaDona);
            for(x = 0; x < datosArch.size(); x++){
                datosLinea = datosArch.get(x).split("\\|");
                aux = centro.buscarCampania(Integer.parseInt(datosLinea[4]));
                if(aux == null)throw new NotFoundException("No se encontro la campaña "+datosLinea[4]+".");
                donante = centro.buscarPersona(datosLinea[2], 1);
                flebo = centro.buscarPersona(datosLinea[3], 2);
                aux.agregarDonacion(new Donacion(Integer.parseInt(datosLinea[0]),datosLinea[1],donante,flebo));
            }
        }catch(FileNotFoundException e){
            System.out.println("LA RUTA "+rutaDona+" NO ES VALIDA.");
            System.exit(1);
        }catch(NumberFormatException e){
            System.out.println("SE LEYO UN DATO ERRONEO.");
            System.exit(1);
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
            System.exit(1);
        }
        return;
    }
    
    /*Metodo cargarDatos: Encargado de procesar los distintos archivos de datos
    que usa el sistema, pasando la informacion de ahi a el objeto CentroDeSangre.
    Primero obtiene el nombre y las campanias de datosCampania, para despues obtener
    las donaciones de datosDonaciones.*/
    /*public static void cargarDatos(CentroDeSangre centro, String rutaCamp, String rutaDona)throws IOException{
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
        
    }*/
    
    /*Metodo guardarEnArchivo: Encargado de escribir en un archivo con ruta dada
    una cadena pasada por parametro. Su funcion es traspasar los datos del sistema a
    los documentos .txt.*/
    public static void guardarEnArchivo(String linea, String ruta)throws IOException{
        BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta,true));
        escritor.write(linea+"\n");
        escritor.close();
    }
    
    public static void limpiarArchivo(String ruta){
        BufferedWriter escritor = null;
        try{
            escritor = new BufferedWriter(new FileWriter(ruta,false));
        }catch(IOException e){
            System.out.println("Error de limpieza de archivo : "+e.getMessage());
        }finally{
            if(escritor != null){
                try{
                    escritor.close();
                }catch(IOException e){
                    System.out.println("Error al cerrar el archivo : "+e.getMessage());
                }
            }
        }
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
